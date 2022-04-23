package com.example.otushomework.data.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.otushomework.data.models.FilmItemModel

@Database (entities = [FilmItemModel::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun FilmDao() : FilmDao
}
