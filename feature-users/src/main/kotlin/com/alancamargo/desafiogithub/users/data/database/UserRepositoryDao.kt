package com.alancamargo.desafiogithub.users.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alancamargo.desafiogithub.data.repository.model.local.DbRepository

@Dao
internal interface UserRepositoryDao {

    @Query("SELECT * FROM REPOSITORIES WHERE ownerUserName = :ownerUserName")
    suspend fun getUserRepositories(ownerUserName: String): List<DbRepository>?

    @Insert(entity = DbRepository::class)
    suspend fun insertRepository(repository: DbRepository)

    @Update(entity = DbRepository::class)
    suspend fun updateRepository(repository: DbRepository)

    @Query("SELECT COUNT() FROM REPOSITORIES WHERE id = :id")
    suspend fun getRepositoryCount(id: Long): Int
}
