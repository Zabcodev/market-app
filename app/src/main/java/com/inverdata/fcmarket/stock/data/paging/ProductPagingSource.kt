package com.inverdata.fcmarket.stock.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.stock.data.mapper.toDomain
import com.inverdata.fcmarket.stock.data.network.model.ProductItemRemote
import com.inverdata.fcmarket.stock.data.network.model.ProductRemote
import com.inverdata.fcmarket.stock.data.network.source.StockRemoteSource
import com.inverdata.fcmarket.stock.domain.model.Product
import java.io.IOException

class ProductPagingSource(
    private val remoteSource: StockRemoteSource
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            var remote: ProductRemote? = null
            remoteSource.getProducts(page).collect { response ->
                when (response) {
                    is ApiRequest.Failure -> {}
                    is ApiRequest.Success -> {
                        remote = response.data
                    }
                }
            }
            var products: List<ProductItemRemote> = emptyList()
            var prevKey: Int? = null
            var nextKey: Int? = null
            if (remote != null) {
                products = remote!!.products
                prevKey = if (page > 0) page - 1 else null
                nextKey = if (remote?.next != null) page + 1 else null
            }

            LoadResult.Page(
                data = products.map { product -> product.toDomain() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}