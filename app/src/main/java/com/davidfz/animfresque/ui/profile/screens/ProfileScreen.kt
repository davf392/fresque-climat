package com.davidfz.animfresque.ui.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidfz.animfresque.R
import com.davidfz.animfresque.ui.navigation.Routes
import com.davidfz.animfresque.ui.profile.AnimatorProfile
import com.davidfz.animfresque.ui.profile.ProfileStat
import com.davidfz.animfresque.ui.profile.components.ProfileInfo
import com.davidfz.animfresque.ui.profile.components.ProfileStatItem


data class ProfileMenuItem(
    val title: String,
    val icon: Int
)

val profileMenuItems = listOf(
    ProfileMenuItem("Mes formations suivies", R.drawable.ic_launcher_background),
)
val profileStats = listOf(
    ProfileStat("Ateliers animés", 0, Icons.Default.Accessibility),
    ProfileStat("Formations animées", 0, Icons.Default.Accessibility),
    ProfileStat("Personnes sensibilisées", 0, Icons.Default.Person),
    ProfileStat("Personnes formées", 0, Icons.Default.Person)
)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Column(
        modifier = modifier.padding(8.dp).fillMaxSize()
    ) {
        ProfileInfo(
            profile = AnimatorProfile(
                firstName = "David",
                lastName = "Fz",
                homeCity = "Lyon, France",
                beltColor = Color.Yellow,
                beltColorName = "Jaune"
            ),
            onClickEditProfile = { navController.navigate(Routes.EDIT_PROFILE) }
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            items(profileStats.size) { index ->
                ProfileStatItem(profileStat = profileStats[index])
            }
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(modifier = Modifier.background(Color.White))
}