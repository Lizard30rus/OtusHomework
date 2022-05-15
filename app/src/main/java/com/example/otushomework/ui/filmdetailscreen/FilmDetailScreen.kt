package com.example.otushomework.ui.filmdetailscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.ui.theme.Shapes
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FilmDetailScreen(
    navHostController: NavHostController,
    filmItemModel: FilmItemModel?
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            imageModel = filmItemModel?.imageFilm,
            modifier = Modifier
                .clip(Shapes.small),
            contentScale = ContentScale.Crop,
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
            text = filmItemModel?.nameFilm ?: "default",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = filmItemModel?.descriptionFilm ?: "default",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}