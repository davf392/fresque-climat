package com.davidfz.animfresque.ui.animate

data class AnimationUiState(
    val animationPhases: List<AnimationPhaseUiState> = listOf(),
    val animationPlayState: AnimationPlayState = AnimationPlayState.STOPPED,
    val currentlyActiveTimer: Int = -1,
    val totalDurationInSec: Int = 0,
    val totalRemainingTimeInSec: Int = 0,
    val totalTimeFormatted: String = "00:00:00",
    val totalRemainingTimeFormatted: String = "00:00:00",
    val plannedEndTimeFormatted: String = ""
)