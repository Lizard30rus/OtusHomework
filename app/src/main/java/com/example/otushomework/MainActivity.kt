package com.example.otushomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.ui.favoritefilmscreen.FavoriteFilmScreen
import com.example.otushomework.ui.filmdetailscreen.FilmDetailScreen
import com.example.otushomework.ui.filmscreen.FilmListScreen
import com.example.otushomework.ui.settings.SettingsScreen
import com.example.otushomework.ui.settings.ShareScreen
import com.example.otushomework.ui.theme.OtusHomeworkTheme
import com.example.otushomework.utils.Constants.FAVORITE_FILMS_LIST_SCREEN
import com.example.otushomework.utils.Constants.FILMS_LIST_SCREEN
import com.example.otushomework.utils.Constants.FILM_DETAIL_SCREEN
import com.example.otushomework.utils.Constants.FILM_ITEM
import com.example.otushomework.utils.Constants.SETTINGS_SCREEN
import com.example.otushomework.utils.Constants.SHARE_SCREEN
import com.example.otushomework.utils.createNavParams
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtusHomeworkTheme {
                val bottomState = rememberSaveable { mutableStateOf(false) }
                val topState = rememberSaveable { mutableStateOf(true) }
                val iconBack = rememberSaveable { mutableStateOf(false) }
                val navController = rememberNavController()
                OtusHomeworkScaffold(navController, bottomState, topState, iconBack)
            }
        }
    }
}

@Composable
fun OtusHomeworkScaffold(
    navController: NavHostController,
    bottomState: MutableState<Boolean>,
    topState: MutableState<Boolean>,
    iconBack: MutableState<Boolean>
) {
    Scaffold(
        topBar = { OtusHomeworkTopBar(navController, topState, iconBack) },
        bottomBar = { OtusHomeworkBottomBar(navController, bottomState) }
    ) {
        NavHost(navController, startDestination = FILMS_LIST_SCREEN) {
            composable(FILMS_LIST_SCREEN) {
                FilmListScreen(navController, topState, bottomState)
            }
            composable(FAVORITE_FILMS_LIST_SCREEN) {
                FavoriteFilmScreen(navController, topState, bottomState)
            }
            composable(
                "${FILM_DETAIL_SCREEN}/{${FILM_ITEM}}",
                arguments = listOf(
                    navArgument(FILM_ITEM) {
                        type = createNavParams<FilmItemModel>()
                    }
                )
            ) {
                val film = it.arguments?.getParcelable<FilmItemModel>(FILM_ITEM)
                bottomState.value = false
                topState.value = true
                FilmDetailScreen(film)
            }
            composable(SETTINGS_SCREEN) {
                SettingsScreen(navController, topState, bottomState)
            }
            composable(SHARE_SCREEN) {
                ShareScreen(navController, topState, bottomState)
            }
        }
    }
}


@Composable
fun OtusHomeworkTopBar(
    navController: NavHostController,
    topState: MutableState<Boolean>,
    iconBack: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = topState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        // Заголовок верхнего меню
        val title = rememberSaveable { mutableStateOf("") }
        LaunchedEffect(navController) {
            navController.currentBackStackEntryFlow.collect {
                title.value = topAppBarTitle(it.destination.route, iconBack)
            }
        }
        TopAppBar {
            if (iconBack.value) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
            } else {
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(
                text = title.value,
                color = Color.White
            )
        }
    }
}

fun topAppBarTitle(route: String?, iconBack: MutableState<Boolean>): String {

    return when (route) {
        FILMS_LIST_SCREEN -> {
            iconBack.value = false
            "Все фильмы"
        }
        FAVORITE_FILMS_LIST_SCREEN -> {
            iconBack.value = false
            "Избранное"
        }
        SETTINGS_SCREEN -> {
            iconBack.value = false
            "Настройки"
        }
        SHARE_SCREEN -> {
            iconBack.value = true
            "Поделиться"
        }
        "${FILM_DETAIL_SCREEN}/{${FILM_ITEM}}" -> {
            iconBack.value = true
            "Назад"
        }
        else -> ""
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
            IconButton(onClick = { navController.navigate(FILMS_LIST_SCREEN) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_main_film_list),
                    contentDescription = "main_film_list",
                )
            }
            Spacer(modifier = Modifier.weight(0.33f, true))
            IconButton(onClick = { navController.navigate(FAVORITE_FILMS_LIST_SCREEN) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite_film_list),
                    contentDescription = "favorite_film_list",
                )
            }
            Spacer(modifier = Modifier.weight(0.33f, true))
            IconButton(onClick = { navController.navigate(SETTINGS_SCREEN) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = "share",
                )
            }
        }
    }
}