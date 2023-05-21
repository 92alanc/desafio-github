package com.alancamargo.desafiogithub.users.ui.navigation

import android.content.Context
import com.alancamargo.desafiogithub.navigation.UserDetailsActivityNavigation
import com.alancamargo.desafiogithub.users.ui.UserDetailsActivity
import javax.inject.Inject

internal class UserDetailsActivityNavigationImpl @Inject constructor(
) : UserDetailsActivityNavigation {

    override fun startActivity(context: Context, userName: String) {
        val args = UserDetailsActivity.Args(userName)
        val intent = UserDetailsActivity.getIntent(context, args)
        context.startActivity(intent)
    }
}
