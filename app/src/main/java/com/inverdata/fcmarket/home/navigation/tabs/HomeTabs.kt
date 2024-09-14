package com.inverdata.fcmarket.home.navigation.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SpaceDashboard
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.tab.Tab
import com.inverdata.fcmarket.configuration.presentation.ConfigurationTab
import com.inverdata.fcmarket.dashboard.presentation.DashboardTab
import com.inverdata.fcmarket.product.presentation.ProductTab

sealed class HomeTabs(
    val tab: Tab,
    val title: String,
    val icon: ImageVector,
) {

    data object Dashboard: HomeTabs(
        tab = DashboardTab,
        title = "Dashboard",
        icon = Icons.Filled.SpaceDashboard
    )

    data object Product: HomeTabs(
        tab = ProductTab,
        title = "Productos",
        icon = Icons.Filled.ShoppingBag,
    )

    data object Configuration: HomeTabs(
        tab = ConfigurationTab,
        title = "Configuracion",
        icon = Icons.Filled.Settings
    )
}