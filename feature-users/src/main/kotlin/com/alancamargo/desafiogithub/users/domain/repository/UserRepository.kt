package com.alancamargo.desafiogithub.users.domain.repository

import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

internal interface UserRepository {

    suspend fun getUsers(): List<UserSummary>

    suspend fun getUser(userName: String): User?
}
