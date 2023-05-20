package com.alancamargo.desafiogithub.core.design.tools

import android.content.Context
import androidx.annotation.StringRes

interface DialogueHelper {

    fun showDialogue(
        context: Context,
        @StringRes titleRes: Int,
        @StringRes messageRes: Int
    )
}
