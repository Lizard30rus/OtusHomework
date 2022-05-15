package com.example.otushomework.ui.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.otushomework.utils.Constants

@Composable
fun ShareScreen(
    navController: NavHostController,
    topState: MutableState<Boolean>,
    bottomState: MutableState<Boolean>
) {
    topState.value = true
    bottomState.value = false
    val context = LocalContext.current
    var value by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = value,
            onValueChange = { value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            Toast.makeText(context, "Приглашение отправлено!", Toast.LENGTH_SHORT).show()
            navController.navigate(Constants.FILMS_LIST_SCREEN)
        }) {
            Text(text = "Пригласить")
        }
    }

}