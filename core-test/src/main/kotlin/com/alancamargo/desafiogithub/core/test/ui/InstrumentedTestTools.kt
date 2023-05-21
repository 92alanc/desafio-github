package com.alancamargo.desafiogithub.core.test.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario

inline fun <reified A : AppCompatActivity> launchActivityWithPrecondition(
    beforeLaunch: () -> Unit
) {
    beforeLaunch()
    launchActivity<A>()
}

inline fun <reified A : AppCompatActivity> launchActivity() {
    ActivityScenario.launch(A::class.java)
}
