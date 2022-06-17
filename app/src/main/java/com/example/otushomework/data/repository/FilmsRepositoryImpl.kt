package com.example.otushomework.data.repository

import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.data.repository.room.favoritefilm.FavoriteFilmDao
import com.example.otushomework.data.repository.room.film.FilmDao
import com.example.otushomework.data.repository.web.FilmsApi
import com.example.otushomework.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val webDataSource: FilmsApi,
    private val dbDataSource: FilmDao,
    private val favoriteDbDataSource: FavoriteFilmDao
) : FilmsRepository {

    override suspend fun getFilmsFromWeb(limit: Int, offset: Int): Response<List<FilmItemModel>> {
        return try {
            val resultFromWeb = webDataSource.updateFilms(limit, offset)
            val result = mutableListOf<FilmItemModel>()
            resultFromWeb.forEach { item ->
                result.add(
                    FilmItemModel(
                        imageFilm = item.imageFilm,
                        id = item.id,
                        nameFilm = item.nameFilm,
                        descriptionFilm = item.descriptionFilm,
                        isFavorite = false
                    )
                )
            }
            dbDataSource.addFilms(result)
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun getFilmsFromRoom(): Response<Flow<List<FilmItemModel>>> {
        return try {
            val result = dbDataSource.getFilms()
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override fun getFavoriteFilms(): Response<Flow<List<FilmItemModel>>> {
        return try {
            val result = favoriteDbDataSource.getFavoriteFilms()
            Response.Success(result)
        } catch (e: java.lang.Exception) {
            Response.Error(e)
        }
    }

    override suspend fun addToFavorites(filmItemModel: FilmItemModel) {
        favoriteDbDataSource.addToFavorites(filmItemModel)
    }

    override suspend fun deleteFromFavorites(filmItemModel: FilmItemModel) {
        favoriteDbDataSource.deleteFromFavorites(filmItemModel)
    }
}