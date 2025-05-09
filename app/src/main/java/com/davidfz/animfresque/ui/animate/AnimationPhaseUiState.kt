package com.davidfz.animfresque.ui.animate

data class AnimationPhaseUiState(
    val name: String = "",
    val timer: CountDownTimer,
    var editedDuration: Int = timer.initialDuration,
    var showTimePicker: Boolean = false
)