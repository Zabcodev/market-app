package com.inverdata.fcmarket.stock.di

import com.inverdata.fcmarket.stock.data.network.source.StockRemoteSource
import com.inverdata.fcmarket.stock.data.network.source.StockRemoteSourceImpl
import com.inverdata.fcmarket.stock.data.paging.ProductPagingSource
import com.inverdata.fcmarket.stock.data.repository.StockRepositoryImpl
import com.inverdata.fcmarket.stock.domain.repository.StockRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val stockModule = module {
    singleOf(::StockRemoteSourceImpl) bind StockRemoteSource::class
    single { ProductPagingSource(get()) }
    singleOf(::StockRepositoryImpl) bind StockRepository::class
}