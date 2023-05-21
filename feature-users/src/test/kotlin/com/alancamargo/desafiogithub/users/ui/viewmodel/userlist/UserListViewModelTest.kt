package com.alancamargo.desafiogithub.users.ui.viewmodel.userlist

import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.core.test.viewmodel.ViewModelFlowCollector
import com.alancamargo.desafiogithub.users.domain.model.UserSummaryListResult
import com.alancamargo.desafiogithub.users.domain.usecase.GetUsersUseCase
import com.alancamargo.desafiogithub.users.testtools.stubUserSummary
import com.alancamargo.desafiogithub.users.ui.model.UserListError
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
class UserListViewModelTest {

    private val mockGetUsersUseCase = mockk<GetUsersUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    private val viewModel = UserListViewModel(
        mockGetUsersUseCase,
        mockLogger,
        testDispatcher
    )

    private val collector = ViewModelFlowCollector(
        stateFlow = viewModel.state,
        actionFlow = viewModel.action,
        dispatcher = testDispatcher
    )

    @Test
    fun `when use case returns result getUsers should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every { mockGetUsersUseCase() } returns flowOf(UserSummaryListResult.NetworkError)

            // WHEN
            viewModel.getUsers()

            // THEN
            val expected = listOf(
                UserListViewState(isLoading = true),
                UserListViewState(error = UserListError.NETWORK_ERROR)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getUsers should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every { mockGetUsersUseCase() } returns flow { throw Throwable() }

            // WHEN
            viewModel.getUsers()

            // THEN
            val expected = listOf(
                UserListViewState(isLoading = true),
                UserListViewState(error = UserListError.GENERIC_ERROR)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getUsers should log exception`() {
        // GIVEN
        every { mockGetUsersUseCase() } returns flow { throw Throwable() }

        // WHEN
        viewModel.getUsers()

        // THEN
        verify { mockLogger.error(throwable = any()) }
    }

    @Test
    fun `onClickUser should send OpenUserDetails action`() {
        collector.test { _, actions ->
            // WHEN
            val user = stubUserSummary()
            viewModel.onClickUser(user)

            // THEN
            val expected = UserListViewAction.OpenUserDetails(user.userName)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `searchUser should send OpenUserDetails action`() {
        collector.test { _, actions ->
            // GIVEN
            val userName = "92alanc"

            // WHEN
            viewModel.searchUser(userName)

            // THEN
            val expected = UserListViewAction.OpenUserDetails(userName)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `onClickShowAppInfo should send ShowAppInfo action`() {
        collector.test { _, actions ->
            // WHEN
            viewModel.onClickShowAppInfo()

            // THEN
            val expected = UserListViewAction.ShowAppInfo
            assertThat(actions).contains(expected)
        }
    }
}
