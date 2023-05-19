package com.alancamargo.desafiogithub.users.data.api

import com.alancamargo.desafiogithub.data.user.model.remote.UserResponse
import com.alancamargo.desafiogithub.data.usersummary.model.remote.UserSummaryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface UserApi {

    @GET("users")
    suspend fun getUsers(): Response<List<UserSummaryResponse>>

    @GET("users/{user_name}")
    suspend fun getUser(@Path("user_name") userName: String): Response<UserResponse>
}
