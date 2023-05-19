package com.alancamargo.desafiogithub.data.repository.mapping.data

import com.alancamargo.desafiogithub.data.repository.model.local.DbRepository
import com.alancamargo.desafiogithub.data.repository.model.remote.RepositoryResponse
import com.alancamargo.desafiogithub.domain.repository.model.Repository

fun RepositoryResponse.toDomain() = Repository(
    id = id,
    name = name,
    description = description,
    ownerUserName = owner.userName,
    language = language
)

fun DbRepository.toDomain() = Repository(
    id = id,
    name = name,
    description = description,
    ownerUserName = ownerUserName,
    language = language
)
