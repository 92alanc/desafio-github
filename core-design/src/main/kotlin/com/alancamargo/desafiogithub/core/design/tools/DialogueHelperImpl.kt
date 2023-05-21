package com.alancamargo.desafiogithub.core.design.tools

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.alancamargo.desafiogithub.core.design.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
class DialogueHelperImpl @Inject constructor() : DialogueHelper {

    override fun showDialogue(context: Context, titleRes: Int, messageRes: Int) {
        MaterialAlertDialogBuilder(context).setTitle(titleRes)
            .setMessage(messageRes)
            .setNeutralButton(R.string.ok, null)
            .create()
            .show()
    }
}
