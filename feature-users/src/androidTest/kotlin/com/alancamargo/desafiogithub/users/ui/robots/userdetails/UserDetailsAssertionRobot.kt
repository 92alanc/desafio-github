package com.alancamargo.desafiogithub.users.ui.robots.userdetails

import com.alancamargo.desafiogithub.core.test.ui.assertViewIsDisplayed
import com.alancamargo.desafiogithub.users.R

internal class UserDetailsAssertionRobot {

    fun showUserDetails() {
        assertViewIsDisplayed("92alanc")
        assertViewIsDisplayed("Alan Camargo")
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
