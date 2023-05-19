package com.alancamargo.desafiogithub.data.usersummary.mapping.domain

import com.alancamargo.desafiogithub.data.usersummary.model.local.DbUserSummary
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

fun UserSummary.toDb() = DbUserSummary(
    userName = userName,
    profilePictureUrl = profilePictureUrl
)
