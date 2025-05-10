package com.davidfz.animfresque.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BeltColorIndicator(beltColor: Color, beltName: String) {
    val lighterBeltColor = beltColor.copy(alpha = 0.5f)

    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(lighterBeltColor)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Text(
                text = "Ceinture $beltName",
                color = Color.Black,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun BeltColorIndicatorPreview() {
    BeltColorIndicator(Color.Yellow, "Jaune")
}