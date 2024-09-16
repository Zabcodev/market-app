package com.inverdata.fcmarket.customer.domain.repository

import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun getCustomer(identification: String): Flow<ApiRequest<Customer, NetworkError>>
}