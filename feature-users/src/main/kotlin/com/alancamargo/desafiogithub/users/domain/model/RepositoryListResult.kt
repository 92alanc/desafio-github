package com.alancamargo.desafiogithub.users.domain.model

import com.alancamargo.desafiogithub.domain.repository.model.Repository

internal sealed class RepositoryListResult {

    data class Success(val repositories: List<Repository>) : RepositoryListResult()

    object NoResults : RepositoryListResult()

    object NetworkError : RepositoryListResult()

    object GenericError : RepositoryListResult()
}
