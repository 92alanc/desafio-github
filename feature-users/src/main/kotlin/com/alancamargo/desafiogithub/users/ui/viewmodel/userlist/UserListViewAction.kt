package com.alancamargo.desafiogithub.users.ui.viewmodel.userlist

internal sealed class UserListViewAction {

    data class OpenUserDetails(val userName: String) : UserListViewAction()

    object ShowAppInfo : UserListViewAction()
}
