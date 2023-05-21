package com.alancamargo.desafiogithub.users.domain.usecase

import com.alancamargo.desafiogithub.users.domain.model.UserSummaryListResult
import kotlinx.coroutines.flow.Flow

internal interface GetUsersUseCase {

    operator fun invoke(): Flow<UserSummaryListResult>
}
