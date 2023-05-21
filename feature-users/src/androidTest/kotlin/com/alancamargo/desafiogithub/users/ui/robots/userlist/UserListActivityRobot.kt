package com.alancamargo.desafiogithub.users.ui.robots.userlist

import com.alancamargo.desafiogithub.core.test.ui.launchActivityWithPrecondition
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
        launchActivityWithPrecondition<UserListActivity> {
            mockWebResponse(jsonAssetPath = "users.json")
        }
    }

    fun launchWithDelayedWebResponse() {
        launchActivityWithPrecondition<UserListActivity> {
            delayWebResponse()
        }
    }

    fun launchDisconnected() {
        launchActivityWithPrecondition<UserListActivity> {
            disconnect()
        }
    }

    fun launchWithGenericError() {
        launchActivityWithPrecondition<UserListActivity> {
            mockWebError(HttpURLConnection.HTTP_INTERNAL_ERROR)
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
}
