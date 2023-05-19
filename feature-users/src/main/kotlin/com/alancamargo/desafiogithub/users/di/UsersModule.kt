package com.alancamargo.desafiogithub.users.di

import com.alancamargo.desafiogithub.users.data.local.UserLocalDataSource
import com.alancamargo.desafiogithub.users.data.local.UserLocalDataSourceImpl
import com.alancamargo.desafiogithub.users.data.remote.UserRemoteDataSource
import com.alancamargo.desafiogithub.users.data.remote.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UsersModule {

    @Binds
    @ViewModelScoped
    abstract fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindUserLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource
}
