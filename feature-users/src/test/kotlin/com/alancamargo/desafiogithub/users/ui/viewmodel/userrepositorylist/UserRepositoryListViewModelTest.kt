package com.alancamargo.desafiogithub.users.ui.viewmodel.userrepositorylist

import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.core.test.viewmodel.ViewModelFlowCollector
import com.alancamargo.desafiogithub.users.domain.model.RepositoryListResult
import com.alancamargo.desafiogithub.users.domain.usecase.GetUserRepositoriesUseCase
import com.alancamargo.desafiogithub.users.ui.model.RepositoryListError
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryListViewModelTest {

    private val mockGetUserRepositoriesUseCase = mockk<GetUserRepositoriesUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    private val viewModel = UserRepositoryListViewModel(
        mockGetUserRepositoriesUseCase,
        mockLogger,
        testDispatcher
    )

    private val collector = ViewModelFlowCollector(
        stateFlow = viewModel.state,
        actionFlow = viewModel.state,
        dispatcher = testDispatcher
    )

    @Test
    fun `when use case returns result getUserRepositories should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every {
                mockGetUserRepositoriesUseCase(ownerUserName = any())
            } returns flowOf(RepositoryListResult.NetworkError)

            // WHEN
            viewModel.getUserRepositories(ownerUserName = "user")

            // THEN
            val expected = listOf(
                UserRepositoryListViewState(isLoading = true),
                UserRepositoryListViewState(error = RepositoryListError.NETWORK_ERROR)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getUserRepositories should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every {
                mockGetUserRepositoriesUseCase(ownerUserName = any())
            } returns flow { throw Throwable() }

            // WHEN
            viewModel.getUserRepositories(ownerUserName = "user")

            // THEN
            val expected = listOf(
                UserRepositoryListViewState(isLoading = true),
                UserRepositoryListViewState(error = RepositoryListError.GENERIC_ERROR)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getUserRepositories should log exception`() {
        // GIVEN
        every {
            mockGetUserRepositoriesUseCase(ownerUserName = any())
        } returns flow { throw Throwable() }

        // WHEN
        viewModel.getUserRepositories(ownerUserName = "user")

        // THEN
        verify { mockLogger.error(throwable = any()) }
    }
}
