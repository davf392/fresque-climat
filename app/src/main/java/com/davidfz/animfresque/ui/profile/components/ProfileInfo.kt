package com.davidfz.animfresque.ui.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.ui.profile.AnimatorProfile
import com.davidfz.animfresque.ui.theme.FresqueClimatColors

@Composable
fun ProfileInfo(
    modifier: Modifier = Modifier,
    profile: AnimatorProfile = AnimatorProfile(),
    onClickEditProfile: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable { onClickEditProfile() }
            ) {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                        .clip(RoundedCornerShape(58.dp))
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "${profile.firstName} ${profile.lastName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = profile.homeCity,
                        fontSize = 16.sp
                    )
                    BeltColorIndicator(profile.beltColor, profile.beltColorName)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }

}


@Composable
@Preview
fun ProfileInfoPreview() {
    ProfileInfo(
        modifier = Modifier.background(Color.White),
        profile = AnimatorProfile(
            firstName = "David",
            lastName = "Fourdrigniez",
            homeCity = "Alès",
            beltColor = Color.Yellow,
            beltColorName = "Jaune"
        )
    )
}