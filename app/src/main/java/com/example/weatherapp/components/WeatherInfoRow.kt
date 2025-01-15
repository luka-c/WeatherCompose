package com.example.weatherapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.DayForecast
import kotlin.math.roundToInt

@Composable
fun WeatherInfoRow(dayForecast: DayForecast) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Humidity", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Text(text = "${dayForecast.humidity}%")
        }

        Column {
            Text(text = "Pressure", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Text(text = "${dayForecast.pressure} psi")
        }

        Column {
            Text(text = "Wind speed", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Text(text = "${dayForecast.speed.roundToInt()} km/h")
        }
    }

    Spacer(modifier = Modifier.height(12.dp))
}