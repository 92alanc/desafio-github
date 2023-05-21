package com.alancamargo.desafiogithub.users.ui

import com.alancamargo.desafiogithub.users.ui.robots.userdetails.given
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class UserDetailsActivityTest {

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
    fun withSuccess_shouldShowUserDetails() {
        given {
            launchWithSuccess()
        } then {
            showUserDetails()
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
