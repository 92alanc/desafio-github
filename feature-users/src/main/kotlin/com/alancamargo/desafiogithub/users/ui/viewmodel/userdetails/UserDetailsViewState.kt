package com.alancamargo.desafiogithub.users.ui.viewmodel.userdetails

import com.alancamargo.desafiogithub.domain.user.model.User
import com.alancamargo.desafiogithub.users.ui.model.UserDetailsError

internal data class UserDetailsViewState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: UserDetailsError? = null
) {

    fun onLoading() = copy(isLoading = true)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onUserDetailsReceived(user: User) = copy(
        user = user,
        error = null
    )

    fun onError(error: UserDetailsError) = copy(
        user = null,
        error = error
    )
}
