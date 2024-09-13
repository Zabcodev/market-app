package com.inverdata.fcmarket.login.presentation

data class LoginState(
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val remember: Boolean = false,
    val loginError: String? = null,
    val logged: Boolean = false,
)
