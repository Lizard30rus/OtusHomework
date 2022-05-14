package com.example.otushomework.ui.filmdetailscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.otushomework.ui.theme.Shapes
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FilmDetailScreen(
    id: Int,
    viewModel: FilmDetailViewModel = hiltViewModel()
) {
    val filmDetail = viewModel.getFilmDetail(id)
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            imageModel = filmDetail.imageFilm,
            modifier = Modifier
                .clip(Shapes.small),
            contentScale = ContentScale.Fit,
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
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = filmDetail.nameFilm,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = filmDetail.descriptionFilm,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}