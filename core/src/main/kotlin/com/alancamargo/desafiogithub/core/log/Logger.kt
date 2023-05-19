package com.alancamargo.desafiogithub.core.log

interface Logger {

    fun debug(message: String)

    fun error(throwable: Throwable)
}
