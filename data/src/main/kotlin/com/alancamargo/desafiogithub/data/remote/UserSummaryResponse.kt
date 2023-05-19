package com.alancamargo.desafiogithub.data.remote

import kotlinx.serialization.SerialName

data class UserSummaryResponse(
    @SerialName("login") val userName: String,
    @SerialName("avatar_url") val profilePictureUrl: String
)
