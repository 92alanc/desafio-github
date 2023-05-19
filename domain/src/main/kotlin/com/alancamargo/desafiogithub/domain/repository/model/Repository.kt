package com.alancamargo.desafiogithub.domain.repository.model

data class Repository(
    val id: Long,
    val name: String,
    val description: String,
    val ownerUserName: String,
    val starCount: Int,
    val watcherCount: Int,
    val forkCount: Int,
    val language: String
)
