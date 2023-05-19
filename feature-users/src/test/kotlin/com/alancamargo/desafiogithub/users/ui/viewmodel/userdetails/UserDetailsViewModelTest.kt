package com.alancamargo.desafiogithub.users.ui.viewmodel.userdetails

import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.core.test.viewmodel.ViewModelFlowCollector
import com.alancamargo.desafiogithub.users.domain.model.UserResult
import com.alancamargo.desafiogithub.users.domain.usecase.GetUserUseCase
import com.alancamargo.desafiogithub.users.ui.model.UserDetailsError
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
class UserDetailsViewModelTest {

    private val mockGetUserUseCase = mockk<GetUserUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    private val viewModel = UserDetailsViewModel(
        mockGetUserUseCase,
        mockLogger,
        testDispatcher
    )

    private val collector = ViewModelFlowCollector(
        stateFlow = viewModel.state,
        actionFlow = viewModel.action,
        dispatcher = testDispatcher
    )

    @Test
    fun `when use case returns result getUserDetails should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every { mockGetUserUseCase(userName = any()) } returns flowOf(UserResult.NetworkError)

            // WHEN
            viewModel.getUserDetails(userName = "user")

            // THEN
            val expected = listOf(
                UserDetailsViewState(isLoading = true),
                UserDetailsViewState(error = UserDetailsError.NETWORK_ERROR)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getUserDetails should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every { mockGetUserUseCase(userName = any()) } returns flow { throw Throwable() }

            // WHEN
            viewModel.getUserDetails(userName = "user")

            // THEN
            val expected = listOf(
                UserDetailsViewState(isLoading = true),
                UserDetailsViewState(error = UserDetailsError.GENERIC_ERROR)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getUserDetails should log exception`() {
        // GIVEN
        every { mockGetUserUseCase(userName = any()) } returns flow { throw Throwable() }

        // WHEN
        viewModel.getUserDetails(userName = "user")

        // THEN
        verify { mockLogger.error(throwable = any()) }
    }

    @Test
    fun `onClickBack should send Finish action`() {
        collector.test { _, actions ->
            // WHEN
            viewModel.onClickBack()

            // THEN
            val expected = UserDetailsViewAction.Finish
            assertThat(actions).contains(expected)
        }
    }
}
