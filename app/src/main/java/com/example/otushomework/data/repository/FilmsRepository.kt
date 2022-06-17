package com.example.otushomework.data.repository

import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    /** Получение данных с back постранично, запись в базу данных Room */
    suspend fun getFilmsFromWeb(limit : Int, offset: Int): Response<List<FilmItemModel>>

    /** Получение списка фильмов с базы данных */
    suspend fun getFilmsFromRoom() : Response<Flow<List<FilmItemModel>>>

    /** Список фильмов из избранного */
    fun getFavoriteFilms() : Response<Flow<List<FilmItemModel>>>

    /** Добавить фильм в избранное */
    suspend fun addToFavorites(filmItemModel: FilmItemModel)

    /** Удалить фильм из избранного */
    suspend fun deleteFromFavorites(filmItemModel: FilmItemModel)
}