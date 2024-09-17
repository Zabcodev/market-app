package com.inverdata.fcmarket.customer.domain.validator

import com.inverdata.fcmarket.customer.domain.model.Customer

object CustomerValidator {

    fun validateCustomer(customer: Customer): ValidationResult {
        var validationResult = ValidationResult()

        if (customer.firstName.isBlank()) {
            validationResult = validationResult.copy(firstNameError = "El nombre no puede ser vacío")
        }

        if (customer.lastName.isBlank()) {
            validationResult = validationResult.copy(lastNameError = "Apellido no puede ser vacío")
        }

        if (customer.identificationNumber.isBlank()) {
            validationResult = validationResult.copy(identificationError = "Identificacion no puede ser vacía")
        }

        if (customer.address.isBlank()) {
            validationResult = validationResult.copy(addressError =  "Dirección no puede ser vacía")
        }

        if (customer.phoneNumber.isBlank()) {
            validationResult = validationResult.copy(phoneNumberError = "Telefono no puede ser vacío")
        }

        val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        if (!emailRegex.matches(customer.email)) {
            validationResult = validationResult.copy(emailError = "Correo electronico no puede ser vacío")
        }

        return validationResult
    }


    data class ValidationResult(
        val firstNameError: String? = null,
        val lastNameError: String? = null,
        val identificationError: String? = null,
        val addressError: String? = null,
        val phoneNumberError: String? = null,
        val emailError: String? = null
    )

}