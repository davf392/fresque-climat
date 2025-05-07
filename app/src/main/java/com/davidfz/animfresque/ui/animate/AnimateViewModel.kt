package com.davidfz.animfresque.ui.animate

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnimateViewModel: ViewModel() {

    private val _animationPhases = MutableStateFlow(initPhasesList())
    val animationPhases: StateFlow<List<AnimationPhaseState>> = _animationPhases.asStateFlow()

    private val _totalTime = MutableStateFlow(0)
    val totalTime: StateFlow<Int> = _totalTime.asStateFlow()

    init {
        computeTotalTime()
    }

    private fun computeTotalTime() {
        _totalTime.value = _animationPhases.value.sumOf { it.editedDuration } / 60
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