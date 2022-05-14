package com.example.otushomework.data.repository.web

import com.example.otushomework.data.models.FilmItem
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {

 /*   @GET("f8126926-8bf7-4fad-8d12-7ce6f5223b11")
    fun updateFilms(): List<FilmItem>*/

  /*  @GET("8d057607-c2c1-41f6-985e-033e145c64b3")
    suspend fun updateFilms(): List<FilmItem>*/

   /* @GET("f6fab473-8411-4106-afb1-67f064f1bd10")*/
    @GET("46f7a916-6255-421e-8830-140569cbb9f4")
    suspend fun updateFilms(
       @Query("limit") limit : Int,
       @Query("offset") offset : Int,
    ): List<FilmItem>

}