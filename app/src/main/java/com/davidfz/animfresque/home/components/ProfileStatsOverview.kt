package com.davidfz.animfresque.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.ui.theme.FresqueClimatColors
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProfileStatsOverview(
    modifier: Modifier = Modifier,
    facilitatorsCount: Int = 0,
    participantsCount: Int = 0
) {
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())

    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Animateur-rice-s
        Box(
            modifier = Modifier
            .weight(1f)
            .background(FresqueClimatColors.SecondaryVariant, RoundedCornerShape(12.dp))
            .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = numberFormat.format(facilitatorsCount),
                    fontSize = 32.sp,
                    color = FresqueClimatColors.Surface,
                )
                Text(
                    text = "Animateur-rice-s",
                    color = FresqueClimatColors.Surface
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        // Participant-e-s
        Box(
            modifier = Modifier
                .weight(1f)
                .background(FresqueClimatColors.SecondaryVariant, RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = numberFormat.format(participantsCount),
                    fontSize = 32.sp,
                    color = FresqueClimatColors.Surface
                )
                Text(
                    text = "Participant-e-s",
                    color = FresqueClimatColors.Surface
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileStatsOverviewPreview() {
    ProfileStatsOverview(
        modifier = Modifier.background(Color.White),
        facilitatorsCount = 407_839,
        participantsCount = 1_934_023
    )
}