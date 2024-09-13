package com.inverdata.fcmarket.login.data.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRemote(
    @SerialName("access")
    val access: String? = null,
    @SerialName("refresh")
    val refresh: String? = null,
    @SerialName("user")
    val user: Int? = null,
)
