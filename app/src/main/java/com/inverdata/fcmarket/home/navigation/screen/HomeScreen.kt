package com.inverdata.fcmarket.home.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.inverdata.fcmarket.core.commom.Constants.homeTabs
import com.inverdata.fcmarket.core.presentation.DefaultLayout
import com.inverdata.fcmarket.home.components.TabNavigationItem
import com.inverdata.fcmarket.home.navigation.tabs.HomeTabs

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(
            HomeTabs.Dashboard.tab,
            tabDisposable = { tabNavigator ->
                TabDisposable(navigator = tabNavigator, tabs = homeTabs)
            }
        ) {
            DefaultLayout(
                bottomBar = {
                    NavigationBar {
                        homeTabs.forEach { tab ->
                            TabNavigationItem(tab)
                        }
                    }
                },
                floatingActionButton = {
                    if (it.current.options.title == "Dashboard") {
                        FloatingActionButton(
                            onClick = {}
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