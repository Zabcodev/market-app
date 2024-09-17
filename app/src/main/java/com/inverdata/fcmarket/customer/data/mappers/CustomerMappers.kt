package com.inverdata.fcmarket.customer.data.mappers

import com.inverdata.fcmarket.customer.data.network.model.CustomerRemote
import com.inverdata.fcmarket.customer.data.network.model.CustomerResponseRemote
import com.inverdata.fcmarket.customer.domain.model.Customer
import com.inverdata.fcmarket.customer.domain.model.CustomerResponse

fun CustomerRemote.toDomain(): Customer = Customer(
    id = id ?: 0,
    firstName = firstName ?: "",
    lastName = lastName ?: "",
    typeIdentification = typeIdentification ?: "",
    identificationNumber = identificationNumber ?: "",
    address = address ?: "",
    phoneNumber = phoneNumber ?: "",
    email = email ?: "",
    isActive = isActive ?: 0,
    isTaxpayer = isTaxpayer ?: false
)

fun Customer.toDto(): CustomerRemote = CustomerRemote(
    id = id,
    firstName = firstName,
    lastName = lastName,
    typeIdentification = typeIdentification,
    identificationNumber = identificationNumber,
    address = address,
    phoneNumber = phoneNumber,
    email = email,
    isActive = isActive,
    isTaxpayer = isTaxpayer
)

fun CustomerResponseRemote.toDomain(): CustomerResponse = CustomerResponse(
    message = message ?: "",
    id = id ?: 0,
)