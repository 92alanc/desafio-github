package com.alancamargo.desafiogithub.core.di

import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.core.log.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LogModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()
}
