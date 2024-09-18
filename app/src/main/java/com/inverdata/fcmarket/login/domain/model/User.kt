package com.inverdata.fcmarket.login.domain.model

data class User(
    val access: String,
    val refresh: String,
    val user: Int,
    val userEmail: String = ""
)
