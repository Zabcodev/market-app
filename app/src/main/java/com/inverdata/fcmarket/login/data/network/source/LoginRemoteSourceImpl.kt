package com.inverdata.fcmarket.login.data.network.source

import com.inverdata.fcmarket.core.data.network.client.createClient
import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.login.data.network.model.request.LoginRemote
import com.inverdata.fcmarket.login.data.network.model.response.ErrorRemote
import com.inverdata.fcmarket.login.data.network.model.response.UserRemote
import com.inverdata.fcmarket.login.utils.Constants.LOGIN_URL
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException

class LoginRemoteSourceImpl(): LoginRemoteSource {
    override suspend fun login(login: LoginRemote): Flow<ApiRequest<UserRemote, NetworkError>> {
        return flow {
            try {
                val response = createClient().post(LOGIN_URL) {
                    contentType(ContentType.Application.Json)
                    setBody(login)
                }
                when(response.status.value) {
                    in 200..299 -> emit(ApiRequest.Success(response.body<UserRemote>()))
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
                e.printStackTrace()
            } catch (e: SerializationException) {
                emit(ApiRequest.Failure(NetworkError.SERIALIZATION))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }
}