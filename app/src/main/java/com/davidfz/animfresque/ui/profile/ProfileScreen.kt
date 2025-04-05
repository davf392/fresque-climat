package com.davidfz.animfresque.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidfz.animfresque.R
import com.davidfz.animfresque.ui.navigation.Routes

data class Profile(
    val firstName: String,
    val lastName: String,
    val homeCity: String,
    val beltColor: Color,
    val beltColorName: String
)

data class ProfileMenuItem(
    val title: String,
    val icon: Int
)

// Sample profile data
val profile = Profile("David", "Fz", "Lyon, France", Color.Yellow, "Jaune")
val profileMenuItems = listOf(
    ProfileMenuItem("Mes formations suivies", R.drawable.ic_launcher_background),
)
val profileStats = listOf(
    ProfileStat("Ateliers\nanimés", 0, Icons.Default.AccountBox),
    ProfileStat("Personnes\nsensibilisées", 0, Icons.Default.Person),
    ProfileStat("Formations\nanimées", 0, Icons.Default.AccountBox),
    ProfileStat("Personnes\nformées", 0, Icons.Default.Person)
)

@Composable
fun ProfileScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.padding(8.dp).fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Routes.EDIT_PROFILE) }
                ) {
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(58.dp))
//                            .border(1.dp, Color.Black)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    ProfileInfo(profile = profile)
                }
                Spacer(modifier = Modifier.width(12.dp))
                ProfileStats(profileStats)
            }
        }
    }
}

@Composable
fun ProfileInfo(profile: Profile) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "${profile.firstName} ${profile.lastName}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(text = profile.homeCity)
        BeltColorIndicator(profile.beltColor, profile.beltColorName)
    }
}

@Composable
fun BeltColorIndicator(beltColor: Color, beltName: String) {
    val lighterBeltColor = beltColor.copy(alpha = 0.2f)

    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(lighterBeltColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Text(
                text = beltName,
                color = Color.Black,
                modifier = Modifier.padding(4.dp)
            )
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black, CircleShape)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(beltColor)
                    .shadow(
                        elevation = 1.dp,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun ProfileStats(stats: List<ProfileStat>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp)
        ) {
        items(stats) { stat ->
            Card(
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stat.label,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stat.value.toString(),
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}