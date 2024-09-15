package com.inverdata.fcmarket.stock.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductItemRemote(
    @SerialName("activo")
    val isActive: Boolean? = null,
    @SerialName("codigo_venta")
    val salesCode: String? = null,
    @SerialName("descripcion")
    val description: String? = null,
    @SerialName("imagen")
    val image: String? = null,
    @SerialName("cant_venta")
    val salesQuantity: String? = null,
    @SerialName("id")
    val productId: Int? = null,
    @SerialName("precio_producto")
    val productPriceOne: Double? = null,
    @SerialName("id_precio_producto")
    val idPriceOne: Long? = null,
    @SerialName("precio_producto_2")
    val productPriceTwo: Double? = null,
    @SerialName("id_precio_producto_2")
    val idPriceTwo: Long? = null,
    @SerialName("precio_producto_3")
    val productPriceThree: Double? = null,
    @SerialName("id_precio_producto_3")
    val idPriceThree: Long? = null,
)
