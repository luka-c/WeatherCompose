package com.example.weatherapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class Favorite(
    @PrimaryKey
    @ColumnInfo("city")
    val city: String,

    @ColumnInfo("country")
    val country: String
)
