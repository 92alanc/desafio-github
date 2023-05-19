package com.alancamargo.desafiogithub.users.domain.usecase

import com.alancamargo.desafiogithub.users.domain.model.UserSummaryListResult
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

internal class GetUsersUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUsersUseCase {

    override fun invoke(): Flow<UserSummaryListResult> = flow {
        val result = try {
            val users = repository.getUsers()

            if (users.isEmpty()) {
                UserSummaryListResult.Empty
            } else {
                UserSummaryListResult.Success(users)
            }
        } catch (e: IOException) {
            UserSummaryListResult.NetworkError
        } catch (t: Throwable) {
            UserSummaryListResult.GenericError
        }

        emit(result)
    }
}
