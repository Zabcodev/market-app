package com.inverdata.fcmarket.customer.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomerResponseRemote(
    @SerialName("mensaje")
    val message: String? = null,
    @SerialName("id")
    val id: Int? = null,
)
