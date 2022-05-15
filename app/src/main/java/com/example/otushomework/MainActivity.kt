package com.example.otushomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.ui.favoritefilmscreen.FavoriteFilmScreen
import com.example.otushomework.ui.filmdetailscreen.FilmDetailScreen
import com.example.otushomework.ui.filmscreen.FilmListScreen
import com.example.otushomework.ui.theme.OtusHomeworkTheme
import com.example.otushomework.ui.theme.Red500
import com.example.otushomework.utils.Constants
import com.example.otushomework.utils.createNavParams
import com.example.otushomework.utils.fromJson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtusHomeworkTheme {
                val bottomState = rememberSaveable { mutableStateOf(false) }
                val topState = rememberSaveable { mutableStateOf(false) }
                val navController = rememberNavController()
                OtusHomeworkScaffold(navController, bottomState, topState)
            }
        }
    }
}

@Composable
fun OtusHomeworkScaffold(
    navController: NavHostController,
    bottomState: MutableState<Boolean>,
    topState: MutableState<Boolean>
) {
    Scaffold(
        topBar = { OtusHomeworkTopBar(navController, topState) },
        bottomBar = { OtusHomeworkBottomBar(navController, bottomState) }
    ) {
        NavHost(navController, startDestination = Constants.FILMS_LIST_SCREEN) {
            composable(Constants.FILMS_LIST_SCREEN) {
                //bottomState.value = true
                FilmListScreen(navController, topState, bottomState)
            }
            composable(Constants.FAVORITE_FILMS_LIST_SCREEN) {
                //bottomState.value = true
                FavoriteFilmScreen(navController, topState, bottomState)
            }
            composable(
                "${Constants.FILM_DETAIL_SCREEN}/{${Constants.FILM_ITEM}}",
                arguments = listOf(
                    navArgument(Constants.FILM_ITEM) {
                        type = createNavParams<FilmItemModel>()
                    }
                )
            ) {
                val film = it.arguments?.getParcelable<FilmItemModel>(Constants.FILM_ITEM)
                bottomState.value = false
                topState.value = true
                FilmDetailScreen(navController, film)
            }
        }
    }
}


@Composable
fun OtusHomeworkTopBar(
    navController: NavHostController,
    topState: MutableState<Boolean>

) {
    AnimatedVisibility(
        visible = topState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        TopAppBar {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
            }
            Text(text = "Назад", color = Color.Black)
        }
    }

}

@Composable
fun OtusHomeworkBottomBar(
    navController: NavHostController,
    bottomState: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = bottomState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomAppBar {
            IconButton(onClick = { navController.navigate(Constants.FILMS_LIST_SCREEN) }) {
                Icon(
                    painter = painterResource(id = R.drawable.main_film_list),
                    contentDescription = "main_film_list",
                    tint = Red500
                )
            }
            Spacer(modifier = Modifier.weight(0.33f, true))
            IconButton(onClick = { navController.navigate(Constants.FAVORITE_FILMS_LIST_SCREEN) }) {
                Icon(
                    painter = painterResource(R.drawable.favorite_film_list),
                    contentDescription = "favorite_film_list",
                    tint = Red500
                )
            }
            Spacer(modifier = Modifier.weight(0.33f, true))
            IconButton(onClick = { navController.navigate(Constants.SHARE_SCREEN) }) {
                Icon(
                    painter = painterResource(R.drawable.share),
                    contentDescription = "share",
                    tint = Red500
                )
            }
        }
    }
}