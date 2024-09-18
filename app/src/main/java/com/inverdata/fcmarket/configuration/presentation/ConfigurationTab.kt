package com.inverdata.fcmarket.configuration.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.inverdata.fcmarket.configuration.presentation.components.ConfigurationComponent
import com.inverdata.fcmarket.home.navigation.tabs.HomeTabs
import com.inverdata.fcmarket.login.presentation.LoginScreen

object ConfigurationTab : Tab {
    private fun readResolve(): Any = ConfigurationTab
    override val options: TabOptions
        @Composable
        get() {
            val title = HomeTabs.Configuration.title
            val icon = rememberVectorPainter(HomeTabs.Configuration.icon)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ConfigurationViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        ConfigurationComponent(
            state = state,
            onLogOutClick = {
                viewModel.deleteSession()
                navigator.parent?.replaceAll(LoginScreen())
            }
        )
    }
}