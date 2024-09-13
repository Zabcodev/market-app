package com.inverdata.fcmarket.login.domain.repository

import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.login.domain.model.Login
import com.inverdata.fcmarket.login.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(login: Login): Flow<ApiRequest<User, NetworkError>>
}