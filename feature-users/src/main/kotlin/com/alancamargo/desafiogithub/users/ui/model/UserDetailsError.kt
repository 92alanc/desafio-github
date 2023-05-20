package com.alancamargo.desafiogithub.users.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alancamargo.desafiogithub.users.R
import com.alancamargo.desafiogithub.core.design.R as R2

internal enum class UserDetailsError(
    @DrawableRes val icon: Int,
    @StringRes val message: Int
) {

    USER_NOT_FOUND(
        icon = R2.drawable.ic_no_results_error,
        message = R.string.message_user_not_found
    ),

    NETWORK_ERROR(
        icon = R2.drawable.ic_network_error,
        message = R2.string.message_network_error
    ),

    GENERIC_ERROR(
        icon = R2.drawable.ic_generic_error,
        message = R2.string.message_generic_error
    )
}
