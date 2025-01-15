package com.example.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.pages.favorites.FavoritesViewModel

@Composable
fun FavoriteCard(
    favorite: Favorite,
    favoritesViewModel: FavoritesViewModel,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .clickable { onClick(favorite.city) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "${favorite.city}, ${favorite.country}", modifier = Modifier.padding(start = 12.dp))

        IconButton(
            onClick = { favoritesViewModel.deleteFavorite(favorite) },

        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Favorite",
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}