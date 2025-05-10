package com.davidfz.animfresque.ui.animate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Stop
import androidx.compose.ui.graphics.vector.ImageVector

enum class AnimationPlayState(
    val isPlaying: Boolean,
    val iconAnimation: ImageVector,
    val iconPlayPause: ImageVector,
    val iconButtonText: String?
) {
    RESUMED(true, Icons.Filled.Stop, Icons.Filled.Pause, "Stop Animation"),
    PAUSED(true, Icons.Filled.Stop, Icons.Filled.PlayArrow, "Stop Animation"),
    STOPPED(false, Icons.Filled.PlayArrow, Icons.Filled.PlayCircle /* not used */, "Start Animation")
}