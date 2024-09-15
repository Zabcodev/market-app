package com.inverdata.fcmarket.product.di

import com.inverdata.fcmarket.product.presentation.viewmodel.ProductViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val productModule = module {
    factoryOf(::ProductViewModel)
}