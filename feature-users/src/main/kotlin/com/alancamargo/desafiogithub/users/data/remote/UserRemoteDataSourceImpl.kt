package com.alancamargo.desafiogithub.users.data.remote

import com.alancamargo.desafiogithub.data.user.mapping.data.toDomain
import com.alancamargo.desafiogithub.data.usersummary.mapping.data.toDomain
import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.data.api.UserApi
import java.net.HttpURLConnection
import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {

    override suspend fun getUsers(): List<UserSummary> {
        val response = userApi.getUsers()

        return if (response.isSuccessful) {
            response.body()?.map {
                it.toDomain()
            } ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getUser(userName: String): User? {
        val response = userApi.getUser(userName)

        return if (response.isSuccessful) {
            response.body()?.toDomain()
        } else {
            when (response.code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> null
                else -> throw Throwable()
            }
        }
    }
}
