package com.alancamargo.desafiogithub.core.test.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.allOf

fun performClick(text: String) {
    onView(
        allOf(
            withText(text),
            isDisplayed()
        )
    ).perform(click())
}

fun clickMenuItem(itemText: String) {
    openActionBarOverflowOrOptionsMenu(
        InstrumentationRegistry.getInstrumentation().targetContext
    )

    performClick(itemText)
}
