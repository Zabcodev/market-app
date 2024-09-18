package com.inverdata.fcmarket.configuration.di

import com.inverdata.fcmarket.configuration.domain.usecases.DeleteSessionUseCase
import com.inverdata.fcmarket.configuration.domain.usecases.GetUserEmailUseCase
import com.inverdata.fcmarket.configuration.presentation.ConfigurationViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val configurationModule = module {
    singleOf(::DeleteSessionUseCase)
    singleOf(::GetUserEmailUseCase)
    factoryOf(::ConfigurationViewModel)
}