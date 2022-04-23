package com.example.otushomework.di

import android.content.Context
import androidx.room.Room
import com.example.otushomework.data.repository.room.FilmDao
import com.example.otushomework.data.repository.room.FilmDatabase
import com.example.otushomework.data.repository.web.FilmsApi
import com.example.otushomework.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideWebDataSource() : FilmsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(FilmsApi::class.java)
    }

    @Singleton
    @Provides
    fun DbDataSource(@ApplicationContext context: Context) : FilmDatabase {
        return Room
            .databaseBuilder(context,
                FilmDatabase::class.java,
            "film_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun filmDao(db : FilmDatabase) : FilmDao = db.FilmDao()
}