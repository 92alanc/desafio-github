package com.alancamargo.desafiogithub.data.repository.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alancamargo.desafiogithub.data.usersummary.model.local.DbUserSummary

@Entity(
    tableName = "REPOSITORIES",
    foreignKeys = [
        ForeignKey(
            entity = DbUserSummary::class,
            parentColumns = ["userName"],
            childColumns = ["ownerId"]
        )
    ]
)
data class DbRepository(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val ownerId: String,
    val starCount: Int,
    val watcherCount: Int,
    val forkCount: Int,
    val language: String
)
