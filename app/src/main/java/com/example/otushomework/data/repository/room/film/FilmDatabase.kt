package com.example.otushomework.data.repository.room.film

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.data.repository.room.film.FilmDao

@Database(entities = [FilmItemModel::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun FilmDao(): FilmDao
}
