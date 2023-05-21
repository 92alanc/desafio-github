package com.alancamargo.desafiogithub.users.domain.model

import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

internal sealed class UserSummaryListResult {

    data class Success(val users: List<UserSummary>) : UserSummaryListResult()

    object Empty : UserSummaryListResult()

    object NetworkError : UserSummaryListResult()

    object GenericError : UserSummaryListResult()
}
