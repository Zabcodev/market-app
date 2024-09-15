package com.inverdata.fcmarket.stock.data.mapper

import com.inverdata.fcmarket.stock.data.network.model.ProductItemRemote
import com.inverdata.fcmarket.stock.domain.model.Product

fun ProductItemRemote.toDomain(): Product = Product(
    isActive = isActive ?: false,
    salesCode = salesCode ?: "",
    description = description ?: "",
    image = image,
    salesQuantity = salesQuantity ?: "",
    productId = productId ?: 0,
    productPriceOne = productPriceOne ?: 0.00,
    idPriceOne = idPriceOne ?: 0L,
    productPriceTwo = productPriceTwo ?: 0.00,
    idPriceTwo = idPriceTwo ?: 0L,
    productPriceThree = productPriceThree ?: 0.00,
    idPriceThree = idPriceThree ?: 0L
)