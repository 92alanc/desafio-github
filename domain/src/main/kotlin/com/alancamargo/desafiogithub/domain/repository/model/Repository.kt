package com.alancamargo.desafiogithub.domain.repository.model

import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

data class Repository(
    val id: Long,
    val name: String,
    val description: String,
    val owner: UserSummary,
    val starCount: Int,
    val watcherCount: Int,
    val forkCount: Int,
    val language: String
)
