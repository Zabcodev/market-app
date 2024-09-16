package com.inverdata.fcmarket.customer.di

import com.inverdata.fcmarket.customer.data.network.source.CustomerRemoteSource
import com.inverdata.fcmarket.customer.data.network.source.CustomerRemoteSourceImpl
import com.inverdata.fcmarket.customer.data.repository.CustomerRepositoryImpl
import com.inverdata.fcmarket.customer.domain.repository.CustomerRepository
import com.inverdata.fcmarket.customer.presentation.CustomerViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val customerModule = module {
    singleOf(::CustomerRemoteSourceImpl) bind CustomerRemoteSource::class
    singleOf(::CustomerRepositoryImpl) bind CustomerRepository::class
    factoryOf(::CustomerViewModel)
}