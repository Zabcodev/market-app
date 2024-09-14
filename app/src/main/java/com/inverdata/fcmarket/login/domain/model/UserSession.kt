package com.inverdata.fcmarket.login.domain.model

data class UserSession(
    val id: Long?,
    val access: String,
    val refresh: String,
    val userId: Long
)
