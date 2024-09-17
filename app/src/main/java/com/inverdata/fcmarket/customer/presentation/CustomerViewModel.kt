package com.inverdata.fcmarket.customer.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.customer.domain.model.Customer
import com.inverdata.fcmarket.customer.domain.repository.CustomerRepository
import com.inverdata.fcmarket.customer.domain.validator.CustomerValidator
import kotlinx.coroutines.delay
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

    private val _action = MutableSharedFlow<String>()
    val action = _action.asSharedFlow()

    var newCustomer: Customer? by mutableStateOf(null)
        private set

    init {
        resetCustomer()
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
                                            isLoading = false,
                                            selectedCustomer = null
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

            is CustomerEvent.OnAddressChanged -> {
                newCustomer = newCustomer?.copy(
                    address = event.address
                )
            }

            is CustomerEvent.OnEmailChanged -> {
                newCustomer = newCustomer?.copy(
                    email = event.email
                )
            }

            is CustomerEvent.OnLastNameChanged -> {
                newCustomer = newCustomer?.copy(
                    lastName = event.lastname
                )
            }

            is CustomerEvent.OnNameChanged -> {
                newCustomer = newCustomer?.copy(
                    firstName = event.name
                )
            }

            is CustomerEvent.OnPhoneNumberChanged -> {
                newCustomer = newCustomer?.copy(
                    phoneNumber = event.phoneNumber
                )
            }

            CustomerEvent.SaveCustomer -> {
                newCustomer?.let { customer ->
                    val validation = CustomerValidator.validateCustomer(customer)
                    val errors = listOfNotNull(
                        validation.firstNameError,
                        validation.lastNameError,
                        validation.identificationError,
                        validation.addressError,
                        validation.phoneNumberError,
                        validation.emailError
                    )

                    if (errors.isEmpty()) {
                        _state.update { state ->
                            state.copy(
                                isAddCustomerSheetOpen = false,
                                nameError = null,
                                lastNameError = null,
                                identificationError = null,
                                addressError = null,
                                phoneNumberError = null,
                                emailError = null,
                                allValidationCheck = true
                            )
                        }
                        screenModelScope.launch {
                            repository.registerCustomer(
                                customer.copy(
                                    isActive = 1,
                                    isTaxpayer = false
                                )
                            ).collect { response ->
                                when (response) {
                                    is ApiRequest.Failure -> println(response.error.message.error)
                                    is ApiRequest.Success -> _action.emit(response.data.message)
                                }
                            }
                            delay(300L)
                            newCustomer = null
                        }
                    } else {
                        _state.update { state ->
                            state.copy(
                                nameError = validation.firstNameError,
                                lastNameError = validation.lastNameError,
                                identificationError = validation.identificationError,
                                addressError = validation.addressError,
                                phoneNumberError = validation.phoneNumberError,
                                emailError = validation.emailError,
                                allValidationCheck = false
                            )
                        }
                    }
                }
            }

            CustomerEvent.OnAddNewCustomerClick -> {
                _state.update { state ->
                    state.copy(
                        isAddCustomerSheetOpen = true,
                        showDialog = false
                    )
                }
                resetCustomer()
            }

            CustomerEvent.OnDismissAddCustomerClick -> {
                _state.update { state ->
                    state.copy(
                        isAddCustomerSheetOpen = false,
                        nameError = null,
                        lastNameError = null,
                        identificationError = null,
                        addressError = null,
                        phoneNumberError = null,
                        emailError = null
                    )
                }
            }

            is CustomerEvent.OnIdentificationTypeChanged -> {
                newCustomer = newCustomer?.copy(
                    typeIdentification = event.identificationType
                )
            }
        }
    }

    fun updateDialogStatus(status: Boolean) {
        _state.update { state -> state.copy(showDialog = status) }
    }

    private fun resetCustomer() {
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
}