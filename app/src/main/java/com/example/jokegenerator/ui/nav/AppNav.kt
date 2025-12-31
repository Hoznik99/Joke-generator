package com.example.jokegenerator.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jokegenerator.ui.screens.FavouritesScreen
import com.example.jokegenerator.ui.screens.HomeScreen

object Routes {
    const val HOME = "home"
    const val FAVOURITES = "favourites"
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onGoToFavourites = { navController.navigate(Routes.FAVOURITES) }
            )
        }
        composable(Routes.FAVOURITES) {
            FavouritesScreen(
                onGoToHome = { navController.navigate(Routes.HOME) }
            )
        }
    }
}
