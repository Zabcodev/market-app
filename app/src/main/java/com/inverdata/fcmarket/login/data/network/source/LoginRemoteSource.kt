package com.inverdata.fcmarket.login.data.network.source

import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.login.data.network.model.request.LoginRemote
import com.inverdata.fcmarket.login.data.network.model.response.UserRemote
import kotlinx.coroutines.flow.Flow

interface LoginRemoteSource {
    suspend fun login(login: LoginRemote): Flow<ApiRequest<UserRemote, NetworkError>>
}