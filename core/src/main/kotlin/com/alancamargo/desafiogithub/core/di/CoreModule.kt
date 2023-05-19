package com.alancamargo.desafiogithub.core.di

import android.content.Context
import com.alancamargo.desafiogithub.core.database.DatabaseProvider
import com.alancamargo.desafiogithub.core.database.DatabaseProviderImpl
import com.alancamargo.desafiogithub.core.network.ApiProvider
import com.alancamargo.desafiogithub.core.network.ApiProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreModule {

    @Provides
    @Singleton
    fun provideApiProvider(
        @BaseUrl baseUrl: String
    ): ApiProvider = ApiProviderImpl(baseUrl)

    @Provides
    @Singleton
    fun provideDatabaseProvider(
        @ApplicationContext context: Context
    ): DatabaseProvider = DatabaseProviderImpl(context)
}
