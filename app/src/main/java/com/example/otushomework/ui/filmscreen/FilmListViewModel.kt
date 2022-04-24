package com.example.otushomework.ui.filmscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.data.repository.FilmsRepository
import com.example.otushomework.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _filmList = MutableStateFlow<List<FilmItemModel>>(listOf())
    val filmList: StateFlow<List<FilmItemModel>> = _filmList

    init {
        viewModelScope.launch {
            updateFilms()
            val result = filmsRepository.getFilms()
            when (result) {
                is Response.Succsess -> {
                    result.data.collect {
                     _filmList.value = it
                    }
                }
                is Response.Error -> {
                    Log.e("Exception", "${result.error.message}", result.error)
                }
            }
        }

    }

    private suspend fun updateFilms() {
        try {
            Log.d("TAG", "now we in init viewmodel, update films!")
            filmsRepository.updateFilms()
        } catch (e: Exception) {
            println("ошибка при загрузке списка фильмов: ${e.message}")
        }
    }
}