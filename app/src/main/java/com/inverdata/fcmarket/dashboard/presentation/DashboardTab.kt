package com.inverdata.fcmarket.dashboard.presentation

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

object DashboardTab: Tab {
    private fun readResolve(): Any = DashboardTab
    override val options: TabOptions
        @Composable
        get() {
            val title = HomeTabs.Dashboard.title
            val icon = rememberVectorPainter(HomeTabs.Dashboard.icon)

            return remember {
                TabOptions(
                    index = 0u,
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
                text = "Dashboard Tab"
            )
        }
    }
}