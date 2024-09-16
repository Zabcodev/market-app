package com.inverdata.fcmarket.home.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.inverdata.fcmarket.core.commom.Constants.homeTabs
import com.inverdata.fcmarket.core.presentation.DefaultLayout
import com.inverdata.fcmarket.customer.presentation.CustomerScreen
import com.inverdata.fcmarket.home.components.TabNavigationItem
import com.inverdata.fcmarket.home.navigation.tabs.HomeTabs

@OptIn(ExperimentalMaterial3Api::class)
class HomeScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(
            HomeTabs.Dashboard.tab,
            tabDisposable = { tabNavigator ->
                TabDisposable(navigator = tabNavigator, tabs = homeTabs)
            }
        ) {
            val navigator = LocalNavigator.currentOrThrow
            DefaultLayout(
                bottomBar = {
                    NavigationBar {
                        homeTabs.forEach { tab ->
                            TabNavigationItem(tab)
                        }
                    }
                },
                topBar = {
                    TopAppBar(
                        title = {
                            Text(it.current.options.title)
                        }
                    )
                },
                floatingActionButton = {
                    if (it.current.options.title == "Dashboard") {
                        FloatingActionButton(
                            onClick = {
                                navigator.parent?.push(CustomerScreen())
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add icon"
                            )
                        }
                    }
                }
            ) {
                CurrentTab()
            }
        }

    }
}