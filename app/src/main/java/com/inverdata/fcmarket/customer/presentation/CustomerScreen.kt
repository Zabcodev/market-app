package com.inverdata.fcmarket.customer.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.inverdata.fcmarket.customer.presentation.components.CustomerComponent


class CustomerScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<CustomerViewModel>()
        val state by viewModel.state.collectAsState()

        CustomerComponent(
            state = state,
            newCustomer = viewModel.newCustomer,
            onEvent = viewModel::onEvent,
            onUpdateDialogStatus = {
                viewModel.updateDialogStatus(status = false)
            },
            onSelectProductClick = {
                
            }
        )
    }
}