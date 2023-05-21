package com.alancamargo.desafiogithub.users.domain.usecase

import app.cash.turbine.test
import com.alancamargo.desafiogithub.users.domain.model.RepositoryListResult
import com.alancamargo.desafiogithub.users.domain.repository.UserRepository
import com.alancamargo.desafiogithub.users.testtools.stubRepositoryList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class GetUserRepositoriesUseCaseImplTest {

    private val mockRepository = mockk<UserRepository>()
    private val useCase = GetUserRepositoriesUseCaseImpl(mockRepository)

    @Test
    fun `when repository returns user repositories invoke should return Success`() {
        runBlocking {
            // GIVEN
            val repositories = stubRepositoryList()
            coEvery {
                mockRepository.getUserRepositories(ownerUserName = any())
            } returns repositories

            // WHEN
            val result = useCase(ownerUserName = "user")

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(RepositoryListResult.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when repository returns empty list invoke should return NoResults`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUserRepositories(ownerUserName = any()) } returns emptyList()

        // WHEN
        val result = useCase(ownerUserName = "user")

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(RepositoryListResult.NoResults::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws IOException invoke should return NetworkError`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUserRepositories(ownerUserName = any()) } throws IOException()

        // WHEN
        val result = useCase(ownerUserName = "user")

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(RepositoryListResult.NetworkError::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws exception invoke should return GenericError`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getUserRepositories(ownerUserName = any()) } throws Throwable()

        // WHEN
        val result = useCase(ownerUserName = "user")

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isInstanceOf(RepositoryListResult.GenericError::class.java)
            awaitComplete()
        }
    }
}
