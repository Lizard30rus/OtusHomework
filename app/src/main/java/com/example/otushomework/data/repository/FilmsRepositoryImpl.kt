package com.example.otushomework.data.repository

import android.util.Log
import com.example.otushomework.data.models.FilmItemModel
import com.example.otushomework.data.repository.room.FilmDao
import com.example.otushomework.data.repository.web.FilmsApi
import com.example.otushomework.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val webDataSource: FilmsApi,
    private val dbDataSource: FilmDao
) : FilmsRepository {

    override suspend fun updateFilms() {
        /* try {
             val resultFromWeb = webDataSource.updateFilms()
             Log.d("TAG", "now we in repository impl, update films, ${resultFromWeb.size}")
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
         } catch (e: Exception) {
             println(e.message)
         }*/
    }

    override suspend fun getFilmsFromWeb(limit: Int, offset: Int): Response<List<FilmItemModel>> {
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
        return try {
            Response.Succsess(result)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override fun getFilms(): Response<Flow<List<FilmItemModel>>> {
        val result = dbDataSource.getFilms()
        return try {
            Response.Succsess(result)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override fun getFavoriteFilms(): Response<Flow<List<FilmItemModel>>> {
        val result = dbDataSource.getFavoriteFilms(true)
        return try {
            Response.Succsess(result)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override fun getDetailsFilm(id: Int): Response<Flow<FilmItemModel>> {
        val result = dbDataSource.getFilm(id)
        Log.d("TAG", "now we in getDetailsFilm, id: $id")
        return try {
            Response.Succsess(result)

        } catch (e: Exception) {
            Response.Error(e)
        }
    }

}