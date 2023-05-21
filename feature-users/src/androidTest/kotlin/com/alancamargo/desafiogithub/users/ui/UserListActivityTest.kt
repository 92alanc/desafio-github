package com.alancamargo.desafiogithub.users.ui

import com.alancamargo.desafiogithub.core.design.tools.DialogueHelper
import com.alancamargo.desafiogithub.navigation.UserDetailsActivityNavigation
import com.alancamargo.desafiogithub.users.ui.robots.userlist.given
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class UserListActivityTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockDialogueHelper: DialogueHelper

    @Inject
    lateinit var mockUserDetailsActivityNavigation: UserDetailsActivityNavigation

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
            userCountIs(count = 3)
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

    @Test
    fun whenClickShowAppInfo_shouldShowAppInfo() {
        given {
            launchWithSuccess()
        } withAction {
            clickAppInfo()
        } then {
            showAppInfo()
        }
    }

    @Test
    fun whenClickUser_shouldNavigateToUserDetails() {
        given {
            launchWithSuccess()
        } withAction {
            clickUser(userName = "defunkt")
        } then {
            navigateToUserDetails(userName = "defunkt")
        }
    }
}
