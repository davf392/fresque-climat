package com.davidfz.animfresque.ui.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidfz.animfresque.R
import com.davidfz.animfresque.ui.navigation.Routes
import com.davidfz.animfresque.ui.profile.AnimatorProfile
import com.davidfz.animfresque.ui.profile.ProfileSettingsUiItem
import com.davidfz.animfresque.ui.profile.components.ProfileInfo
import com.davidfz.animfresque.ui.profile.components.ProfileSettingsItem


val profileItems = listOf(
    ProfileSettingsUiItem("Remplir son compteur", Icons.Default.Add),
    ProfileSettingsUiItem("Mes sessions à venir", Icons.Default.CalendarMonth),
    ProfileSettingsUiItem("Mes sessions passées", Icons.Default.History),
    ProfileSettingsUiItem("Mon parcours d'animateur", Icons.Default.CoPresent)
)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        ProfileInfo(
            profile = AnimatorProfile(
                firstName = "John",
                lastName = "Doe",
                homeCity = "Paris, France",
                beltColor = Color.Yellow,
                beltColorName = "Jaune"
            ),
            onClickEditProfile = { navController.navigate(Routes.EDIT_PROFILE) }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            items(profileItems.size) { index ->
                ProfileSettingsItem(item = profileItems[index])
                HorizontalDivider()
            }
        }

        ElevatedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(50.dp),
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
        ) {
            Text(
                text = stringResource(R.string.profile_logout),
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(modifier = Modifier.background(Color.White))
}