package com.davidfz.animfresque.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Screens used in the [FreskApp].
 */
object Routes {
    const val HOME = "home"
    const val EXPLORE = "explore"
    const val ANIMATE = "animate"
    const val LOGIN = "login"
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "editProfile"
}

/**
 * Remembers and creates an instance of [FreskNavController]
 */
@Composable
fun rememberFreskNavController(
    navController: NavHostController = rememberNavController()
): FreskNavController = remember(navController) {
    FreskNavController(navController)
}

/**
 * Responsible for holding UI Navigation logic.
 */
@Stable
class FreskNavController(val navController: NavHostController)