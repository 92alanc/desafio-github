package com.alancamargo.desafiogithub.users.ui.robots.repositories

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.desafiogithub.core.constants.EXTRA_ARGS
import com.alancamargo.desafiogithub.core.test.web.delayWebResponse
import com.alancamargo.desafiogithub.core.test.web.disconnect
import com.alancamargo.desafiogithub.core.test.web.mockWebError
import com.alancamargo.desafiogithub.core.test.web.mockWebResponse
import com.alancamargo.desafiogithub.users.ui.FragmentTestActivity
import com.alancamargo.desafiogithub.users.ui.fragments.UserRepositoryListFragment
import java.net.HttpURLConnection

internal fun given(
    block: UserRepositoryListFragmentRobot.() -> Unit
): UserRepositoryListFragmentRobot = UserRepositoryListFragmentRobot().apply(block)

internal class UserRepositoryListFragmentRobot {

    private val fragmentArgs = UserRepositoryListFragment.Args(ownerUserName = "user")

    fun launchWithSuccess() {
        launchFragmentWithPrecondition<UserRepositoryListFragment>(fragmentArgs) {
            mockWebResponse(jsonAssetPath = "repositories.json")
        }
    }

    fun launchWithDelayedWebResponse() {
        launchFragmentWithPrecondition<UserRepositoryListFragment>(fragmentArgs) {
            delayWebResponse()
        }
    }

    fun launchDisconnected() {
        launchFragmentWithPrecondition<UserRepositoryListFragment>(fragmentArgs) {
            disconnect()
        }
    }

    fun launchWithGenericError() {
        launchFragmentWithPrecondition<UserRepositoryListFragment>(fragmentArgs) {
            mockWebError(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }
    }

    infix fun then(
        assertion: UserRepositoryListAssertionRobot.() -> Unit
    ): UserRepositoryListAssertionRobot {
        Thread.sleep(200) // Tests won't pass consistently without this
        return UserRepositoryListAssertionRobot().apply(assertion)
    }

    private inline fun <reified F : Fragment> launchFragmentWithPrecondition(
        args: Parcelable,
        beforeLaunch: () -> Unit
    ) {
        beforeLaunch()
        launchFragment<F>(args)
    }

    private inline fun <reified F : Fragment> launchFragment(args: Parcelable) {
        val bundle = bundleOf(EXTRA_ARGS to args)
        launchFragmentInHiltContainer<F>(bundle)
    }

    private inline fun <reified F : Fragment> launchFragmentInHiltContainer(bundle: Bundle) {
        val startActivityIntent = Intent.makeMainActivity(
            ComponentName(
                InstrumentationRegistry.getInstrumentation().targetContext,
                FragmentTestActivity::class.java
            )
        )

        ActivityScenario.launch<FragmentTestActivity>(startActivityIntent).onActivity { activity ->
            val fragment: Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
                F::class.java.classLoader!!,
                F::class.java.name
            )
            fragment.arguments = bundle
            activity.supportFragmentManager.commitNow {
                add(android.R.id.content, fragment, "")
            }
        }
    }
}
