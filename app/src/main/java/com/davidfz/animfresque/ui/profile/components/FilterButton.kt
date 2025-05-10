package com.davidfz.animfresque.ui.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidfz.animfresque.ui.explore.ui_states.FilterItem
import com.davidfz.animfresque.ui.theme.FresqueClimatColors

@Composable
fun FilterButton(
    filter: FilterItem = FilterItem()
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = filter.onClick,
        colors = ButtonColors(
            contentColor = FresqueClimatColors.Surface,
            containerColor = FresqueClimatColors.Primary,
            disabledContainerColor = FresqueClimatColors.Surface,
            disabledContentColor = Color.DarkGray
        )
    ) {
        Icon(
            imageVector = filter.icon,
            contentDescription = filter.label,
            modifier = Modifier.padding(end = 4.dp).size(16.dp)
        )
        Text(filter.label.uppercase())
    }
}

@Preview
@Composable
fun FilterButtonPreview() {
    FilterButton(
        FilterItem(
            onClick = {},
            icon = Icons.Default.Map,
            label = "Location"
        )
    )
}