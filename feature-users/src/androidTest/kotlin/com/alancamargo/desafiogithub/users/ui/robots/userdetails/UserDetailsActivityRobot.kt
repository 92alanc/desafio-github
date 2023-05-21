package com.alancamargo.desafiogithub.users.ui.robots.userdetails

import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.desafiogithub.core.test.ui.launchActivityWithPrecondition
import com.alancamargo.desafiogithub.core.test.web.delayWebResponse
import com.alancamargo.desafiogithub.core.test.web.disconnect
import com.alancamargo.desafiogithub.core.test.web.mockWebError
import com.alancamargo.desafiogithub.core.test.web.mockWebResponse
import com.alancamargo.desafiogithub.users.ui.UserDetailsActivity
import java.net.HttpURLConnection

internal fun given(
    block: UserDetailsActivityRobot.() -> Unit
): UserDetailsActivityRobot = UserDetailsActivityRobot().apply(block)

internal class UserDetailsActivityRobot {

    fun launchWithSuccess() {
        launchWithPrecondition {
            mockWebResponse(jsonAssetPath = "user_details.json")
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
            mockWebError(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }
    }

    infix fun then(
        assertion: UserDetailsAssertionRobot.() -> Unit
    ): UserDetailsAssertionRobot {
        return UserDetailsAssertionRobot().apply(assertion)
    }

    private fun launchWithPrecondition(beforeLaunch: () -> Unit) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val args = UserDetailsActivity.Args(userName = "92alanc")
        val intent = UserDetailsActivity.getIntent(context, args)
        launchActivityWithPrecondition<UserDetailsActivity>(intent, beforeLaunch)
    }
}
