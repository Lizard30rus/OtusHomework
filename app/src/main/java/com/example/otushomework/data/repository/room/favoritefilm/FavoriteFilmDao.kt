package com.example.otushomework.data.repository.room.favoritefilm

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.otushomework.data.models.FilmItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFilmDao {
    @Query("SELECT * FROM FilmItemModel")
    fun getFavoriteFilms() : Flow<List<FilmItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(film : FilmItemModel)

    @Delete
    suspend fun deleteFromFavorites(film: FilmItemModel)
}