package com.alancamargo.desafiogithub.core.network

interface ApiProvider {

    fun <T> provideService(clazz: Class<T>): T
}
