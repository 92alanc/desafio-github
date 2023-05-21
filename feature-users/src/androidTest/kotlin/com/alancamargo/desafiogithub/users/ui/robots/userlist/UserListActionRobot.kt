package com.alancamargo.desafiogithub.users.ui.robots.userlist

import com.alancamargo.desafiogithub.core.test.ui.clickMenuItem
import com.alancamargo.desafiogithub.core.test.ui.performClick
import com.alancamargo.desafiogithub.users.ui.UserListActivityTest

internal class UserListActionRobot(private val testSuite: UserListActivityTest) {

    fun clickUser(userName: String) {
        performClick(userName)
    }

    fun clickAppInfo() {
        clickMenuItem(itemText = "Sobre o aplicativo")
    }

    infix fun then(
        assertion: UserListAssertionRobot.() -> Unit
    ): UserListAssertionRobot {
        return UserListAssertionRobot(testSuite).apply(assertion)
    }
}
