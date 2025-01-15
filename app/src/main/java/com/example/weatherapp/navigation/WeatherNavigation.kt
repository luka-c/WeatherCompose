package com.example.weatherapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.pages.favorites.FavoritesScreen
import com.example.weatherapp.pages.favorites.FavoritesViewModel
import com.example.weatherapp.pages.home.HomeScreen
import com.example.weatherapp.pages.home.HomeViewModel
import com.example.weatherapp.pages.search.SearchScreen
import com.example.weatherapp.pages.settings.SettingsScreen
import com.example.weatherapp.pages.settings.SettingsViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherPages.Main.name,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) }
    ) {
        navigation(
            route = WeatherPages.Main.name,
            startDestination = WeatherPages.Home.name + "/Zagreb"
        ) {

            val home = WeatherPages.Home.name
            composable(
                route = "$home/{city}",
                exitTransition = {
                    slideOutHorizontally(targetOffsetX = { -it })
                },
                popEnterTransition = {
                    slideInHorizontally(initialOffsetX = { -it })
                },
                popExitTransition = {
                    slideOutHorizontally(targetOffsetX = { it })
                },
                arguments = listOf(
                    navArgument("city") {
                        type = NavType.StringType
                    }
                )
            ) {

                it.arguments?.getString("city").let { city ->
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
                    HomeScreen(
                        navController = navController,
                        viewModel = homeViewModel,
                        city = city!!,
                        favoritesViewModel = favoritesViewModel
                    )
                }

            }

            composable(
                route = WeatherPages.Search.name,
                enterTransition = {
                    slideInHorizontally(initialOffsetX = { it })
                },
                exitTransition = {
                    slideOutHorizontally(targetOffsetX = { -it })
                },
                popEnterTransition = {
                    slideInHorizontally(initialOffsetX = { -it })
                },
                popExitTransition = {
                    slideOutHorizontally(targetOffsetX = { it })
                }
            ) {
                SearchScreen(navController = navController)
            }
        }

        composable(route = WeatherPages.Favorite.name) {
            val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
            FavoritesScreen(navController = navController, favoritesViewModel = favoritesViewModel)
        }

        composable(route = WeatherPages.Settings.name) {
            val settingsViewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(navController = navController, viewModel = settingsViewModel)
        }
    }
}
