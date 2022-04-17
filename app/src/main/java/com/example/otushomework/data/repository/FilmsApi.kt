package com.example.otushomework.data.repository

import com.example.otushomework.data.models.FilmItem
import com.example.otushomework.data.models.FilmItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface FilmsApi {

    @GET("8d057607-c2c1-41f6-985e-033e145c64b3")
    fun getFilms() : Flow<List<FilmItem>>
}