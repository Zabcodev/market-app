package com.inverdata.fcmarket.stock.data.network.source

import com.inverdata.fcmarket.core.data.network.client.createClient
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.stock.data.network.model.ProductRemote
import com.inverdata.fcmarket.stock.utils.StockConstants.PAGE_PARAMS
import com.inverdata.fcmarket.stock.utils.StockConstants.PRODUCT_URL
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException

class StockRemoteSourceImpl : StockRemoteSource {
    override suspend fun getProducts(page: Int): Flow<ApiRequest<ProductRemote, NetworkError>> {
        return flow {
            try {
                val response = createClient().get(PRODUCT_URL) {
                    url { parameters.append(name = PAGE_PARAMS, value = page.toString()) }
                }
                when (response.status.value) {
                    in 200..299 -> emit(ApiRequest.Success(response.body<ProductRemote>()))
                    400 -> emit(ApiRequest.Failure(NetworkError.BAD_REQUEST))
                    401 -> emit(ApiRequest.Failure(NetworkError.UNAUTHORIZED))
                    408 -> emit(ApiRequest.Failure(NetworkError.REQUEST_TIMEOUT))
                    409 -> emit(ApiRequest.Failure(NetworkError.CONFLICT))
                    413 -> emit(ApiRequest.Failure(NetworkError.PAYLOAD_TO_LARGE))
                    in 501..599 -> emit(ApiRequest.Failure(NetworkError.SERVER_ERROR))
                    else -> emit(ApiRequest.Failure(NetworkError.UNKNOWN))
                }
            } catch (e: UnresolvedAddressException) {
                emit(ApiRequest.Failure(NetworkError.NO_INTERNET))
            } catch (e: SerializationException) {
                emit(ApiRequest.Failure(NetworkError.SERIALIZATION))
            }
        }.flowOn(Dispatchers.IO)
    }
}