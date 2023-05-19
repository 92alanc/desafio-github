package com.alancamargo.desafiogithub.data.user.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USERS")
data class DbUser(
    @PrimaryKey val userName: String,
    val profilePictureUrl: String,
    val name: String,
    val bio: String,
    val location: String,
    val publicRepositoryCount: Int,
    val publicGistCount: Int,
    val followerCount: Int,
    val followingCount: Int
)
