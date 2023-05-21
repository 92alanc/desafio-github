package com.alancamargo.desafiogithub.domain.user.model

data class User(
    val userName: String,
    val profilePictureUrl: String,
    val name: String,
    val bio: String,
    val location: String,
    val publicRepositoryCount: Int,
    val publicGistCount: Int,
    val followerCount: Int,
    val followingCount: Int
)
