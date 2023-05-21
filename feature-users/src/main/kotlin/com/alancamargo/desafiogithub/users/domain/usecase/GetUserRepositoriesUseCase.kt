package com.alancamargo.desafiogithub.users.domain.usecase

import com.alancamargo.desafiogithub.users.domain.model.RepositoryListResult
import kotlinx.coroutines.flow.Flow

internal interface GetUserRepositoriesUseCase {

    operator fun invoke(ownerUserName: String): Flow<RepositoryListResult>
}
