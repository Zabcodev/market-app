package com.inverdata.fcmarket.login.data.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRemote(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)
