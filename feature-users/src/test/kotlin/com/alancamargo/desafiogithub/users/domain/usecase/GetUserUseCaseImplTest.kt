package com.alancamargo.desafiogithub.users.domain.usecase

import app.cash.turbine.test
import com.alancamargo.desafiogithub.users.domain.model.UserResult
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import com.alancamargo.desafiogithub.users.testtools.stubUser
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class GetUserUseCaseImplTest {

    private val mockRepository = mockk<UserRepository>()
    private val useCase = GetUserUseCaseImpl(mockRepository)

    @Test
    fun `when repository returns user invoke should return Success`() {
        runBlocking {
            // GIVEN
            val user = stubUser()
            coEvery { mockRepository.getUser(userName = any()) } returns user

            // WHEN
            val result = useCase(userName = "user")

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(UserResult.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when repository returns null invoke should return UserNotFound`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUser(userName = any()) } returns null

        // WHEN
        val result = useCase(userName = "user")

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(UserResult.UserNotFound::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws IOException invoke should return NetworkError`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUser(userName = any()) } throws IOException()

        // WHEN
        val result = useCase(userName = "user")

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(UserResult.NetworkError::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws exception invoke should return GenericError`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUser(userName = any()) } throws Throwable()

        // WHEN
        val result = useCase(userName = "user")

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(UserResult.GenericError::class.java)
            awaitComplete()
        }
    }
}
