package com.inverdata.fcmarket.login.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.inverdata.fcmarket.home.navigation.screen.HomeScreen
import com.inverdata.fcmarket.login.presentation.components.LoginComponent

class LoginScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<LoginViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(viewModel.sharedFlow) {
            viewModel.sharedFlow.collect { action ->
                when (action) {
                    ScreenEvent.NavigateToHome -> {
                        navigator.replaceAll(HomeScreen())
                    }
                }
            }
        }

        LoginComponent(
            state = state,
            newLogin = viewModel.newLogin,
            onEvent = viewModel::onEvent
        )
    }
}