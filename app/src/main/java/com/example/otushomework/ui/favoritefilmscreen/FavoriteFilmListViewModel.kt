package com.example.otushomework.ui.favoritefilmscreen

import android.util.Log
import androidx.compose.runtime.collectAsState
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
class FavoriteFilmListViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _filmList = MutableStateFlow<List<FilmItemModel>>(listOf())
    val filmList: StateFlow<List<FilmItemModel>> = _filmList

    init {
        viewModelScope.launch {
            val result = filmsRepository.getFavoriteFilms()
            when (result) {
                is Response.Success -> {
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

    suspend fun deleteFromFavorites(filmItemModel: FilmItemModel) {
        filmsRepository.deleteFromFavorites(filmItemModel)
    }
}