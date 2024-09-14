package com.inverdata.fcmarket.core.commom

import cafe.adriel.voyager.navigator.tab.Tab
import com.inverdata.fcmarket.home.navigation.tabs.HomeTabs

object Constants {

    const val BASE_URL = "http://159.203.165.120"

    val homeTabs: List<Tab> = listOf(
        HomeTabs.Dashboard.tab,
        HomeTabs.Product.tab,
        HomeTabs.Configuration.tab
    )

}