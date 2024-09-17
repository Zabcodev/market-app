package com.inverdata.fcmarket.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.inverdata.fcmarket.session.presentation.SplashScreen
import com.inverdata.fcmarket.core.presentation.theme.FCMarketTheme

@Composable
fun App() {
    FCMarketTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Navigator(screen = SplashScreen()) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}