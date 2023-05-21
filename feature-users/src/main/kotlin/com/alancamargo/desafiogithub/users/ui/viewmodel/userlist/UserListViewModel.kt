package com.alancamargo.desafiogithub.users.ui.viewmodel.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.desafiogithub.core.di.IoDispatcher
import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.domain.model.UserSummaryListResult
import com.alancamargo.desafiogithub.users.domain.usecase.GetUsersUseCase
import com.alancamargo.desafiogithub.users.ui.model.UserListError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(UserListViewState())
    private val _action = MutableSharedFlow<UserListViewAction>()

    val state: StateFlow<UserListViewState> = _state
    val action: SharedFlow<UserListViewAction> = _action

    fun getUsers() {
        viewModelScope.launch(dispatcher) {
            getUsersUseCase().onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch { t ->
                logger.error(t)
                _state.update { it.onError(UserListError.GENERIC_ERROR) }
            }.collect(::handleResult)
        }
    }

    fun onClickUser(user: UserSummary) {
        viewModelScope.launch(dispatcher) {
            _action.emit(UserListViewAction.OpenUserDetails(user.userName))
        }
    }

    fun onClickShowAppInfo() {
        viewModelScope.launch(dispatcher) {
            _action.emit(UserListViewAction.ShowAppInfo)
        }
    }

    private fun handleResult(result: UserSummaryListResult) {
        when (result) {
            is UserSummaryListResult.Success -> _state.update {
                it.onUsersReceived(result.users)
            }

            is UserSummaryListResult.Empty -> _state.update {
                it.onError(UserListError.NO_RESULTS)
            }

            is UserSummaryListResult.NetworkError -> _state.update {
                it.onError(UserListError.NETWORK_ERROR)
            }

            is UserSummaryListResult.GenericError -> _state.update {
                it.onError(UserListError.GENERIC_ERROR)
            }
        }
    }
}
