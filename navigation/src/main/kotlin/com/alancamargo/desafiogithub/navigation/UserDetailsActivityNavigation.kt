package com.alancamargo.desafiogithub.navigation

import android.content.Context

interface UserDetailsActivityNavigation {

    fun startActivity(context: Context, userName: String)
}
