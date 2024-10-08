package com.inverdata.fcmarket.customer.data.network.source

import com.inverdata.fcmarket.core.data.network.request.ApiRequest
import com.inverdata.fcmarket.core.data.network.request.NetworkError
import com.inverdata.fcmarket.customer.data.network.model.CustomerRemote
import com.inverdata.fcmarket.customer.data.network.model.CustomerResponseRemote
import kotlinx.coroutines.flow.Flow

interface CustomerRemoteSource {
    suspend fun getCustomerData(identification: String): Flow<ApiRequest<CustomerRemote, NetworkError>>
    suspend fun registerClient(customer: CustomerRemote): Flow<ApiRequest<CustomerResponseRemote, NetworkError>>
}