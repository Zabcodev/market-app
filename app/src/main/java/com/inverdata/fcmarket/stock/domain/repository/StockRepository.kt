package com.inverdata.fcmarket.stock.domain.repository

import androidx.paging.PagingData
import com.inverdata.fcmarket.stock.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getProducts(): Flow<PagingData<Product>>
}