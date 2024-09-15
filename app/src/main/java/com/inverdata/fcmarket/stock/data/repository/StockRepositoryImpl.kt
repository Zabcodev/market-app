package com.inverdata.fcmarket.stock.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.inverdata.fcmarket.stock.data.network.source.StockRemoteSource
import com.inverdata.fcmarket.stock.data.paging.ProductPagingSource
import com.inverdata.fcmarket.stock.domain.model.Product
import com.inverdata.fcmarket.stock.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow

class StockRepositoryImpl(
    private val remoteSource: StockRemoteSource
) : StockRepository {

    companion object {
        const val PAGE_SIZE = 20
        const val INITIAL_LOAD = 12
        const val PREFETCH_ITEMS = 16
    }

    override fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = INITIAL_LOAD,
                prefetchDistance = PREFETCH_ITEMS
            ),
            pagingSourceFactory = {
                ProductPagingSource(remoteSource)
            }
        ).flow
    }
}