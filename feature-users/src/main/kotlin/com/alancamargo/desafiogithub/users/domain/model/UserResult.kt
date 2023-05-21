package com.alancamargo.desafiogithub.users.domain.model

import com.alancamargo.desafiogithub.domain.user.model.User

internal sealed class UserResult {

    data class Success(val user: User) : UserResult()

    object UserNotFound : UserResult()

    object NetworkError : UserResult()

    object GenericError : UserResult()
}
