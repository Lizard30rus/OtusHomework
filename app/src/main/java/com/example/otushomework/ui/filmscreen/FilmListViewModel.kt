package com.example.otushomework.ui.filmscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.data.repository.FilmsRepository
import com.example.otushomework.utils.Constants.PAGE_SIZE
import com.example.otushomework.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _filmList = MutableStateFlow<List<FilmItemModel>>(listOf())
    val filmList: StateFlow<List<FilmItemModel>> = _filmList

    private var curPage = 0

    val loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val endReached = mutableStateOf(false)
    val networkError = mutableStateOf(false)

    init {
        loadFilmsPaginated()
    }

    fun loadFilmsPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = filmsRepository.getFilmsFromWeb(PAGE_SIZE, curPage * PAGE_SIZE)) {
                is Response.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= 20
                    curPage++
                    loadError.value = ""
                    isLoading.value = false
                    _filmList.value = result.data
                }
                is Response.Error -> {
                    isLoading.value = false
                    loadError.value = result.error.message.toString()
                }
            }
        }
    }
    suspend fun addToFavorites(filmItemModel: FilmItemModel) {
        filmsRepository.addToFavorites(filmItemModel)
    }
}