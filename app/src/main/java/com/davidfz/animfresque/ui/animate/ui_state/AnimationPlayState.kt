package com.davidfz.animfresque.ui.animate.ui_state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Stop
import androidx.compose.ui.graphics.vector.ImageVector
import com.davidfz.animfresque.R

enum class AnimationPlayState(
    val isPlaying: Boolean,
    val iconAnimation: ImageVector,
    val iconPlayPause: ImageVector,
    val iconButtonText: Int
) {
    RESUMED(true, Icons.Filled.Stop, Icons.Filled.Pause, R.string.stop_animation),
    PAUSED(true, Icons.Filled.Stop, Icons.Filled.PlayArrow, R.string.stop_animation),
    STOPPED(false, Icons.Filled.PlayArrow, Icons.Filled.PlayCircle /* not used */, R.string.start_animation)
}