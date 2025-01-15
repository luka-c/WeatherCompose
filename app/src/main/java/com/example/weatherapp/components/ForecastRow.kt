package com.example.weatherapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.model.DayForecast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun ForecastRow(day: DayForecast, index: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 12.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // DAY OF THE WEEK
            val today = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("EEE")
            Text(text = today.plusDays(index.toLong()).format(formatter), fontSize = 18.sp)

            // WEATHER DESCRIPTION
            Surface(
                modifier = Modifier.width(130.dp),
                color = MaterialTheme.colorScheme.surfaceTint,
                shape = CircleShape
            ) {
                Row(
                    modifier = Modifier.padding(start = 10.dp, end = 23.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    WeatherImage(image = day.weather.first().icon)
                    Text(text = day.weather.first().main.uppercase())
                }
            }

            // TEMPERATURES
            Row {
                Text(
                    text = "${day.temp.max.roundToInt()}°",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "${day.temp.min.roundToInt()}°",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }

        HorizontalDivider()
    }
}