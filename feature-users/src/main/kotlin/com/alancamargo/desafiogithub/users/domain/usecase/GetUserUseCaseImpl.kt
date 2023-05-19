package com.alancamargo.desafiogithub.users.domain.usecase

import com.alancamargo.desafiogithub.users.domain.model.UserResult
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

internal class GetUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUserUseCase {

    override fun invoke(userName: String): Flow<UserResult> = flow {
        val result = try {
            val user = repository.getUser(userName)

            if (user == null) {
                UserResult.UserNotFound
            } else {
                UserResult.Success(user)
            }
        } catch (e: IOException) {
            UserResult.NetworkError
        } catch (t: Throwable) {
            UserResult.GenericError
        }

        emit(result)
    }
}
