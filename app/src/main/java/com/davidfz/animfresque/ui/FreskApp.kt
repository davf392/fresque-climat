package com.davidfz.animfresque.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.davidfz.animfresque.ui.explore.ExploreScreen
import com.davidfz.animfresque.ui.animate.screen.AnimateScreen
import com.davidfz.animfresque.ui.animate.viewmodel.AnimateViewModel
import com.davidfz.animfresque.ui.community.CommunityScreen
import com.davidfz.animfresque.ui.create.CreateScreen
import com.davidfz.animfresque.ui.navigation.BottomNavItem
import com.davidfz.animfresque.ui.navigation.Routes
import com.davidfz.animfresque.ui.navigation.rememberFreskNavController
import com.davidfz.animfresque.ui.profile.EditProfileScreen
import com.davidfz.animfresque.ui.profile.ProfileScreen
import com.davidfz.animfresque.ui.theme.FreskTheme
import com.davidfz.animfresque.ui.theme.FresqueClimatColors


@Composable
fun FreskApp() {
    FreskTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() { FreskTheme { FreskApp() } }

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun DefaultPreviewNight() { FreskTheme { FreskApp() } }

@Composable
fun MainScreen() {
    val freskNavController = rememberFreskNavController()
    val animateViewModel: AnimateViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomNavigationBar(freskNavController.navController) }
    ) { innerPadding ->
        NavHost(
            freskNavController.navController,
            startDestination = Routes.HOME,
            Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) { CommunityScreen() }
            composable(Routes.EXPLORE) { ExploreScreen() }
            composable(Routes.CREATE) { CreateScreen() }
            composable(Routes.ANIMATE) { AnimateScreen(viewModel = animateViewModel) }
            composable(Routes.PROFILE) { ProfileScreen(freskNavController.navController) }
            composable(Routes.EDIT_PROFILE) { EditProfileScreen(freskNavController.navController) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        BottomNavItem("Home", Routes.HOME, Icons.Default.Home),
        BottomNavItem("Explore", Routes.EXPLORE, Icons.Default.Search),
        BottomNavItem("Create", Routes.CREATE, Icons.Default.Add),
        BottomNavItem("Animate", Routes.ANIMATE, Icons.Default.Face),
        BottomNavItem("Profile", Routes.PROFILE, Icons.Default.AccountCircle)
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navItems.forEach { item ->
            NavigationBarItem (
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        tint = if (currentRoute == item.route)
                            FresqueClimatColors.Primary
                        else
                            FresqueClimatColors.Secondary
                    )
                },
                label = { Text(item.name) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
