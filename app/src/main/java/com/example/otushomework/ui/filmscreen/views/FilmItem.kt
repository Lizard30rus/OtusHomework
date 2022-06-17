package com.example.otushomework.ui.filmscreen.views

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.ui.theme.Teal200
import com.example.otushomework.utils.Constants
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FilmItem(
    isLoading: Boolean,
    film: FilmItemModel,
    navController: NavController,
    add: () -> Unit
) {
    val context = LocalContext.current
    val json = Uri.encode(Gson().toJson(film))
    if (isLoading) {
        CircularProgressIndicator(color = Color.Blue)
    } else {
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
                        add()
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
}