package com.alancamargo.desafiogithub.users.di

import com.alancamargo.desafiogithub.navigation.UserDetailsActivityNavigation
import com.alancamargo.desafiogithub.users.ui.navigation.UserDetailsActivityNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class UsersNavigationModule {

    @Binds
    @ActivityScoped
    abstract fun bindUserDetailsActivityNavigation(
        impl: UserDetailsActivityNavigationImpl
    ): UserDetailsActivityNavigation
}
