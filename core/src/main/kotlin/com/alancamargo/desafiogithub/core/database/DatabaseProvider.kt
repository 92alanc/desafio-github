package com.alancamargo.desafiogithub.core.database

import androidx.room.RoomDatabase
import kotlin.reflect.KClass

interface DatabaseProvider {

    fun <T : RoomDatabase> provideDatabase(clazz: KClass<T>, databaseName: String): T

    fun <T : RoomDatabase> provideDatabaseFromAsset(
        clazz: KClass<T>,
        databaseName: String,
        assetPath: String
    ): T
}
