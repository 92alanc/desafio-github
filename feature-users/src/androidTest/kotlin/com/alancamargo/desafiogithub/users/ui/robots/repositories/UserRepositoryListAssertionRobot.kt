package com.alancamargo.desafiogithub.users.ui.robots.repositories

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alancamargo.desafiogithub.core.test.ui.assertViewIsDisplayed
import com.alancamargo.desafiogithub.core.test.ui.withRecyclerViewItemCount
import com.alancamargo.desafiogithub.users.R

internal class UserRepositoryListAssertionRobot {

    fun repositoryCountIs(count: Int) {
        onView(withId(R.id.recyclerView)).check(withRecyclerViewItemCount(count))
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
