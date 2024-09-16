package com.inverdata.fcmarket.customer.presentation

import com.inverdata.fcmarket.customer.domain.model.Customer

data class CustomerState(
    val isLoading: Boolean = false,
    val identificationError: String? = null,
    val selectedCustomer: Customer? = null,
    val showDialog: Boolean = false,
    val messageError: String? = null,
)
