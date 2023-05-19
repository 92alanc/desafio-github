package com.alancamargo.desafiogithub.users.testtools

import com.alancamargo.desafiogithub.data.user.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.user.mapping.domain.toDb
import com.alancamargo.desafiogithub.data.user.model.remote.UserResponse
import com.alancamargo.desafiogithub.data.usersummary.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.usersummary.mapping.domain.toDb
import com.alancamargo.desafiogithub.data.usersummary.model.remote.UserSummaryResponse

fun stubUserSummaryResponseList() = listOf(
    UserSummaryResponse(
        userName = "ricky_gervais",
        profilePictureUrl = "https://test.com/ricky-gervais.png"
    ),
    UserSummaryResponse(
        userName = "stephen_merchant",
        profilePictureUrl = "https://test.com/stephen-merchant.png"
    )
)

fun stubUserResponse() = UserResponse(
    userName = "ricky_gervais",
    profilePictureUrl = "https://test.com/ricky-gervais.png",
    name = "Ricky Gervais",
    bio = "Comedian and creator of The Office",
    location = "Reading",
    publicRepositoryCount = 20,
    publicGistCount = 3,
    followerCount = 1000,
    followingCount = 1
)

fun stubUserSummaryList() = stubUserSummaryResponseList().map { it.toDomain() }

fun stubUser() = stubUserResponse().toDomain()

fun stubDbUserSummaryList() = stubUserSummaryList().map { it.toDb() }

fun stubDbUser() = stubUser().toDb()
