package com.alancamargo.desafiogithub.data.repository.mapping.data

import com.alancamargo.desafiogithub.data.repository.model.local.DbRepository
import com.alancamargo.desafiogithub.data.repository.model.remote.RepositoryResponse
import com.alancamargo.desafiogithub.data.usersummary.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.usersummary.model.local.DbUserSummary
import com.alancamargo.desafiogithub.domain.repository.model.Repository

fun RepositoryResponse.toDomain() = Repository(
    id = id,
    name = name,
    description = description,
    owner = owner.toDomain(),
    starCount = starCount,
    watcherCount = watcherCount,
    forkCount = forkCount,
    language = language
)

fun DbRepository.toDomain(dbUserSummary: DbUserSummary) = Repository(
    id = id,
    name = name,
    description = description,
    owner = dbUserSummary.toDomain(),
    starCount = starCount,
    watcherCount = watcherCount,
    forkCount = forkCount,
    language = language
)
