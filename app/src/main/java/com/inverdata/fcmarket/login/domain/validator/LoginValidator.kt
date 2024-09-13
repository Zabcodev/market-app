package com.inverdata.fcmarket.login.domain.validator

import com.inverdata.fcmarket.login.domain.model.Login

object LoginValidator {

    fun validateLogin(login: Login): ValidationResult {
        var result = ValidationResult()

        val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        if (!emailRegex.matches(login.email)) {
            result = result.copy(
                emailError = "Email invalido."
            )
        }

        if (login.password.isBlank()) {
            result = result.copy(
                passwordError = "El campo de contraseña no puede estar vacío."
            )
        }

            return result
        }

        data class ValidationResult(
            val emailError: String? = null,
            val passwordError: String? = null,
        )

    }