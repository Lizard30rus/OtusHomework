package com.example.otushomework.data.repository.web

import com.example.otushomework.data.models.FilmItem
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {


    @GET("46f7a916-6255-421e-8830-140569cbb9f4")
    suspend fun updateFilms(
       @Query("limit") limit : Int,
       @Query("offset") offset : Int,
    ): List<FilmItem>

}