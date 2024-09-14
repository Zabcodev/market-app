package com.inverdata.fcmarket.session.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.inverdata.fcmarket.R
import com.inverdata.fcmarket.home.navigation.screen.HomeScreen
import com.inverdata.fcmarket.login.presentation.LoginScreen

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<SplashViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(viewModel.sharedFlow) {
            viewModel.sharedFlow.collect { screen ->
                when (screen) {
                    SplashDestination.Home -> {
                        navigator.replaceAll(HomeScreen())
                    }

                    SplashDestination.Login -> {
                        navigator.replaceAll(LoginScreen())
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo_fc),
                contentDescription = stringResource(R.string.app_name)
            )
        }

    }
}