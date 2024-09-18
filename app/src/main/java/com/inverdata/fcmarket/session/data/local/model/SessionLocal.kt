package com.inverdata.fcmarket.session.data.local.model

data class SessionLocal(
    val id: Long?,
    val access: String,
    val refresh: String,
    val userId: Long,
    val userEmail: String,
)
