package com.alancamargo.desafiogithub.users.ui.robots.userlist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alancamargo.desafiogithub.core.test.ui.assertViewIsDisplayed
import com.alancamargo.desafiogithub.core.test.ui.withRecyclerViewItemCount
import com.alancamargo.desafiogithub.users.R
import com.alancamargo.desafiogithub.users.ui.UserListActivityTest
import io.mockk.verify

internal class UserListAssertionRobot(private val testSuite: UserListActivityTest) {

    fun userCountIs(count: Int) {
        onView(withId(R.id.recyclerView)).check(withRecyclerViewItemCount(count))
    }

    fun navigateToUserDetails(userName: String) {
        verify {
            testSuite.mockUserDetailsActivityNavigation.startActivity(
                context = any(),
                userName = userName
            )
        }
    }

    fun showAppInfo() {
        verify {
            testSuite.mockDialogueHelper.showDialogue(
                context = any(),
                titleRes = R.string.app_info_label,
                messageRes = R.string.app_info
            )
        }
    }

    fun showShimmer() {
        assertViewIsDisplayed(R.id.shimmerContainer)
    }

    fun showNetworkError() {
        assertViewIsDisplayed("Parece que você está desconectado. Verifique sua conexão com a internet e tente novamente")
    }

    fun showGenericError() {
        assertViewIsDisplayed("Algo deu errado. Tente novamente mais tarde")
    }
}
