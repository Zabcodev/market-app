package com.inverdata.fcmarket.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.login.domain.model.Login
import com.inverdata.fcmarket.login.domain.repository.LoginRepository
import com.inverdata.fcmarket.login.domain.validator.LoginValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    var newLogin: Login? by mutableStateOf(null)
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnButtonClicked -> {
                newLogin?.let { login ->
                    val result = LoginValidator.validateLogin(login)
                    val errors = listOfNotNull(
                        result.emailError,
                        result.passwordError
                    )

                    if (errors.isEmpty()) {
                        viewModelScope.launch {
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
                                    }
                                    is ApiRequest.Success -> {
                                        _state.update { state ->
                                            state.copy(
                                                logged = true,
                                                loginError = null,
                                            )
                                        }
                                        println(response.data.toString())
                                        println("Usuario Logueado: ${state.value.logged}")
                                    }
                                }
                            }
                            _state.update { state -> state.copy(isLoading = true) }
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
            is LoginEvent.OnEmailChanged -> {
                newLogin = newLogin?.copy(
                    email = event.email
                )
            }
            is LoginEvent.OnPasswordChanged -> {
                newLogin = newLogin?.copy(
                    password = event.password
                )
            }
            is LoginEvent.OnRememberChanged -> {
                newLogin = newLogin?.copy(
                    remember = event.remember
                )
            }
        }
    }

}