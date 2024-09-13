package com.inverdata.fcmarket.login.domain.model

data class Login(
    val email: String,
    val password: String,
    val remember: Boolean = false
)
