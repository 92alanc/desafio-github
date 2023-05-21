package com.alancamargo.desafiogithub.users.data.local

import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.data.repository.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.repository.mapping.domain.toDb
import com.alancamargo.desafiogithub.data.user.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.user.mapping.domain.toDb
import com.alancamargo.desafiogithub.data.usersummary.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.usersummary.mapping.domain.toDb
import com.alancamargo.desafiogithub.domain.repository.model.Repository
import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.data.database.UserDao
import com.alancamargo.desafiogithub.users.data.database.UserRepositoryDao
import com.alancamargo.desafiogithub.users.data.database.UserSummaryDao
import javax.inject.Inject

internal class UserLocalDataSourceImpl @Inject constructor(
    private val userSummaryDao: UserSummaryDao,
    private val userDao: UserDao,
    private val userRepositoryDao: UserRepositoryDao,
    private val logger: Logger
) : UserLocalDataSource {

    override suspend fun getUsers(): List<UserSummary> {
        return try {
            userSummaryDao.selectUsers()?.map {
                it.toDomain()
            } ?: emptyList()
        } catch (t: Throwable) {
            logger.error(t)
            emptyList()
        }
    }

    override suspend fun getUser(userName: String): User? {
        return try {
            userDao.selectUser(userName)?.toDomain()
        } catch (t: Throwable) {
            logger.error(t)
            null
        }
    }

    override suspend fun saveUserSummary(userSummary: UserSummary) {
        try {
            val userExists = userSummaryDao.getUserCount(userSummary.userName) == 1
            val dbUserSummary = userSummary.toDb()

            if (userExists) {
                userSummaryDao.updateUserSummary(dbUserSummary)
            } else {
                userSummaryDao.insertUserSummary(dbUserSummary)
            }
        } catch (t: Throwable) {
            logger.error(t)
        }
    }

    override suspend fun saveUser(user: User) {
        try {
            val userExists = userDao.getUserCount(user.userName) == 1
            val dbUser = user.toDb()

            if (userExists) {
                userDao.updateUser(dbUser)
            } else {
                userDao.insertUser(dbUser)
            }
        } catch (t: Throwable) {
            logger.error(t)
        }
    }

    override suspend fun getUserRepositories(ownerUserName: String): List<Repository> {
        return try {
            userRepositoryDao.selectUserRepositories(ownerUserName)?.map {
                it.toDomain()
            } ?: emptyList()
        } catch (t: Throwable) {
            logger.error(t)
            emptyList()
        }
    }

    override suspend fun saveRepository(repository: Repository) {
        try {
            val repositoryExists = userRepositoryDao.getRepositoryCount(repository.id) == 1
            val dbRepository = repository.toDb()

            if (repositoryExists) {
                userRepositoryDao.updateRepository(dbRepository)
            } else {
                userRepositoryDao.insertRepository(dbRepository)
            }
        } catch (t: Throwable) {
            logger.error(t)
        }
    }
}
