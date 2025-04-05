package com.davidfz.animfresque.data

data class AnimationPhase(
    val name: String,
    var duration: Int,
    var remainingTime: Int = duration,
    var isPaused: Boolean = true
)