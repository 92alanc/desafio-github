package com.alancamargo.desafiogithub.users.ui.viewmodel.userrepositorylist

import com.alancamargo.desafiogithub.domain.repository.model.Repository
import com.alancamargo.desafiogithub.users.ui.model.RepositoryListError

internal data class UserRepositoryListViewState(
    val isLoading: Boolean = false,
    val repositories: List<Repository>? = null,
    val error: RepositoryListError? = null
) {

    fun onLoading() = copy(isLoading = true)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onRepositoriesReceived(repositories: List<Repository>) = copy(
        repositories = repositories,
        error = null
    )

    fun onError(error: RepositoryListError) = copy(
        repositories = null,
        error = error
    )
}
