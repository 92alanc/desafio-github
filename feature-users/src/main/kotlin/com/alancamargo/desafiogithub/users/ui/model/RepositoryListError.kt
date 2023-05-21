package com.alancamargo.desafiogithub.users.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alancamargo.desafiogithub.users.R
import com.alancamargo.desafiogithub.core.design.R as R2

internal enum class RepositoryListError(
    @DrawableRes val icon: Int,
    @StringRes val message: Int
) {

    NO_RESULTS(
        icon = R2.drawable.ic_no_results_error,
        message = R.string.message_no_repositories_found
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
