package com.inverdata.fcmarket.login.presentation

sealed interface LoginEvent {
    data class OnEmailChanged(val email: String): LoginEvent
    data class OnPasswordChanged(val password: String): LoginEvent
    data class OnRememberChanged(val remember: Boolean): LoginEvent
    data object OnButtonClicked: LoginEvent
}