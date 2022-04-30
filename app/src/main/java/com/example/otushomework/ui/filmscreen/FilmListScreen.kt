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
    Log.d("TAG", "${filmList.size}")
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(filmList.size) {
            FilmItem(
                film = filmList[it],
                navController = navController,
                modifier = Modifier
            )
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
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate("${Constants.FILM_DETAIL_SCREEN}/${film.nameFilm}")
            }) {
        GlideImage(
            imageModel = film.imageFilm,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            loading = {
                CircularProgressIndicator(
                    color = Color.Blue
                )
            },
        failure =  {
            Text(
                text = "image request failed.",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp))
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