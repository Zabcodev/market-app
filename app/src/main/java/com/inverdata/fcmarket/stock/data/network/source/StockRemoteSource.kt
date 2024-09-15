package com.inverdata.fcmarket.stock.data.network.source

import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.stock.data.network.model.ProductRemote
import kotlinx.coroutines.flow.Flow

interface StockRemoteSource {
    suspend fun getProducts(page: Int = 1): Flow<ApiRequest<ProductRemote, NetworkError>>
}