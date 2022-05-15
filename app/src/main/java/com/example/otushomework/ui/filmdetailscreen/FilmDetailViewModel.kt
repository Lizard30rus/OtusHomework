package com.example.otushomework.ui.filmdetailscreen

/*

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.data.repository.FilmsRepository
import com.example.otushomework.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _filmDetail = MutableStateFlow(
        FilmItemModel(
            imageFilm = "",
            nameFilm = "empty name",
            descriptionFilm = "empty description",
            id = 0
        )
    )
    val filmDetail: StateFlow<FilmItemModel> = _filmDetail


     fun getFilmDetail(id: Int): FilmItemModel {
         viewModelScope.launch {
             Log.d("TAG", "now we in detailviewModel, id: $id")
             val result = filmsRepository.getDetailsFilm(id)
             when (result) {
                 is Response.Succsess -> {
                     result.data.collect {
                         _filmDetail.value = it
                         Log.d("TAG", "now we in getFilmDetail, result from room database: ${_filmDetail.value.nameFilm}")
                     }
                 }
                 is Response.Error -> {
                     println(result.error.message)
                 }
             }
             return@launch
         }
         return _filmDetail.value
    }
}*/
