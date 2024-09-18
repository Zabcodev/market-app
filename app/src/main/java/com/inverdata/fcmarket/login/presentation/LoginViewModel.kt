package com.inverdata.fcmarket.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.login.data.mappers.toSession
import com.inverdata.fcmarket.login.domain.model.Login
import com.inverdata.fcmarket.login.domain.repository.LoginRepository
import com.inverdata.fcmarket.login.domain.validator.LoginValidator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ScreenModel {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<ScreenEvent>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    var newLogin: Login? by mutableStateOf(null)
        private set

    init {
        newLogin = Login(
            email = "",
            password = "",
            remember = false
        )
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                newLogin = newLogin?.copy(
                    email = event.value
                )
            }

            is LoginEvent.OnPasswordChanged -> {
                newLogin = newLogin?.copy(
                    password = event.value
                )
            }

            is LoginEvent.OnRememberChanged -> {
                newLogin = newLogin?.copy(
                    remember = event.value
                )
            }

            LoginEvent.OnButtonClicked -> {
                newLogin?.let { login ->
                    val result = LoginValidator.validateLogin(login)
                    val errors = listOfNotNull(
                        result.emailError,
                        result.passwordError
                    )

                    if (errors.isEmpty()) {
                        screenModelScope.launch {
                            _state.update { state -> state.copy(isLoading = true) }
                            repository.login(login).collect { response ->
                                when (response) {
                                    is ApiRequest.Failure -> {
                                        _state.update { state ->
                                            state.copy(
                                                isLoading = false,
                                                loginError = response.error.message.error,
                                                logged = false
                                            )
                                        }
                                        // println(response.error.message.error)
                                    }

                                    is ApiRequest.Success -> {
                                        _state.update { state ->
                                            state.copy(
                                                logged = true,
                                                loginError = null,
                                                isLoading = false
                                            )
                                        }
                                        repository.insertSession(
                                            response.data.copy(userEmail = login.email).toSession()
                                        )
                                        _sharedFlow.emit(ScreenEvent.NavigateToHome)
                                        // println(response.data.toString())
                                    }
                                }
                            }
                        }
                    } else {
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                emailError = result.emailError,
                                passwordError = result.passwordError
                            )
                        }
                    }
                }
            }
        }
    }

}