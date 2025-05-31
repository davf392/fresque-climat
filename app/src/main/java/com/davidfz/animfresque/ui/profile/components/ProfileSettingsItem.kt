package com.davidfz.animfresque.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidfz.animfresque.ui.profile.ProfileSettingsUiItem
import com.davidfz.animfresque.ui.theme.FresqueClimatColors


@Composable
fun ProfileSettingsItem(
    modifier: Modifier = Modifier,
    item: ProfileSettingsUiItem = ProfileSettingsUiItem(),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = {})
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.iconRes,
            contentDescription = null,
            tint = FresqueClimatColors.Primary,
            modifier = Modifier.size(24.dp)
        )
        Column(
            modifier = modifier.weight(1f).fillMaxWidth()
        ) {
            Text(
                text = item.label,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Aller à l'écran suivant",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun ProfileSettingsListPreview() {
    ProfileSettingsItem(
        modifier = Modifier.background(Color.White),
        item = ProfileSettingsUiItem("Ateliers animés", Icons.Default.Person)
    )
}