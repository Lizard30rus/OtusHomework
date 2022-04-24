package com.example.otushomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.otushomework.ui.filmscreen.FilmListScreen
import com.example.otushomework.ui.theme.OtusHomeworkTheme
import com.example.otushomework.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtusHomeworkTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Constants.FILMS_LIST_SCREEN
                ) {
                    composable(Constants.FILMS_LIST_SCREEN) {
                        FilmListScreen(navController = navController)
                    }
                    composable(Constants.FAVORITE_FILMS_LIST_SCREEN) {

                    }
                    composable(
                        "${Constants.FILM_DETAIL_SCREEN}/{name_film}",
                        arguments = listOf(
                            navArgument(Constants.NAME_FILM_KEY) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val nameFilm = remember {
                            it.arguments?.getString(Constants.NAME_FILM_KEY)
                        }
                    }
                }
            }
        }
    }
}