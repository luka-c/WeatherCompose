package com.example.weatherapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.weatherapp.utils.Constants

@Composable
fun WeatherImage(image: String) {
    AsyncImage(
        modifier = Modifier.size(40.dp),
        model = Constants.IMAGES_URL + image + ".png",
        contentDescription = "Weather Image",
        contentScale = ContentScale.Crop
    )
}