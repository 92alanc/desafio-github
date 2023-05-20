package com.alancamargo.desafiogithub.core.design.tools

import android.content.Context
import com.alancamargo.desafiogithub.core.design.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

internal class DialogueHelperImpl @Inject constructor() : DialogueHelper {

    override fun showDialogue(context: Context, titleRes: Int, messageRes: Int) {
        MaterialAlertDialogBuilder(context).setTitle(titleRes)
            .setMessage(messageRes)
            .setNeutralButton(R.string.ok, null)
            .create()
            .show()
    }
}
