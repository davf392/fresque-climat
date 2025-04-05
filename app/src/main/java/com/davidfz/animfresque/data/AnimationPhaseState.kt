package com.davidfz.animfresque.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimationPhaseState(
    val phase: AnimationPhase,
    val onTimeFinished: () -> Unit,
    val onTimerZero: (AnimationPhaseState) -> Unit
) {
    var remainingTime by mutableStateOf(phase.duration)
    var displayedTimeInSec by mutableStateOf(phase.duration)
    var editedDuration by mutableStateOf(phase.duration)
    var elapsedSeconds by mutableStateOf(0L)
    var isPaused by mutableStateOf(true)
    var showTimePicker by mutableStateOf(false)
    var isTimerZero by mutableStateOf(false)

    private var timerJob: Job? = null
    private var elapsedJob: Job? = null

    fun resetTimer() {
        Log.d(TAG, "resetTimer()")
        pauseTimer()
        remainingTime = phase.duration
        displayedTimeInSec = phase.duration
        elapsedSeconds = 0L
        isTimerZero = false
    }

    fun startTimer(scope: CoroutineScope) {
        Log.d(TAG, "startTimer() - isPaused = $isPaused")
        if (isPaused) {
            isPaused = false
            timerJob = scope.launch {
                while (remainingTime > 0) {
                    delay(1000)
                    remainingTime -= 1
                    displayedTimeInSec = remainingTime
                }
                // timer has reached 0
                isPaused = true
                displayedTimeInSec = 0
                delay(1000) // we wait 1 sec because timer = 999ms
                isTimerZero = true
                onTimerZero(this@AnimationPhaseState) // Call the new lambda
                elapsedJob = scope.launch {
                    while (isTimerZero) {
                        delay(1000)
                        elapsedSeconds++
                    }
                }
                Log.d(TAG, "onTimeFinished()")
                onTimeFinished()
            }
        }
    }

    fun pauseTimer() {
        Log.d(TAG, "pauseTimer()")
        if (!isPaused) {
            isPaused = true
            timerJob?.cancel()
            elapsedJob?.cancel()
        }
    }

    fun setNewDuration(minutes: Int, seconds: Int) {
        Log.d(TAG, "setNewDuration($minutes, $seconds)")
        editedDuration = (minutes * 60) + seconds
        phase.duration = editedDuration
        resetTimer()
        showTimePicker = false
    }

    fun formatElapsedTime() =
        TIMER_ELAPSED_TIME_FORMAT.format(elapsedSeconds / 60, elapsedSeconds % 60)

    fun formatDuration() =
        TIMER_REMAINING_TIME_FORMAT.format(
            displayedTimeInSec / 60,
            displayedTimeInSec % 60
        )

    companion object {
        const val TAG = "PhaseTimerState"
        const val TIMER_REMAINING_TIME_FORMAT = "%02d:%02d"
        const val TIMER_ELAPSED_TIME_FORMAT = "- %02d:%02d"
    }
}