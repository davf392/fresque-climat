package com.davidfz.animfresque.ui.animate

import android.os.CountDownTimer
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

const val TIME_FORMAT = "%02d:%02d"

/**
 * A versatile countdown timer that supports start, pause, reset, and provides
 * observable states for remaining time, elapsed time and running status.
 * The timer uses coroutines for asynchronous operation and Flow for emitting state updates.
 *
 * @property initialDuration The initial duration of the timer in milliseconds.
 * @property onTimeFinished A callback function that is invoked when the timer reaches zero.
 */
class CountDownTimer(
    var initialDuration: Int = 0,
    val onTimeFinished: () -> Unit = {}
) {
    private var elapsedTime = 0
    private var elapsedJob: Job? = null
    private var androidTimer: CountDownTimer? = null

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _isTimerZero = MutableStateFlow(false)
    val isTimerZero: StateFlow<Boolean> = _isTimerZero.asStateFlow()

    private val _remainingTime = MutableStateFlow(initialDuration)
    val remainingTime: StateFlow<Int> = _remainingTime.asStateFlow()

    private val _remainingTimeFormatted = MutableStateFlow(formatTime(_remainingTime.value, TIME_FORMAT))
    val remainingTimeFormatted: StateFlow<String> = _remainingTimeFormatted.asStateFlow()

    private val _elapsedTimeFormatted = MutableStateFlow("")
    val elapsedTimeFormatted: StateFlow<String> = _elapsedTimeFormatted.asStateFlow()

    /**
     * Resets the timer to its initial duration, pauses the countdown, and resets the elapsed time.
     */
    fun reset() {
        Log.d(TAG, "reset()")
        pause()
        _remainingTime.update { initialDuration }
        elapsedTime = 0
        updateFormattedTime()
    }

    /**
     * Starts the timer.
     * If the timer is already running, this function does nothing.
     * The countdown progresses every second until the remaining time reaches zero,
     * at which point the [onTimeFinished] callback is invoked and an elapsed time counter starts.
     *
     * @param scope The [CoroutineScope] in which to launch the countdown.
     */
    fun start(scope: CoroutineScope) {
        Log.d(TAG, "start() - isRunning = ${_isRunning.value}")
        if (_isRunning.value) return
        _isRunning.update { true }
        androidTimer = createCountDownTimer(scope).also { it.start() }
    }

    /**
     * Pauses the timer if it is currently running.
     */
    fun pause() {
        Log.d(TAG, "pause()")
        if (_isRunning.value) {
            _isRunning.update { false }
            androidTimer?.cancel()
            elapsedJob?.cancel()
            _isTimerZero.update { false }
        }
    }

    /**
     * Checks if the timer has been started or not.
     *
     * @return `true` if the timer is running and remaining time is different from the initial duration, `false` otherwise.
     */
    fun isStarted() = _isRunning.value && (_remainingTime.value != initialDuration)

    private fun createCountDownTimer(scope: CoroutineScope) =
        object : CountDownTimer(_remainingTime.value * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _remainingTime.update { (millisUntilFinished / 1000).toInt() }
                updateFormattedTime(remainingTime = _remainingTime.value)
            }

            override fun onFinish() {
                _isTimerZero.update { true }
                onTimeFinished()
                elapsedJob = scope.launch {
                    while (_isTimerZero.value) {
                        if (elapsedTime != 0) delay(1000)
                        elapsedTime += 1
                        updateFormattedTime(elapsedTime = elapsedTime)
                    }
                }
            }
        }

    private fun updateFormattedTime(remainingTime: Int = initialDuration, elapsedTime: Int = 0) {
        _remainingTimeFormatted.update { formatTime(remainingTime, TIME_FORMAT) }
        _elapsedTimeFormatted.update { formatTime(elapsedTime, "- $TIME_FORMAT") }
    }

    private fun formatTime(totalSeconds: Int, format: String) = totalSeconds.let {
        format.format(TimeUnit.SECONDS.toMinutes(it.toLong()), it % 60)
    }
}