package com.alancamargo.desafiogithub.core.test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario

inline fun <reified A : AppCompatActivity> launchActivityWithPrecondition(
    intent: Intent,
    beforeLaunch: () -> Unit
) {
    beforeLaunch()
    launchActivity<A>(intent)
}

inline fun <reified A : AppCompatActivity> launchActivityWithPrecondition(
    beforeLaunch: () -> Unit
) {
    beforeLaunch()
    launchActivity<A>()
}

inline fun <reified A : AppCompatActivity> launchActivity(intent: Intent) {
    ActivityScenario.launch<A>(intent)
}

inline fun <reified A : AppCompatActivity> launchActivity() {
    ActivityScenario.launch(A::class.java)
}
