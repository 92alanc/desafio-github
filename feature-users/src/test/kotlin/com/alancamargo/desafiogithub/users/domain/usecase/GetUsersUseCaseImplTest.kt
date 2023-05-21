package com.alancamargo.desafiogithub.users.domain.usecase

import app.cash.turbine.test
import com.alancamargo.desafiogithub.users.domain.model.UserSummaryListResult
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import com.alancamargo.desafiogithub.users.testtools.stubUserSummaryList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class GetUsersUseCaseImplTest {

    private val mockRepository = mockk<UserRepository>()
    private val useCase = GetUsersUseCaseImpl(mockRepository)

    @Test
    fun `when repository returns users invoke should return Success`() {
        runBlocking {
            // GIVEN
            val users = stubUserSummaryList()
            coEvery { mockRepository.getUsers() } returns users

            // WHEN
            val result = useCase()

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(UserSummaryListResult.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when repository returns empty list invoke should return Empty`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUsers() } returns emptyList()

        // WHEN
        val result = useCase()

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(UserSummaryListResult.Empty::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws IOException invoke should return NetworkError`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUsers() } throws IOException()

        // WHEN
        val result = useCase()

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(UserSummaryListResult.NetworkError::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws exception invoke should return GenericError`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUsers() } throws Throwable()

        // WHEN
        val result = useCase()

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(UserSummaryListResult.GenericError::class.java)
            awaitComplete()
        }
    }
}
