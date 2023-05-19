package com.alancamargo.desafiogithub.domain

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
