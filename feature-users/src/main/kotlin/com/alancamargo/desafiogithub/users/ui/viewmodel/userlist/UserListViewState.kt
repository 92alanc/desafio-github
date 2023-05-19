package com.alancamargo.desafiogithub.users.ui.viewmodel.userlist

import com.alancamargo.desafiogithub.users.ui.model.UiUserSummary
import com.alancamargo.desafiogithub.users.ui.model.UserListError

internal data class UserListViewState(
    val isLoading: Boolean = false,
    val users: List<UiUserSummary>? = null,
    val error: UserListError? = null
) {

    fun onLoading() = copy(isLoading = true)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onUsersReceived(users: List<UiUserSummary>) = copy(
        users = users,
        error = null
    )

    fun onError(error: UserListError) = copy(
        users = null,
        error = error
    )
}
