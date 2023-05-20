package com.alancamargo.desafiogithub.data.user.mapping.data

import com.alancamargo.desafiogithub.data.user.model.local.DbUser
import com.alancamargo.desafiogithub.data.user.model.remote.UserResponse
import com.alancamargo.desafiogithub.domain.user.model.User

fun UserResponse.toDomain() = User(
    userName = userName,
    profilePictureUrl = profilePictureUrl,
    name = name,
    bio = bio.orEmpty(),
    location = location.orEmpty(),
    publicRepositoryCount = publicRepositoryCount,
    publicGistCount = publicGistCount,
    followerCount = followerCount,
    followingCount = followingCount
)

fun DbUser.toDomain() = User(
    userName = userName,
    profilePictureUrl = profilePictureUrl,
    name = name,
    bio = bio,
    location = location,
    publicRepositoryCount = publicRepositoryCount,
    publicGistCount = publicGistCount,
    followerCount = followerCount,
    followingCount = followingCount
)
