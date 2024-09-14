package com.inverdata.fcmarket.configuration.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.inverdata.fcmarket.home.navigation.tabs.HomeTabs

object ConfigurationTab: Tab {
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Configuration Tab"
            )
        }
    }
}