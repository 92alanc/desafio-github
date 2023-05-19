package com.alancamargo.desafiogithub.users.ui.viewmodel.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.desafiogithub.core.di.IoDispatcher
import com.alancamargo.desafiogithub.core.log.Logger
import com.alancamargo.desafiogithub.users.domain.model.UserResult
import com.alancamargo.desafiogithub.users.domain.usecase.GetUserUseCase
import com.alancamargo.desafiogithub.users.ui.model.UserDetailsError
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
internal class UserDetailsViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(UserDetailsViewState())
    private val _action = MutableSharedFlow<UserDetailsViewAction>()

    val state: StateFlow<UserDetailsViewState> = _state
    val action: SharedFlow<UserDetailsViewAction> = _action

    fun getUserDetails(userName: String) {
        viewModelScope.launch(dispatcher) {
            getUserUseCase(userName).onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch { t ->
                logger.error(t)
                _state.update { it.onError(UserDetailsError.GENERIC_ERROR) }
            }.collect(::handleResult)
        }
    }

    fun onClickBack() {
        viewModelScope.launch(dispatcher) {
            _action.emit(UserDetailsViewAction.Finish)
        }
    }

    private fun handleResult(result: UserResult) {
        when (result) {
            is UserResult.Success -> _state.update { it.onUserDetailsReceived(result.user) }

            is UserResult.UserNotFound -> _state.update {
                it.onError(UserDetailsError.USER_NOT_FOUND)
            }

            is UserResult.NetworkError -> _state.update {
                it.onError(UserDetailsError.NETWORK_ERROR)
            }

            is UserResult.GenericError -> _state.update {
                it.onError(UserDetailsError.GENERIC_ERROR)
            }
        }
    }
}
