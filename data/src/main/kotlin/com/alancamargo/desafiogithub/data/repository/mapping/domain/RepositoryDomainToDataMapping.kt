package com.alancamargo.desafiogithub.data.repository.mapping.domain

import com.alancamargo.desafiogithub.data.repository.model.local.DbRepository
import com.alancamargo.desafiogithub.domain.repository.model.Repository

fun Repository.toDb() = DbRepository(
    id = id,
    name = name,
    description = description,
    ownerUserName = ownerUserName,
    language = language
)
