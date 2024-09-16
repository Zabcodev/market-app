package com.inverdata.fcmarket.customer.domain.model

data class Customer(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val typeIdentification: String = "",
    val identificationNumber: String = "",
    val address: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val isActive: Int = 0,
    val isTaxpayer: Boolean = false,
)
