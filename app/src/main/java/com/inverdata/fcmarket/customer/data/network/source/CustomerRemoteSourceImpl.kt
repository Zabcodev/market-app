package com.inverdata.fcmarket.customer.data.network.source

import com.inverdata.fcmarket.core.data.network.client.createClient
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.customer.data.network.model.CustomerRemote
import com.inverdata.fcmarket.customer.data.network.model.CustomerResponseRemote
import com.inverdata.fcmarket.customer.utils.CustomerConstants.CUSTOMER_URL
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException

class CustomerRemoteSourceImpl : CustomerRemoteSource {
    override suspend fun getCustomerData(identification: String): Flow<ApiRequest<CustomerRemote, NetworkError>> {
        return flow {
            try {
                val response = createClient().get(CUSTOMER_URL) {
                    url {
                        appendPathSegments(identification)
                    }
                }

                when (response.status.value) {
                    in 200..299 -> emit(ApiRequest.Success(response.body<CustomerRemote>()))
                    400 -> emit(ApiRequest.Failure(NetworkError.BAD_REQUEST))
                    401 -> emit(ApiRequest.Failure(NetworkError.UNAUTHORIZED))
                    404 -> emit(ApiRequest.Failure(NetworkError.NOT_FOUND))
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

    override suspend fun registerClient(customer: CustomerRemote): Flow<ApiRequest<CustomerResponseRemote, NetworkError>> {
        return flow {
            try {
                val response = createClient().post(CUSTOMER_URL) {
                    contentType(ContentType.Application.Json)
                    setBody(customer)
                }
                when (response.status.value) {
                    in 200..299 -> emit(ApiRequest.Success(response.body<CustomerResponseRemote>()))
                    400 -> emit(ApiRequest.Failure(NetworkError.BAD_REQUEST))
                    401 -> emit(ApiRequest.Failure(NetworkError.UNAUTHORIZED))
                    404 -> emit(ApiRequest.Failure(NetworkError.NOT_FOUND))
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