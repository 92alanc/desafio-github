package com.alancamargo.desafiogithub.users.di

import com.alancamargo.desafiogithub.users.data.local.UserLocalDataSource
import com.alancamargo.desafiogithub.users.data.local.UserLocalDataSourceImpl
import com.alancamargo.desafiogithub.users.data.remote.UserRemoteDataSource
import com.alancamargo.desafiogithub.users.data.remote.UserRemoteDataSourceImpl
import com.alancamargo.desafiogithub.users.data.repository.UserRepositoryImpl
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import com.alancamargo.desafiogithub.users.domain.usecase.GetUsersUseCase
import com.alancamargo.desafiogithub.users.domain.usecase.GetUsersUseCaseImpl
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

    @Binds
    @ViewModelScoped
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @ViewModelScoped
    abstract fun bindGetUsersUseCase(impl: GetUsersUseCaseImpl): GetUsersUseCase
}
