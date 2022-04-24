package com.example.otushomework.data.models

import com.google.gson.annotations.SerializedName

/**
 * Модель фильма при получении данных с back
 */
data class FilmItem(
    @SerializedName("poster_path") val imageFilm: String,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val nameFilm: String,
    @SerializedName("description") val descriptionFilm: String,
)
