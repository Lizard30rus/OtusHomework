package com.example.otushomework.ui.filmscreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.otushomework.R
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.utils.Constants
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FilmListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.main_film_list),
                            contentDescription = "main_film_list"
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.33f, true))
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.favorite_film_list),
                            contentDescription = "favorite_film_list"
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.33f, true))
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.share),
                            contentDescription = "share"
                        )
                    }
                }
            }) {
            FilmList(navController = navController)
        }
    }
}

@Composable
fun FilmList(
    navController: NavController,
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                navController.navigate("${Constants.FILM_DETAIL_SCREEN}/${film.id}")
            }) {
        GlideImage(
            imageModel = film.imageFilm,
            modifier = Modifier
                .height(150.dp)
                .width(100.dp),
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
        Text(
            text = film.nameFilm,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { },
        ) {

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
            onClick = { onRetry }
        ) {
            Text(text = "Retry")

        }

    }
}