package com.alancamargo.desafiogithub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_SUMMARIES")
data class DbUserSummary(
    @PrimaryKey val userName: String,
    val profilePictureUrl: String
)
