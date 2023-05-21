package com.alancamargo.desafiogithub.core.design.di

import com.alancamargo.desafiogithub.core.design.tools.DialogueHelper
import com.alancamargo.desafiogithub.core.design.tools.DialogueHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class CoreDesignModule {

    @Binds
    @ActivityScoped
    abstract fun bindDialogueHelper(impl: DialogueHelperImpl): DialogueHelper
}
