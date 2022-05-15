package com.example.otushomework.data.repository.room.favoritefilm

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.otushomework.data.models.FilmItemModel

@Database(entities = [FilmItemModel::class], version = 1)
abstract class FavoriteFilmDatabase: RoomDatabase() {
    abstract fun FavoriteFilmDao() : FavoriteFilmDao
}