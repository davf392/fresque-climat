package com.davidfz.animfresque.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class ProfileSettingsUiItem(
    val label: String = "",
    val iconRes: ImageVector = Icons.Default.Person
)
