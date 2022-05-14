package com.example.otushomework.di

import android.content.Context
import androidx.room.Room
import com.example.otushomework.BuildConfig
import com.example.otushomework.data.repository.room.FilmDao
import com.example.otushomework.data.repository.room.FilmDatabase
import com.example.otushomework.data.repository.web.FilmsApi
import com.example.otushomework.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideWebDataSource(): FilmsApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                }).build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(FilmsApi::class.java)
    }

    @Singleton
    @Provides
    fun DbDataSource(@ApplicationContext context: Context): FilmDatabase {
        return Room
            .databaseBuilder(
                context,
                FilmDatabase::class.java,
                "film_database"
            )
            .build()
    }

    @Singleton
    @Provides
    fun filmDao(db: FilmDatabase): FilmDao = db.FilmDao()
}