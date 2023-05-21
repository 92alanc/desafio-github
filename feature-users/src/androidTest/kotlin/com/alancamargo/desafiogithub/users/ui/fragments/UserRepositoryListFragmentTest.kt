package com.alancamargo.desafiogithub.users.ui.fragments

import com.alancamargo.desafiogithub.users.ui.robots.repositories.given
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class UserRepositoryListFragmentTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun whenLoading_shouldShowShimmer() {
        given {
            launchWithDelayedWebResponse()
        } then {
            showShimmer()
        }
    }

    @Test
    fun withSuccess_shouldShowCorrectNumberOfUsers() {
        given {
            launchWithSuccess()
        } then {
            repositoryCountIs(count = 5)
        }
    }

    @Test
    fun withNetworkError_shouldShowNetworkErrorMessage() {
        given {
            launchDisconnected()
        } then {
            showNetworkError()
        }
    }

    @Test
    fun withGenericError_shouldShowGenericErrorMessage() {
        given {
            launchWithGenericError()
        } then {
            showGenericError()
        }
    }
}
