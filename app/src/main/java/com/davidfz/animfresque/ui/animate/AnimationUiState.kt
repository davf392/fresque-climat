package com.davidfz.animfresque.ui.animate

data class AnimationUiState(
    val animationPhases: List<AnimationPhaseState> = listOf(),
    val totalTimeInSec: Int = 0,
    val totalTimeFormatted: String = "00:00:00"
)
