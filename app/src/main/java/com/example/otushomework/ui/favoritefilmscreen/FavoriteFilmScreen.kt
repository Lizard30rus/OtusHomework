package com.example.otushomework.ui.favoritefilmscreen

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.otushomework.ui.theme.Teal200
import com.example.otushomework.utils.Constants
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun FavoriteFilmScreen(
    navController: NavHostController,
    topState: MutableState<Boolean>,
    bottomState: MutableState<Boolean>
) {
    topState.value = true
    bottomState.value = true
    FavoriteFilmList(navController)
}

@Composable
fun FavoriteFilmList(
    navController: NavHostController,
    viewModel: FavoriteFilmListViewModel = hiltViewModel()
) {
    val favoriteFilmList by viewModel.filmList.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(favoriteFilmList.size) {
            FavoriteFilmItem(
                film = favoriteFilmList[it],
                navController = navController,
                delete = {
                    coroutineScope.launch {
                        viewModel.deleteFromFavorites(favoriteFilmList[it])
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun FavoriteFilmItem(
    film: FilmItemModel,
    navController: NavController,
    delete: () -> Unit
) {
    val context = LocalContext.current
    val json = Uri.encode(Gson().toJson(film))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(color = Teal200)
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
                    delete()
                    Toast.makeText(context, "Фильм удален из избранного!", Toast.LENGTH_SHORT)
                        .show()
                },
            ) {
                Text(
                    text = "Удалить из избранного!",
                    color = Color.Black
                )
            }
        }
    }
}