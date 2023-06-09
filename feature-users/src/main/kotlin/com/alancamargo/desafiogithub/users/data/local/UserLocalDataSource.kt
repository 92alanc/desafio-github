package com.alancamargo.desafiogithub.users.data.local

import com.alancamargo.desafiogithub.domain.repository.model.Repository
import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

internal interface UserLocalDataSource {

    suspend fun getUsers(): List<UserSummary>

    suspend fun getUser(userName: String): User?

    suspend fun saveUserSummary(userSummary: UserSummary)

    suspend fun saveUser(user: User)

    suspend fun getUserRepositories(ownerUserName: String): List<Repository>

    suspend fun saveRepository(repository: Repository)
}
