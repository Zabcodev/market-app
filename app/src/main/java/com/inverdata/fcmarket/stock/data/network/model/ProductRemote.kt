package com.inverdata.fcmarket.stock.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductRemote(
    @SerialName("pages")
    val pages: Int? = null,
    @SerialName("count")
    val count: Int? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val products: List<ProductItemRemote> = emptyList(),
)
