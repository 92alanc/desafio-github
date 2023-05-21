package com.alancamargo.desafiogithub.users.domain.usecase

import com.alancamargo.desafiogithub.users.domain.model.UserResult
import kotlinx.coroutines.flow.Flow

internal interface GetUserUseCase {

    operator fun invoke(userName: String): Flow<UserResult>
}
