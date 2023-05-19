package com.alancamargo.desafiogithub.data.user.mapping.domain

import com.alancamargo.desafiogithub.data.user.model.local.DbUser
import com.alancamargo.desafiogithub.domain.user.model.User

fun User.toDb() = DbUser(
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
