package com.alancamargo.desafiogithub.users.data.remote

import com.alancamargo.desafiogithub.domain.repository.model.Repository
import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

internal interface UserRemoteDataSource {

    suspend fun getUsers(): List<UserSummary>

    suspend fun getUser(userName: String): User?

    suspend fun getUserRepositories(ownerUserName: String): List<Repository>
}
