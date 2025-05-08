package com.davidfz.animfresque.ui.animate

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit

const val REMAINING_TIME_TOTAL_FORMAT = "%02d:%02d:%02d"

open class AnimateViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AnimationUiState())
    open val uiState: StateFlow<AnimationUiState> = _uiState.asStateFlow()

    init {
        resetAnimation()
    }

    private fun resetAnimation() {
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

    private fun initPhasesList(): List<AnimationPhaseState> {
        return mutableStateListOf(
            AnimationPhaseState("Intro", CountDownTimer(15 * 60)),
            AnimationPhaseState("Lot 1", CountDownTimer(10 * 60)),
            AnimationPhaseState("Lot 2", CountDownTimer(15 * 60)),
            AnimationPhaseState("Lot 3", CountDownTimer(20 * 60)),
            AnimationPhaseState("Lot 4", CountDownTimer(15 * 60)),
            AnimationPhaseState("Lot 5", CountDownTimer(10 * 60)),
            AnimationPhaseState("Créativité", CountDownTimer(10 * 60)),
            AnimationPhaseState("Synthèse", CountDownTimer(5 * 60)),
            AnimationPhaseState("Quiz", CountDownTimer(15 * 60)),
            AnimationPhaseState("Émotions", CountDownTimer(15 * 60)),
            AnimationPhaseState("Débats", CountDownTimer(45 * 60)),
            AnimationPhaseState("Conclusion", CountDownTimer(10 * 60))
        )
    }
}