package com.alancamargo.desafiogithub.users.data.local

import com.alancamargo.desafiogithub.data.user.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.user.mapping.domain.toDb
import com.alancamargo.desafiogithub.data.usersummary.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.usersummary.mapping.domain.toDb
import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.data.database.UserDao
import com.alancamargo.desafiogithub.users.data.database.UserSummaryDao
import javax.inject.Inject

internal class UserLocalDataSourceImpl @Inject constructor(
    private val userSummaryDao: UserSummaryDao,
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun getUsers(): List<UserSummary> {
        return userSummaryDao.selectUsers()?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override suspend fun getUser(userName: String): User? {
        return userDao.selectUser(userName)?.toDomain()
    }

    override suspend fun saveUserSummary(userSummary: UserSummary) {
        val userExists = userSummaryDao.getUserCount(userSummary.userName) == 1
        val dbUserSummary = userSummary.toDb()

        if (userExists) {
            userSummaryDao.updateUserSummary(dbUserSummary)
        } else {
            userSummaryDao.insertUserSummary(dbUserSummary)
        }
    }

    override suspend fun saveUser(user: User) {
        val userExists = userDao.getUserCount(user.userName) == 1
        val dbUser = user.toDb()

        if (userExists) {
            userDao.updateUser(dbUser)
        } else {
            userDao.insertUser(dbUser)
        }
    }

    override suspend fun deleteUserSummaries() {
        userSummaryDao.deleteUserSummaries()
    }

    override suspend fun deleteUsers() {
        userDao.deleteUsers()
    }
}
