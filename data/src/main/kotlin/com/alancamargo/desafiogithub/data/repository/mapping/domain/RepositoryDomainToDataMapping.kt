package com.alancamargo.desafiogithub.data.repository.mapping.domain

import com.alancamargo.desafiogithub.data.repository.model.local.DbRepository
import com.alancamargo.desafiogithub.domain.repository.model.Repository

fun Repository.toDb() = DbRepository(
    id = id,
    name = name,
    description = description,
    ownerId = owner.userName,
    starCount = starCount,
    watcherCount = watcherCount,
    forkCount = forkCount,
    language = language
)
