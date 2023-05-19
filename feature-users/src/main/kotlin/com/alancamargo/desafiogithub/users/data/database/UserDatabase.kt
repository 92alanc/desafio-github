package com.alancamargo.desafiogithub.users.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.desafiogithub.data.repository.model.local.DbRepository
import com.alancamargo.desafiogithub.data.user.model.local.DbUser
import com.alancamargo.desafiogithub.data.usersummary.model.local.DbUserSummary

@Database(
    entities = [
        DbUserSummary::class,
        DbUser::class,
        DbRepository::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class UserDatabase : RoomDatabase() {

    abstract fun provideUserSummaryDao(): UserSummaryDao

    abstract fun provideUserDao(): UserDao

    abstract fun provideUserRepositoryDao(): UserRepositoryDao
}
