package com.davidfz.animfresque.ui.animate.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidfz.animfresque.ui.animate.ui_state.AnimationPhaseUiState
import com.davidfz.animfresque.ui.animate.ui_state.AnimationPlayState
import com.davidfz.animfresque.ui.animate.ui_state.AnimationUiState
import com.davidfz.animfresque.ui.animate.CountDownTimer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit


private const val TAG = "AnimateViewModel"

private const val END_TIME_FORMAT = "HH:mm"

open class AnimateViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AnimationUiState())
    open val uiState: StateFlow<AnimationUiState> = _uiState.asStateFlow()

    private var endTimeUpdateJob: Job? = null

    init {
        resetAnimation()
        startEndTimeUpdateJob()
    }

    fun setPhaseDuration(index: Int, minutes: Int, seconds: Int) {
        Log.d(TAG, "setPhaseDuration (min=$minutes, sec=$seconds) for item $index")
        updatePhaseUiState(index) { it.copy().apply { setNewDuration(minutes, seconds) } }
    }

    fun setShowTimePicker(index: Int, show: Boolean) {
        Log.d(TAG, "setShowTimePicker(show=$show) for item $index")
        if (_uiState.value.animationPlayState.isPlaying) return
        updatePhaseUiState(index) { it.copy(showTimePicker = show) }
    }

    fun startStopAnimation() {
        Log.d(TAG, "startStopAnimation()")
        val isPlaying = _uiState.value.animationPlayState.isPlaying
        val shouldStartPlaying = !isPlaying

        _uiState.update {
            updateAnimationPhasesUi(_uiState.value.animationPhases.mapIndexed { index, phaseState ->
                phaseState.copy(isAnimationStarted = shouldStartPlaying).apply {
                    // reset each animation phase timer and start the first one
                    timer.reset()
                    if (shouldStartPlaying && index == 0)
                        timer.start(viewModelScope)
                }
            }.toMutableList()).copy(
                currentlyActiveTimer = if (shouldStartPlaying) 0 else -1,
                animationPlayState = if (isPlaying) AnimationPlayState.STOPPED else AnimationPlayState.RESUMED
            )
        }
    }

    fun startPauseAnimation() {
        Log.d(TAG, "startPauseAnimation()")
        val isResumed = _uiState.value.animationPlayState == AnimationPlayState.RESUMED
        val newAnimationPlayState = if (isResumed) AnimationPlayState.PAUSED else AnimationPlayState.RESUMED
        val runningIndex = _uiState.value.currentlyActiveTimer.also { runningIndex ->
            if (runningIndex == -1) return
            if (_uiState.value.animationPhases[runningIndex].timer.isTimerZero.value) return
        }
        // start or pause currently running timer according to button state
        updatePhaseUiState(runningIndex) {
            it.copy().apply { if (isResumed) it.timer.pause() else it.timer.start(viewModelScope) }
        }
        // update the state of the animation player
        _uiState.update { it.copy(animationPlayState = newAnimationPlayState) }
    }

    fun nextAnimationPhase() {
        Log.d(TAG, "nextAnimationPhase()")
        val currentPhases = _uiState.value.animationPhases
        val runningIndex = _uiState.value.currentlyActiveTimer

        if (runningIndex != -1) {
            // pause currently running timer
            updatePhaseUiState(runningIndex) { it.copy().apply { it.timer.pause() } }
            val nextIndex = runningIndex + 1
            if (nextIndex < currentPhases.size) {
                // start the next one
                _uiState.update { it.copy(currentlyActiveTimer = nextIndex) }
                updatePhaseUiState(nextIndex) { it.copy().apply { timer.start(viewModelScope) } }
            }
        } else {
            // if no timer is running, start the first one
            if (currentPhases.isNotEmpty()) {
                updatePhaseUiState(0) { it.copy().apply { timer.start(viewModelScope) } }
                _uiState.update { it.copy(currentlyActiveTimer = 0) }
            }
        }
        // update the state of the animation player
        _uiState.update { it.copy(animationPlayState = AnimationPlayState.RESUMED) }
    }

    private fun updatePhaseUiState(index: Int, update: (AnimationPhaseUiState) -> AnimationPhaseUiState) {
        Log.d(TAG, "updatePhaseUiState(index=$index)")
        _uiState.update {
            val currentPhases = it.animationPhases
            if (index in currentPhases.indices) {
                updateAnimationPhasesUi(newPhases = currentPhases.toMutableList().apply { this[index] = update(this[index]) })
            } else it
        }
    }

    private fun updateAnimationPhasesUi(newPhases: List<AnimationPhaseUiState> = _uiState.value.animationPhases) =
        _uiState.value.copy(
            animationPhases = newPhases,
            totalDurationInSec = newPhases.sumOf { it.timer.initialDuration },
            totalRemainingTimeInSec = newPhases.sumOf { it.timer.remainingTime.value },
            totalTimeFormatted = getFormattedRemainingTime(newPhases.sumOf { it.timer.initialDuration }),
            totalRemainingTimeFormatted = getFormattedRemainingTime(newPhases.sumOf { it.timer.remainingTime.value }),
            plannedEndTimeFormatted = getFormattedEndTime(newPhases.sumOf { it.timer.initialDuration }),
        )

    private fun getFormattedEndTime(totalTime: Int): String {
        val now = Calendar.getInstance().time
        val endTime = Calendar.getInstance().apply {
            timeInMillis = now.time + TimeUnit.SECONDS.toMillis(totalTime.toLong())
        }
        return SimpleDateFormat(END_TIME_FORMAT, Locale.getDefault()).format(endTime.time)
    }

    private fun resetAnimation() {
        Log.d(TAG, "resetAnimation")
        _uiState.update { updateAnimationPhasesUi(initPhasesList()) }
    }

    private fun startEndTimeUpdateJob() {
        var lastMinuteUpdated: Int = -1
        endTimeUpdateJob = viewModelScope.launch {
            // compute end time as long as animation has not started yet
            while (true) {
                val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
                if (currentMinute != lastMinuteUpdated) {
                    lastMinuteUpdated = currentMinute
                    _uiState.update { updateAnimationPhasesUi() }
                }
                delay(500) // check for a new minute each 0,5 sec
            }
        }
    }

    private fun getFormattedRemainingTime(totalTime: Int): String {
        val hours = TimeUnit.SECONDS.toHours(totalTime.toLong())
        val minutes = TimeUnit.SECONDS.toMinutes(totalTime.toLong()) % 60
        return if (hours > 0 && minutes >= 0) "$hours h $minutes min" else "$minutes min"
    }

    private fun initPhasesList(): List<AnimationPhaseUiState> {
        return mutableStateListOf(
            AnimationPhaseUiState("Intro", CountDownTimer(10 * 60)),
            AnimationPhaseUiState("Lot 1", CountDownTimer(10 * 60)),
            AnimationPhaseUiState("Lot 2", CountDownTimer(15 * 60)),
            AnimationPhaseUiState("Lot 3", CountDownTimer(20 * 60)),
            AnimationPhaseUiState("Lot 4", CountDownTimer(15 * 60)),
            AnimationPhaseUiState("Lot 5", CountDownTimer(10 * 60)),
            AnimationPhaseUiState("Créativité", CountDownTimer(10 * 60)),
            AnimationPhaseUiState("Synthèse", CountDownTimer(5 * 60)),
            AnimationPhaseUiState("Quiz", CountDownTimer(15 * 60)),
            AnimationPhaseUiState("Émotions", CountDownTimer(15 * 60)),
            AnimationPhaseUiState("Débats", CountDownTimer(45 * 60)),
            AnimationPhaseUiState("Conclusion", CountDownTimer(10 * 60))
        )
    }
}