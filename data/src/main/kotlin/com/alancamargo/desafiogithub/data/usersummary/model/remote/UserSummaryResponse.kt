package com.alancamargo.desafiogithub.data.usersummary.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSummaryResponse(
    @SerialName("login") val userName: String,
    @SerialName("avatar_url") val profilePictureUrl: String
)
