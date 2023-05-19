package com.alancamargo.desafiogithub.users.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alancamargo.desafiogithub.data.usersummary.model.local.DbUserSummary

@Dao
internal interface UserSummaryDao {

    @Query("SELECT * FROM USER_SUMMARIES")
    suspend fun selectUsers(): List<DbUserSummary>?

    @Insert(entity = DbUserSummary::class)
    suspend fun insertUserSummary(userSummary: DbUserSummary)

    @Update(entity = DbUserSummary::class)
    suspend fun updateUserSummary(userSummary: DbUserSummary)

    @Query("DELETE FROM USER_SUMMARIES")
    suspend fun deleteUserSummaries()

    @Query("SELECT COUNT() FROM USER_SUMMARIES WHERE userName = :userName")
    suspend fun getUserCount(userName: String): Int
}
