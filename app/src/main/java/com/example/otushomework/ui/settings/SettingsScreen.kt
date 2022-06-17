package com.example.otushomework.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.otushomework.utils.Constants

@Composable
fun SettingsScreen(
    navController: NavHostController,
    topState: MutableState<Boolean>,
    bottomState: MutableState<Boolean>
) {
    topState.value = true
    bottomState.value = true
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item(content = {
            ShareItem(navController)

        })
    }
}

@Composable
private fun ShareItem(
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    }
    Button(onClick = { navController.navigate(Constants.SHARE_SCREEN) }) {
        Text(text = "Поделиться...")
    }
}
