package com.inverdata.fcmarket.stock.domain.model

data class Product(
    val isActive: Boolean,
    val salesCode: String,
    val description: String,
    val image: String?,
    val salesQuantity: String,
    val productId: Int,
    val productPriceOne: Double,
    val idPriceOne: Long,
    val productPriceTwo: Double,
    val idPriceTwo: Long,
    val productPriceThree: Double,
    val idPriceThree: Long
)
