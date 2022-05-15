package com.example.otushomework.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.otushomework.utils.Constants

@Composable
fun SettingsScreen(
    navController: NavHostController,
    topState: MutableState<Boolean>,
    bottomState: MutableState<Boolean>
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item( content = {
            ShareItem(navController, bottomState, topState)

        })
    }
}

@Composable
private fun ShareItem(
    navController: NavHostController,
    bottomState: MutableState<Boolean>,
    topState: MutableState<Boolean>
) {
   Text(text = "Поделиться...",
   modifier = Modifier.clickable { navController.navigate(Constants.SHARE_SCREEN) })
}
