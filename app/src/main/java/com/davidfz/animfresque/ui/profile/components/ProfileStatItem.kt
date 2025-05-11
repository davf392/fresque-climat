package com.davidfz.animfresque.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.ui.profile.ProfileStat


@Composable
fun ProfileStatItem(
    modifier: Modifier = Modifier,
    profileStat: ProfileStat = ProfileStat(),
) {
    Card(
        modifier = modifier.padding(8.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = profileStat.label,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f).padding(start = 8.dp).align(Alignment.CenterVertically)
            )
            Text(
                text = profileStat.value.toString(),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp).align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun ProfileStatsListPreview() {
    ProfileStatItem(
        modifier = Modifier.background(Color.White),
        profileStat = ProfileStat("Ateliers animés", 15))
}