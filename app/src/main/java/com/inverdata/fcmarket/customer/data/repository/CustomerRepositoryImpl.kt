package com.inverdata.fcmarket.customer.data.repository

import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.customer.data.mappers.toDomain
import com.inverdata.fcmarket.customer.data.network.source.CustomerRemoteSource
import com.inverdata.fcmarket.customer.domain.model.Customer
import com.inverdata.fcmarket.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CustomerRepositoryImpl(
    private val remoteSource: CustomerRemoteSource
): CustomerRepository {
    override suspend fun getCustomer(identification: String): Flow<ApiRequest<Customer, NetworkError>> {
        return flow {
            remoteSource.getCustomerData(identification).collect { response ->
                when (response) {
                    is ApiRequest.Failure -> emit(ApiRequest.Failure(response.error))
                    is ApiRequest.Success -> emit(ApiRequest.Success(response.data.toDomain()))
                }
            }
        }
    }
}