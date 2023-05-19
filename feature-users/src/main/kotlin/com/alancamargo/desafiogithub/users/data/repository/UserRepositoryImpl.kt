package com.alancamargo.desafiogithub.users.data.repository

import com.alancamargo.desafiogithub.domain.repository.model.Repository
import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.data.local.UserLocalDataSource
import com.alancamargo.desafiogithub.users.data.remote.UserRemoteDataSource
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUsers(): List<UserSummary> {
        return try {
            remoteDataSource.getUsers().onEach {
                localDataSource.saveUserSummary(it)
            }
        } catch (t: Throwable) {
            localDataSource.getUsers().takeUnless { it.isEmpty() } ?: throw t
        }
    }

    override suspend fun getUser(userName: String): User? {
        return try {
            remoteDataSource.getUser(userName)?.also {
                localDataSource.saveUser(it)
            }
        } catch (t: Throwable) {
            localDataSource.getUser(userName).takeUnless { it == null } ?: throw t
        }
    }

    override suspend fun getUserRepositories(ownerUserName: String): List<Repository> {
        return try {
            remoteDataSource.getUserRepositories(ownerUserName).onEach {
                localDataSource.saveRepository(it)
            }
        } catch (t: Throwable) {
            localDataSource.getUserRepositories(ownerUserName).takeUnless {
                it.isEmpty()
            } ?: throw t
        }
    }
}
