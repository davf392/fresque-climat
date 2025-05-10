package com.davidfz.animfresque.ui.animate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.ui.theme.FresqueClimatColors.Primary
import com.davidfz.animfresque.ui.theme.FresqueClimatColors.Secondary


@Composable
fun AnimationTotalProgressBar(
    modifier: Modifier = Modifier,
    height: Dp = 55.dp,
    totalAnimationTime: Int = 0,
    remainingAnimationTime: Int = 0,
    remainingAnimationFormattedTime: String = "00:00:00",
    plannedEndTimeFormatted: String = "00:00:00",
    totalTimeFormatted: String = "00:00:00",
    animationIsStarted: Boolean = false
) {
    val percentageAnimation = when {
        totalAnimationTime > 0 && animationIsStarted -> remainingAnimationTime.toFloat() / totalAnimationTime
        totalAnimationTime > 0 -> 1f
        else -> 0f
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .clip(RoundedCornerShape(15.dp))
                .height(height)
                .background(Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .height(height)
                    .background(Brush.horizontalGradient(listOf(Color(Primary.value), Color(Secondary.value))))
                    .fillMaxWidth(fraction = percentageAnimation.coerceIn(0f, 1f))
            )
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (animationIsStarted) remainingAnimationFormattedTime else totalTimeFormatted,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = if (animationIsStarted) plannedEndTimeFormatted else "Fin de l'atelier à $plannedEndTimeFormatted",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun ProgressBarPreview() {
    AnimationTotalProgressBar(
        totalAnimationTime = 10800,
        remainingAnimationTime = 2500,
        remainingAnimationFormattedTime = "00:45:34",
        plannedEndTimeFormatted = "03:00:00",
        animationIsStarted = true
    )
}