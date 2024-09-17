package com.inverdata.fcmarket.customer.presentation

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.inverdata.fcmarket.customer.presentation.components.AddCustomerSheet
import com.inverdata.fcmarket.customer.presentation.components.CustomerComponent


class CustomerScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<CustomerViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        LaunchedEffect(viewModel.action) {
            viewModel.action.collect { action ->
                Toast.makeText(context, action, Toast.LENGTH_SHORT).show()
            }
        }

        CustomerComponent(
            state = state,
            newCustomer = viewModel.newCustomer,
            onEvent = viewModel::onEvent,
            onUpdateDialogStatus = {
                viewModel.updateDialogStatus(status = false)
            },
            onSelectProductClick = {
                
            },
            onArrowBackClick = {
                navigator.pop()
            }
        )
    }
}