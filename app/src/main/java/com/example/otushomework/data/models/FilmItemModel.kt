package com.example.otushomework.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Модель фильма для записи данных в БД
 */
@Entity
data class FilmItemModel(
    val imageFilm : String,
    @PrimaryKey val id: Int,
    val nameFilm: String,
    val descriptionFilm : String,
    var isFavorite : Boolean = false
)


