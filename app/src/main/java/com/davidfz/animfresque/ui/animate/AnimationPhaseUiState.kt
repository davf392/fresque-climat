package com.davidfz.animfresque.ui.animate

data class AnimationPhaseUiState(
    val name: String = "",
    val timer: CountDownTimer,
    var editedDuration: Int = timer.initialDuration,
    var showTimePicker: Boolean = false,
    var isAnimationStarted: Boolean = false
) {
    fun setNewDuration(minutes: Int, seconds: Int) {
        editedDuration = (minutes * 60) + seconds
        timer.initialDuration = editedDuration
        timer.reset()
        showTimePicker = false
    }
}