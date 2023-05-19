package com.alancamargo.desafiogithub.users.ui.viewmodel.userlist

import com.alancamargo.desafiogithub.users.ui.model.UiUserSummary

internal sealed class UserListViewAction {

    data class OpenUserDetails(val userSummary: UiUserSummary) : UserListViewAction()

    object ShowAppInfo : UserListViewAction()
}
