package com.inverdata.fcmarket.login.data.repository

import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.login.data.mappers.toDomain
import com.inverdata.fcmarket.login.data.mappers.toRemote
import com.inverdata.fcmarket.login.data.network.source.LoginRemoteSource
import com.inverdata.fcmarket.login.domain.model.Login
import com.inverdata.fcmarket.login.domain.model.User
import com.inverdata.fcmarket.login.domain.model.UserSession
import com.inverdata.fcmarket.login.domain.repository.LoginRepository
import com.inverdata.fcmarket.session.data.local.source.SessionLocalSource
import com.inverdata.fcmarket.session.data.mappers.toDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(
    private val remoteSource: LoginRemoteSource,
    private val sessionSource: SessionLocalSource
) : LoginRepository {
    override suspend fun login(login: Login): Flow<ApiRequest<User, NetworkError>> = flow {
        remoteSource.login(login.toRemote()).collect { response ->
            when (response) {
                is ApiRequest.Failure -> {
                    emit(ApiRequest.Failure(response.error))
                }

                is ApiRequest.Success -> {
                    emit(ApiRequest.Success(response.data.toDomain()))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertSession(session: UserSession) {
        sessionSource.insertSession(session = session.toDatabase())
    }

}