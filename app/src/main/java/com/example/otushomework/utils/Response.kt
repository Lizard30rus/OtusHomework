package com.example.otushomework.utils

sealed class Response<T> {

    data class Succsess<T>(val data: T) : Response<T>()
    data class Error<T>(val error: Exception) : Response<T>()
    data class Loading<T>(val data :T? = null) : Response<T>()
}