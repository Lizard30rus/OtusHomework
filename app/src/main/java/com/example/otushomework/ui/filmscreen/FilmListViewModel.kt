package com.example.otushomework.ui.filmscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.data.repository.FilmsRepository
import com.example.otushomework.utils.Constants
import com.example.otushomework.utils.Constants.PAGE_SIZE
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

    private var curPage = 0

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadFilmsPaginated()
    }

    fun loadFilmsPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = filmsRepository.getFilmsFromWeb(PAGE_SIZE, curPage * Constants.PAGE_SIZE)
            when (result) {
                is Response.Succsess -> {
                    endReached.value = curPage * PAGE_SIZE >= 20
                    curPage++
                    loadError.value = ""
                    isLoading.value = false
                    _filmList.value = result.data
                }
                is Response.Error -> {
                    loadError.value = result.error.message.toString()

                }
            }
        }

    }

    /*init {
        viewModelScope.launch {
          //  updateFilms()
            val result = filmsRepository.getFilmsFromWeb()
            when (result) {
                is Response.Succsess -> {
                   _filmList.value = result.data
                }
                is Response.Error -> {
                    Log.e("Exception", "${result.error.message}", result.error)
                }
            }
        }

    }*/

    /*private suspend fun updateFilms() {
        try {
            Log.d("TAG", "now we in init viewmodel, update films!")
            filmsRepository.updateFilms()
        } catch (e: Exception) {
            println("ошибка при загрузке списка фильмов: ${e.message}")
        }
    }*/
}