package com.alancamargo.desafiogithub.users.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal enum class UserDetailsError(
    @DrawableRes val icon: Int,
    @StringRes val message: Int
) {

    USER_NOT_FOUND(
        icon = 0,
        message = 0
    ),

    NETWORK_ERROR(
        icon = 0,
        message = 0
    ),

    GENERIC_ERROR(
        icon = 0,
        message = 0
    )
}
