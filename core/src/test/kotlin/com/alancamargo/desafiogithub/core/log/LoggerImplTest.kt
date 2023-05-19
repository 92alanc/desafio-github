package com.alancamargo.desafiogithub.core.log

import android.util.Log
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

private const val TAG = "LOG_ALAN"
private const val LOG_MESSAGE = "message"

class LoggerImplTest {

    private val logger = LoggerImpl()

    @Before
    fun setUp() {
        mockkStatic(Log::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(Log::class)
    }

    @Test
    fun `debug should log message`() {
        // WHEN
        logger.debug(LOG_MESSAGE)

        // THEN
        verify { Log.d(TAG, LOG_MESSAGE) }
    }

    @Test
    fun `error should log exception and message`() {
        // GIVEN
        val exception = Throwable()

        // WHEN
        logger.error(exception)

        // THEN
        verify { Log.e(TAG, exception.message, exception) }
    }
}
