package com.example.otushomework.ui.filmscreen.views

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.otushomework.ui.filmscreen.FilmListViewModel
import com.example.otushomework.ui.filmscreen.RetryLoading
import kotlinx.coroutines.launch

@Composable
fun FilmList(
    navController: NavHostController,
    viewModel: FilmListViewModel = hiltViewModel()
) {
    val filmList by viewModel.filmList.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        val itemCount = filmList.size + 1
        items(filmList.size) {
            if (it >= itemCount - 1 && !viewModel.endReached.value) {
                viewModel.loadFilmsPaginated()
            }
            FilmItem(
                film = filmList[it],
                navController = navController,
                add = {
                    coroutineScope.launch {
                        viewModel.addToFavorites(filmList[it])
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (viewModel.isLoading.value && viewModel.loadError.value.isEmpty()) {
            CircularProgressIndicator(color = Color.Blue)
        }
        if (viewModel.loadError.value.isNotEmpty()) {
            Log.d("TAG", "viewModel.loadError.value.isNotEmpty()")
            RetryLoading(
                error = viewModel.loadError,
                onRetry = viewModel::loadFilmsPaginated
            )
        }
    }
}