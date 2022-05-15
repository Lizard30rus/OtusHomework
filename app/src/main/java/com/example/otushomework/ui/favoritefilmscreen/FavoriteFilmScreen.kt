package com.example.otushomework.ui.favoritefilmscreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController

@Composable
fun FavoriteFilmScreen(
    navController: NavHostController,
    topState : MutableState<Boolean>,
    bottomState : MutableState<Boolean>
) {
    topState.value = false
    bottomState.value = true
    Text(text = "dfasdasdas")
}