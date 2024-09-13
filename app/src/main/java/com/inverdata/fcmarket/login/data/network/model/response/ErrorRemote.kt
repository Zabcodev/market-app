package com.inverdata.fcmarket.login.data.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorRemote(
    @SerialName("message")
    val error: String? = null,
)
