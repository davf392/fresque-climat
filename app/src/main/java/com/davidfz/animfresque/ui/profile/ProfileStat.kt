package com.davidfz.animfresque.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class ProfileStat(
    val label: String = "",
    val value: Int = 0,
    val iconRes: ImageVector = Icons.Default.Person
)
