package com.aneke.peter.kwg.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate

object MainDestinations {
    const val MAINSCREEN = "mainscreen"
    const val POSTVIEW = "postscreen"
    const val WEBVIEW = "webviewscreen"
}


class Navigation(navController: NavHostController) {

    val mainScreen : () -> Unit = {
        navController.navigate(MainDestinations.MAINSCREEN)
    }

    val webViewScreen : () -> Unit = {
        navController.navigate(MainDestinations.WEBVIEW)
    }

    val postsScreen : () -> Unit = {
        navController.navigate(MainDestinations.POSTVIEW)
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

//    val actions = remember(navController) { Navigation(navController) }
//
//    val navHost = NavHost(
//        navController = navController,
//        startDestination = MainDestinations.MAINSCREEN) {
//
//        composable()
//    }

}
