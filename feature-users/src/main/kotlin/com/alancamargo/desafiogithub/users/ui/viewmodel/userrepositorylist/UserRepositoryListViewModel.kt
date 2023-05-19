package com.alancamargo.desafiogithub.users.ui.viewmodel.userrepositorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.desafiogithub.core.di.IoDispatcher
import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.users.domain.model.RepositoryListResult
import com.alancamargo.desafiogithub.users.domain.usecase.GetUserRepositoriesUseCase
import com.alancamargo.desafiogithub.users.ui.model.RepositoryListError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserRepositoryListViewModel @Inject constructor(
    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(UserRepositoryListViewState())

    val state: StateFlow<UserRepositoryListViewState> = _state

    fun getUserRepositories(ownerUserName: String) {
        viewModelScope.launch(dispatcher) {
            getUserRepositoriesUseCase(ownerUserName).onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch { t ->
                logger.error(t)
                _state.update { it.onError(RepositoryListError.GENERIC_ERROR) }
            }.collect(::handleResult)
        }
    }

    private fun handleResult(result: RepositoryListResult) {
        when (result) {
            is RepositoryListResult.Success -> _state.update {
                it.onRepositoriesReceived(result.repositories)
            }

            is RepositoryListResult.NoResults -> _state.update {
                it.onError(RepositoryListError.NO_RESULTS)
            }

            is RepositoryListResult.NetworkError -> _state.update {
                it.onError(RepositoryListError.NETWORK_ERROR)
            }

            is RepositoryListResult.GenericError -> _state.update {
                it.onError(RepositoryListError.GENERIC_ERROR)
            }
        }
    }
}
