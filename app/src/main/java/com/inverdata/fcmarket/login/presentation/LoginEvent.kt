package com.inverdata.fcmarket.login.presentation

sealed interface LoginEvent {
    data class OnEmailChanged(val value: String): LoginEvent
    data class OnPasswordChanged(val value: String): LoginEvent
    data class OnRememberChanged(val value: Boolean): LoginEvent
    data object OnButtonClicked: LoginEvent
}

sealed interface ScreenEvent {
    data object NavigateToHome: ScreenEvent
}