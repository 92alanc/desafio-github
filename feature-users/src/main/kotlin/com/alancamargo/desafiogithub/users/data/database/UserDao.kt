package com.alancamargo.desafiogithub.users.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alancamargo.desafiogithub.data.user.model.local.DbUser

@Dao
interface UserDao {

    @Query("SELECT * FROM USERS WHERE userName = :userName")
    suspend fun selectUser(userName: String): DbUser

    @Insert(entity = DbUser::class)
    suspend fun insertUser(user: DbUser)

    @Update(entity = DbUser::class)
    suspend fun updateUser(user: DbUser)

    @Query("DELETE FROM USERS")
    suspend fun deleteUsers()
}
