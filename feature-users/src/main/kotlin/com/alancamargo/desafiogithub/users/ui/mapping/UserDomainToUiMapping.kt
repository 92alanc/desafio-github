package com.alancamargo.desafiogithub.users.ui.mapping

import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.ui.model.UiUserSummary

internal fun UserSummary.toUi() = UiUserSummary(
    userName = userName,
    profilePictureUrl = profilePictureUrl
)
