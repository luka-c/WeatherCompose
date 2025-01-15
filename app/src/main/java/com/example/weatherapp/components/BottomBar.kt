package com.example.weatherapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherPages

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = navController.currentDestination?.route!!.contains(WeatherPages.Home.name),
            onClick = { navController.navigate(WeatherPages.Main.name) },
            icon = { Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home Icon") },
            label = { Text(text = "Home") }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == WeatherPages.Favorite.name,
            onClick = { navController.navigate(WeatherPages.Favorite.name) },
            icon = { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = "Favorites Icon") },
            label = { Text(text = "Favorites") }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == WeatherPages.Settings.name,
            onClick = { navController.navigate(WeatherPages.Settings.name) },
            icon = { Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Settings Icon") },
            label = { Text(text = "Settings") }
        )
    }
}