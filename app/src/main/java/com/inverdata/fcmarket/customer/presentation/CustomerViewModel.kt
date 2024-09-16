package com.inverdata.fcmarket.customer.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.customer.domain.model.Customer
import com.inverdata.fcmarket.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val repository: CustomerRepository
) : ScreenModel {

    private val _state = MutableStateFlow(CustomerState())
    val state = _state.asStateFlow()

    var newCustomer: Customer? by mutableStateOf(null)
        private set

    init {
        newCustomer = Customer(
            id = 0,
            firstName = "",
            lastName = "",
            typeIdentification = "",
            identificationNumber = "",
            address = "",
            phoneNumber = "",
            email = "",
            isActive = 0,
            isTaxpayer = false
        )
    }

    fun onEvent(event: CustomerEvent) {
        when (event) {
            is CustomerEvent.OnIdentificationChanged -> {
                newCustomer = newCustomer?.copy(
                    identificationNumber = event.identification
                )
            }

            CustomerEvent.OnSearchClick -> {
                screenModelScope.launch {
                    _state.update { state -> state.copy(isLoading = true) }
                    repository.getCustomer(identification = newCustomer?.identificationNumber ?: "")
                        .collect { response ->
                            when (response) {
                                is ApiRequest.Failure -> {
                                    _state.update { state ->
                                        state.copy(
                                            showDialog = true,
                                            messageError = response.error.message.error,
                                            isLoading = false
                                        )
                                    }
                                }

                                is ApiRequest.Success -> {
                                    _state.update { state ->
                                        state.copy(
                                            selectedCustomer = response.data
                                        )
                                    }
                                }
                            }
                        }
                    _state.update { state -> state.copy(isLoading = false) }
                }
            }
        }
    }

    fun updateDialogStatus(status: Boolean) {
        _state.update { state -> state.copy(showDialog = status) }
    }

}