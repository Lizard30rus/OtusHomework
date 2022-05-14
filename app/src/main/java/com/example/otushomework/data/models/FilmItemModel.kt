package com.example.otushomework.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 * Модель фильма для записи данных в БД
 */
@Entity @Parcelize
data class FilmItemModel(
    val imageFilm: String,
    @PrimaryKey val id: Int,
    val nameFilm: String,
    val descriptionFilm: String,
    var isFavorite: Boolean = false
) : Parcelable


