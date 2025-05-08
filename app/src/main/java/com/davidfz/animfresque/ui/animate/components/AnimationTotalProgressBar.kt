package com.davidfz.animfresque.ui.animate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.ui.theme.FresqueClimatColors.Primary
import com.davidfz.animfresque.ui.theme.FresqueClimatColors.Secondary


@Composable
fun AnimationTotalProgressBar(
    totalAnimationTime: Int = 0,
    remainingAnimationTime: Int = 0,
    remainingAnimationFormattedTime: String = "00:00:00"
) {
    val percentageAnimation = if (totalAnimationTime > 0)
        remainingAnimationTime.toFloat() / totalAnimationTime
    else 0f

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clip(RoundedCornerShape(15.dp))
                .height(30.dp)
                .background(Color.Gray)
                .width(400.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .height(30.dp)
                    .background(Brush.horizontalGradient(listOf(Color(Primary.value), Color(Secondary.value))))
                    .fillMaxWidth(fraction = percentageAnimation.coerceIn(0f, 1f))
            )
            Text(
                text = remainingAnimationFormattedTime,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun ProgressBarPreview() {
    AnimationTotalProgressBar(
        totalAnimationTime = 10800,
        remainingAnimationTime = 8000,
        remainingAnimationFormattedTime = "02:45:34"
    )
}