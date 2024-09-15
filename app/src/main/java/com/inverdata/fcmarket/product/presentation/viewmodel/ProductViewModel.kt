package com.inverdata.fcmarket.product.presentation.viewmodel

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.ScreenModel
import com.inverdata.fcmarket.stock.domain.model.Product
import com.inverdata.fcmarket.stock.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow

class ProductViewModel(
    private val repository: StockRepository
): ScreenModel {

    val products: Flow<PagingData<Product>> = repository.getProducts()

}