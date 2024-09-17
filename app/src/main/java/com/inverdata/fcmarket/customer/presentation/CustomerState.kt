package com.inverdata.fcmarket.customer.presentation

import com.inverdata.fcmarket.customer.domain.model.Customer

data class CustomerState(
    val isLoading: Boolean = false,
    val identificationError: String? = null,
    val selectedCustomer: Customer? = null,
    val showDialog: Boolean = false,
    val messageError: String? = null,
    val nameError: String? = null,
    val lastNameError: String? = null,
    val addressError: String? = null,
    val phoneNumberError: String? = null,
    val emailError: String? = null,
    val isAddCustomerSheetOpen: Boolean = false,
    val allValidationCheck: Boolean = false
)
