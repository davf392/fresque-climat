package com.davidfz.animfresque.ui.animate

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit

const val REMAINING_TIME_TOTAL_FORMAT = "%02d:%02d:%02d"
private const val TAG = "AnimateViewModel"

open class AnimateViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AnimationUiState())
    open val uiState: StateFlow<AnimationUiState> = _uiState.asStateFlow()

    init {
        resetAnimation()
    }

    fun setPhaseDuration(index: Int, minutes: Int, seconds: Int) {
        Log.d(TAG, "setPhaseDuration")
        updatePhase(index) { it.copy().apply {
            editedDuration = (minutes * 60) + seconds
            timer.initialDuration = editedDuration
            showTimePicker = false
            timer.reset()
        } }
    }

    fun setShowTimePicker(index: Int, show: Boolean) {
        Log.d(TAG, "setShowTimePicker")
        updatePhase(index) { it.copy(showTimePicker = show) }
    }

    private fun updatePhase(index: Int, update: (AnimationPhaseUiState) -> AnimationPhaseUiState) {
        _uiState.update { currentState ->
            currentState.animationPhases.toMutableList().apply {
                if (index in indices) {
                    this[index] = update(this[index])
                }
            }.let { currentState.copy(animationPhases = it) }
        }
    }

    private fun resetAnimation() {
        Log.d(TAG, "resetAnimation")
        val phasesList = initPhasesList()
        val totalTime = phasesList.sumOf { it.timer.initialDuration }
        _uiState.update {
            AnimationUiState(
                animationPhases = phasesList,
                totalTimeInSec = totalTime,
                totalTimeFormatted = getFormattedRemainingTime(totalTime)
            )
        }
    }

    private fun getFormattedRemainingTime(totalTime: Int): String {
        return REMAINING_TIME_TOTAL_FORMAT.format(
            TimeUnit.SECONDS.toHours(totalTime.toLong()),
            TimeUnit.SECONDS.toMinutes(totalTime.toLong()) % 60,
            TimeUnit.SECONDS.toSeconds(totalTime.toLong()) % 60
        )
    }

    private fun initPhasesList(): List<AnimationPhaseUiState> {
        return mutableStateListOf(
            AnimationPhaseUiState("Intro", CountDownTimer(15 * 60)),
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