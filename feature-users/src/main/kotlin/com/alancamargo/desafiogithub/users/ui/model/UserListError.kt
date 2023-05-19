package com.alancamargo.desafiogithub.users.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal enum class UserListError(
    @DrawableRes val icon: Int,
    @StringRes val message: Int
) {

    NO_RESULTS(
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
