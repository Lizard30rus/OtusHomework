package com.example.otushomework.data.repository

import com.example.otushomework.data.models.FilmItem
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    suspend fun updateFilms()

    suspend fun getFilmsFromWeb(limit : Int, offset: Int): Response<List<FilmItemModel>>

    fun getFilms(): Response<Flow<List<FilmItemModel>>>

    fun getFavoriteFilms(): Response<Flow<List<FilmItemModel>>>

    fun getDetailsFilm(id: Int): Response<Flow<FilmItemModel>>
}