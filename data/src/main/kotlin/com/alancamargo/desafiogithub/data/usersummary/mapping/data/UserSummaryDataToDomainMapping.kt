package com.alancamargo.desafiogithub.data.usersummary.mapping.data

import com.alancamargo.desafiogithub.data.usersummary.model.local.DbUserSummary
import com.alancamargo.desafiogithub.data.usersummary.model.remote.UserSummaryResponse
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

fun UserSummaryResponse.toDomain() = UserSummary(
    userName = userName,
    profilePictureUrl = profilePictureUrl
)

fun DbUserSummary.toDomain() = UserSummary(
    userName = userName,
    profilePictureUrl = profilePictureUrl
)
