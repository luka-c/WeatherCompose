package com.example.weatherapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.model.DayForecast
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.pages.favorites.FavoritesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun TopSection(
    city: String,
    dayForecast: DayForecast,
    isFavorite: Boolean,
    onFavoriteClick: (String) -> Unit = {}
) {
    val date = SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date())

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = city.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.headlineMedium)
            Text(text = date, style = MaterialTheme.typography.titleSmall)
        }

        IconButton(onClick = { onFavoriteClick(city) }) {
            Icon(
                imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = "Favorites Icon"
            )
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)
            .clip(RoundedCornerShape(7.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "${dayForecast.temp.day.roundToInt()}°",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 60.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Surface(
            color = MaterialTheme.colorScheme.surfaceTint,
            shape = CircleShape
        ) {
            Row(
                modifier = Modifier.padding(start = 10.dp, end = 23.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherImage(image = dayForecast.weather.first().icon)
                Text(
                    text = dayForecast.weather.first().main.uppercase(),
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        WeatherInfoRow(dayForecast = dayForecast)
    }

}