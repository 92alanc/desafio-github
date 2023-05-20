package com.alancamargo.desafiogithub.data.repository.model.remote

import com.alancamargo.desafiogithub.data.usersummary.model.remote.UserSummaryResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String?,
    @SerialName("owner") val owner: UserSummaryResponse,
    @SerialName("language") val language: String?
)
