package com.example.otushomework.ui.filmscreen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.ui.theme.Yellow800
import com.example.otushomework.utils.Constants
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FilmListScreen(
    navController: NavHostController,
    topState : MutableState<Boolean>,
    bottomState : MutableState<Boolean>
) {
    topState.value = false
    bottomState.value = true
    FilmList(navController)
}

@Composable
fun FilmList(
    navController: NavHostController,
    viewModel: FilmListViewModel = hiltViewModel()
) {
    val filmList by viewModel.filmList.collectAsState()
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    Log.d("TAG", "${filmList.size}")
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        val itemCount = filmList.size + 1
        items(filmList.size) {
            if (it >= itemCount - 1 && !endReached) {
                viewModel.loadFilmsPaginated()
            }
            FilmItem(
                film = filmList[it],
                navController = navController,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.Blue)
        }
        if (loadError.isNotEmpty()) {
            RetryLoading(
                error = loadError
            ) {
                viewModel.loadFilmsPaginated()
            }
        }
    }
}

@Composable
fun FilmItem(
    film: FilmItemModel,
    navController: NavController,
    modifier: Modifier
) {
    val context = LocalContext.current
    val json = Uri.encode(Gson().toJson(film))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(color = Yellow800)
    ) {
        GlideImage(
            imageModel = film.imageFilm,
            modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            loading = {
                CircularProgressIndicator(
                    color = Color.Blue
                )
            },
            failure = {
                Text(
                    text = "image request failed.",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                )
            })
        Spacer(modifier = Modifier.width(12.dp))
        Column(Modifier.padding(top = 12.dp)) {
            Text(
                text = film.nameFilm,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = {
                    navController.navigate("${Constants.FILM_DETAIL_SCREEN}/$json")
                },
            ) {
                Text(
                    text = "Узнать больше...",
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Фильм добавлен в избранное!", Toast.LENGTH_SHORT)
                        .show()
                },
            ) {
                Text(
                    text = "В избранное!",
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun RetryLoading(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(
            text = error,
            color = Color.Red,
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Button(
            onClick = { onRetry() }
        ) {
            Text(text = "Retry")

        }

    }
}