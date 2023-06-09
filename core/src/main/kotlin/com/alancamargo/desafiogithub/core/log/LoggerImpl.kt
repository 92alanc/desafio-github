package com.alancamargo.desafiogithub.core.log

import android.util.Log
import javax.inject.Inject

private const val LOG_TAG = "LOG_ALAN"

internal class LoggerImpl @Inject constructor() : Logger {

    override fun debug(message: String) {
        Log.d(LOG_TAG, message)
    }

    override fun error(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message, throwable)
    }
}
