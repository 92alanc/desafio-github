package com.alancamargo.desafiogithub.users.di

import com.alancamargo.desafiogithub.core.database.DatabaseProvider
import com.alancamargo.desafiogithub.core.network.ApiProvider
import com.alancamargo.desafiogithub.users.data.api.UserApi
import com.alancamargo.desafiogithub.users.data.database.UserDao
import com.alancamargo.desafiogithub.users.data.database.UserDatabase
import com.alancamargo.desafiogithub.users.data.database.UserRepositoryDao
import com.alancamargo.desafiogithub.users.data.database.UserSummaryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UsersDataModule {

    @Provides
    @Singleton
    fun provideUserApi(apiProvider: ApiProvider): UserApi {
        return apiProvider.provideService(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(databaseProvider: DatabaseProvider): UserDatabase {
        return databaseProvider.provideDatabase(
            clazz = UserDatabase::class,
            databaseName = "users"
        )
    }

    @Provides
    @Singleton
    fun provideUserSummaryDao(userDatabase: UserDatabase): UserSummaryDao {
        return userDatabase.provideUserSummaryDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.provideUserDao()
    }

    @Provides
    @Singleton
    fun provideUserRepositoryDao(userDatabase: UserDatabase): UserRepositoryDao {
        return userDatabase.provideUserRepositoryDao()
    }
}
