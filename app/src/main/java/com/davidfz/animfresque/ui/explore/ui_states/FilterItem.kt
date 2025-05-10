package com.davidfz.animfresque.ui.explore.ui_states

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class FilterItem(
    val label: String = "",
    val icon: ImageVector = Icons.Default.Person,
    val onClick: () -> Unit = {}
)
