package com.alancamargo.desafiogithub.data.user.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("login") val userName: String,
    @SerialName("avatar_url") val profilePictureUrl: String,
    @SerialName("name") val name: String,
    @SerialName("bio") val bio: String?,
    @SerialName("location") val location: String?,
    @SerialName("public_repos") val publicRepositoryCount: Int,
    @SerialName("public_gists") val publicGistCount: Int,
    @SerialName("followers") val followerCount: Int,
    @SerialName("following") val followingCount: Int
)
