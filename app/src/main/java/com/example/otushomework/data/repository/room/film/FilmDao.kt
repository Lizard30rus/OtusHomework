package com.example.otushomework.data.repository.room.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.otushomework.data.models.FilmItemModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFilms(films: List<FilmItemModel>)

    @Query("SELECT * FROM FilmItemModel")
    fun getFilms(): Flow<List<FilmItemModel>>

    @Query("SELECT * FROM filmitemmodel WHERE id=(:id)")
    fun getFilm(id: Int): Flow<FilmItemModel>

    @Query("SELECT * FROM FilmItemModel WHERE isFavorite=(:isFavorite)")
    fun getFavoriteFilms(isFavorite: Boolean): Flow<List<FilmItemModel>>
}