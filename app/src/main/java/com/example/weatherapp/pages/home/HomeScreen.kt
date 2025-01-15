package com.example.weatherapp.pages.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.weatherapp.components.AppBar
import com.example.weatherapp.components.BottomBar
import com.example.weatherapp.components.DataView
import com.example.weatherapp.components.ForecastRow
import com.example.weatherapp.components.TopSection
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.pages.favorites.FavoritesViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    city: String
) {
    viewModel.getForecast(city)

    Scaffold(
        topBar = { AppBar("Weather App", navController, true) },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            HomeContent(city, viewModel, favoritesViewModel)
        }
    }
}


@Composable
private fun HomeContent(
    city: String,
    viewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel
) {
    DataView(
        isLoading = viewModel.isLoading,
        error = viewModel.error,
        errorAction = { viewModel.getForecast(city) }
    ) {
        val data = viewModel.forecast.collectAsStateWithLifecycle().value
        val favorites = favoritesViewModel.favorites.collectAsStateWithLifecycle().value
        val context = LocalContext.current

        val isFavorite = rememberSaveable {
            mutableStateOf(favorites.contains(Favorite(city, viewModel.forecast.value!!.city.country)))
        }

        if (data != null) {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopSection(
                            city = city,
                            dayForecast = data.list.first(),
                            isFavorite = isFavorite.value,
                            onFavoriteClick = {
                                isFavorite.value = !isFavorite.value
                                val country = viewModel.forecast.value!!.city.country

                                if (isFavorite.value) {
                                    favoritesViewModel.insertFavorite(Favorite(city, country))
                                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
                                }
                                else {
                                    favoritesViewModel.deleteFavorite(Favorite(city, country))
                                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }
                
                val otherDays = data.list.slice(IntRange(0, data.list.size - 1))
                
                itemsIndexed(otherDays) { index, day ->
                    ForecastRow(day = day, index = index + 1)
                }
            }
        }
    }
}