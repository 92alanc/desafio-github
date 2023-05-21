package com.alancamargo.desafiogithub.users.domain.usecase

import com.alancamargo.desafiogithub.users.domain.model.RepositoryListResult
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

internal class GetUserRepositoriesUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUserRepositoriesUseCase {

    override fun invoke(ownerUserName: String): Flow<RepositoryListResult> = flow {
        val result = try {
            val repositories = repository.getUserRepositories(ownerUserName)

            if (repositories.isEmpty()) {
                RepositoryListResult.NoResults
            } else {
                RepositoryListResult.Success(repositories)
            }
        } catch (e: IOException) {
            RepositoryListResult.NetworkError
        } catch (t: Throwable) {
            RepositoryListResult.GenericError
        }

        emit(result)
    }
}
