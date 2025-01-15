package com.example.weatherapp.pages.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import com.example.weatherapp.components.AppBar
import com.example.weatherapp.components.BottomBar
import com.example.weatherapp.components.SearchBar
import com.example.weatherapp.navigation.WeatherPages

@Composable
fun SearchScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppBar(
            title = "Search",
            navController = navController,
            showNavigation = true
        ) },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            SearchContent(navController)
        }
    }
}

@Composable
fun SearchContent(navController: NavHostController) {
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(searchQuery) {
            keyboardController?.hide()
            focusManager.clearFocus()

            if (searchQuery.value.isNotEmpty())
                navController.navigate(WeatherPages.Home.name + "/${searchQuery.value}") {
                    popUpTo(WeatherPages.Home.name + "/{city") { inclusive = true }
                }
        }
    }
}