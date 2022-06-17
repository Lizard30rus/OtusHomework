package com.example.otushomework.ui.filmscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.otushomework.ui.filmscreen.views.FilmList
import com.example.otushomework.ui.theme.Red800

@Composable
fun FilmListScreen(
    navController: NavHostController,
    topState: MutableState<Boolean>,
    bottomState: MutableState<Boolean>
) {
    topState.value = true
    bottomState.value = true
    FilmList(navController)
}

@Composable
fun RetryLoading(
    error: MutableState<String>,
    onRetry: () -> Unit,
    networkError : MutableState<Boolean>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error.value,
            color = Red800,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                error.value = ""
                networkError.value = false
                onRetry()
            }
        ) {
            Text(text = "Retry")
        }
    }
}