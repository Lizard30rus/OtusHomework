package com.example.otushomework.data.repository

interface FilmsRepository {

    suspend fun updateFilms()


    fun getFilms()
}