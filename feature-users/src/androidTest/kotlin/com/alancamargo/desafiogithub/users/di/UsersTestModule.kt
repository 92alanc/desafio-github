package com.alancamargo.desafiogithub.users.di

import com.alancamargo.desafiogithub.core.design.di.CoreDesignModule
import com.alancamargo.desafiogithub.core.design.tools.DialogueHelper
import com.alancamargo.desafiogithub.core.di.BaseUrl
import com.alancamargo.desafiogithub.core.di.BaseUrlModule
import com.alancamargo.desafiogithub.core.test.web.mockWebServer
import com.alancamargo.desafiogithub.navigation.UserDetailsActivityNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        UsersNavigationModule::class,
        CoreDesignModule::class,
        BaseUrlModule::class
    ]
)
internal object UsersTestModule {

    @Provides
    @Singleton
    fun provideMockUserDetailsActivityNavigation(): UserDetailsActivityNavigation {
        return mockk(relaxed = true)
    }

    @Provides
    @Singleton
    fun provideMockDialogueHelper(): DialogueHelper = mockk(relaxed = true)

    @Provides
    @Singleton
    @BaseUrl
    fun provideMockBaseUrl(): String = runBlocking(Dispatchers.IO) {
        mockWebServer.url("/").toString()
    }
}
