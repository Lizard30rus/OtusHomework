package com.example.otushomework.di

import com.example.otushomework.data.repository.FilmsRepository
import com.example.otushomework.data.repository.FilmsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun filmRepository(impl : FilmsRepositoryImpl) : FilmsRepository
}