package com.example.weatherapp.pages.favorites

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.weatherapp.components.AppBar
import com.example.weatherapp.components.BottomBar
import com.example.weatherapp.components.FavoriteCard
import com.example.weatherapp.navigation.WeatherPages

@Composable
fun FavoritesScreen(navController: NavController, favoritesViewModel: FavoritesViewModel) {
    Scaffold(
        topBar = { AppBar(
            title = "Favorites",
            navController = navController
        ) },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            FavoritesContent(navController, favoritesViewModel)
        }
    }
}

@Composable
fun FavoritesContent(navController: NavController, favoritesViewModel: FavoritesViewModel) {
    val favorites = favoritesViewModel.favorites.collectAsStateWithLifecycle().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp),
    ) {
        item { Spacer(modifier = Modifier.height(12.dp)) }
        items(favorites) { favorite ->
            FavoriteCard(favorite, favoritesViewModel) {
                navController.navigate(route = WeatherPages.Home.name + "/$it")
            }
        }
    }
}
