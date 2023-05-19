package com.alancamargo.desafiogithub.data.remote

import com.alancamargo.desafiogithub.domain.UserSummary
import kotlinx.serialization.SerialName

data class RepositoryResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("owner") val owner: UserSummary,
    @SerialName("stargazers_count") val starCount: Int,
    @SerialName("watchers_count") val watcherCount: Int,
    @SerialName("forks_count") val forkCount: Int,
    @SerialName("language") val language: String
)
