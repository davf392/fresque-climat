package com.davidfz.animfresque.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.davidfz.animfresque.ui.home.HomeScreen
import com.davidfz.animfresque.ui.explore.screens.ExploreScreen
import com.davidfz.animfresque.ui.animate.screens.AnimateScreen
import com.davidfz.animfresque.ui.animate.viewmodel.AnimateViewModel
import com.davidfz.animfresque.ui.community.screens.CommunityScreen
import com.davidfz.animfresque.ui.navigation.BottomNavItem
import com.davidfz.animfresque.ui.navigation.Routes
import com.davidfz.animfresque.ui.navigation.rememberFreskNavController
import com.davidfz.animfresque.ui.profile.screens.EditProfileScreen
import com.davidfz.animfresque.ui.profile.screens.LoginScreen
import com.davidfz.animfresque.ui.profile.screens.ProfileScreen
import com.davidfz.animfresque.ui.theme.FreskTheme
import com.davidfz.animfresque.ui.theme.FresqueClimatColors


@Composable
fun FreskApp(
    modifier: Modifier = Modifier
) {
    FreskTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() { FreskTheme { FreskApp(Modifier.background(Color.White)) } }


@Composable
fun MainScreen() {
    val navController = rememberFreskNavController()
    val navBackStackEntry by navController.navController.currentBackStackEntryAsState(    )
    val animateViewModel: AnimateViewModel = viewModel()

    Scaffold(
        topBar = {
//            TopAppBar(
//                title = { Text("Fresque du Climat") },
//                navigationIcon = {
//                    if (currentRoute == Routes.EDIT_PROFILE) {
//                        IconButton(onClick = { navController.navController.popBackStack() }) {
//                            Icon(
//                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                                contentDescription = "Localized description"
//                            )
//                        }
//                    }
//                },
//            )
        },
        bottomBar = { BottomNavigationBar(navController.navController) }
    ) { innerPadding ->
        NavHost(
            navController.navController,
            startDestination = Routes.HOME,
            Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) { HomeScreen() }
            composable(Routes.COMMUNITY) { CommunityScreen() }
            composable(Routes.EXPLORE) { ExploreScreen() }
            composable(Routes.ANIMATE) {
                AnimateScreen(
                    viewModel = animateViewModel
                )
            }
            composable(Routes.LOGIN) {
                LoginScreen(
                    navController = navController.navController
                )
            }
            composable(Routes.PROFILE) {
                ProfileScreen(
                    navController = navController.navController
                )
            }
            composable(Routes.EDIT_PROFILE) {
                EditProfileScreen(
                    navController = navController.navController
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        BottomNavItem("Home", Routes.HOME, Icons.Default.Home),
        BottomNavItem("Community", Routes.COMMUNITY, Icons.Default.People),
        BottomNavItem("Explore", Routes.EXPLORE, Icons.Default.Search),
        BottomNavItem("Animate", Routes.ANIMATE, Icons.Default.Face),
        BottomNavItem("Profile", Routes.LOGIN, Icons.Default.AccountCircle)
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
                label = {
                    Text(text = item.name, fontSize = 10.sp)
                },
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
