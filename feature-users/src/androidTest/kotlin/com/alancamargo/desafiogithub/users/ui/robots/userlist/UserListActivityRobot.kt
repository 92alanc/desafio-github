package com.alancamargo.desafiogithub.users.ui.robots.userlist

import androidx.test.core.app.ActivityScenario
import com.alancamargo.desafiogithub.core.test.web.delayWebResponse
import com.alancamargo.desafiogithub.core.test.web.disconnect
import com.alancamargo.desafiogithub.core.test.web.mockWebError
import com.alancamargo.desafiogithub.core.test.web.mockWebResponse
import com.alancamargo.desafiogithub.users.ui.UserListActivity
import com.alancamargo.desafiogithub.users.ui.UserListActivityTest
import java.net.HttpURLConnection

internal fun UserListActivityTest.given(
    block: UserListActivityRobot.() -> Unit
): UserListActivityRobot = UserListActivityRobot(testSuite = this).apply(block)

internal class UserListActivityRobot(private val testSuite: UserListActivityTest) {

    fun launchWithSuccess() {
        launchWithPrecondition {
            mockWebResponse(jsonAssetPath = "users.json")
        }
    }

    fun launchWithDelayedWebResponse() {
        launchWithPrecondition {
            delayWebResponse()
        }
    }

    fun launchDisconnected() {
        launchWithPrecondition {
            disconnect()
        }
    }

    fun launchWithGenericError() {
        launchWithPrecondition {
            mockWebError(HttpURLConnection.HTTP_NOT_FOUND)
        }
    }

    infix fun withAction(
        action: UserListActionRobot.() -> Unit
    ): UserListActionRobot {
        return UserListActionRobot(testSuite).apply(action)
    }

    infix fun then(
        assertion: UserListAssertionRobot.() -> Unit
    ): UserListAssertionRobot {
        return UserListAssertionRobot(testSuite).apply(assertion)
    }

    private fun launchWithPrecondition(beforeLaunch: () -> Unit) {
        beforeLaunch()
        launch()
    }

    private fun launch() {
        ActivityScenario.launch(UserListActivity::class.java)
    }
}
