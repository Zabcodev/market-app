package com.inverdata.fcmarket.login.di

import com.inverdata.fcmarket.login.data.network.source.LoginRemoteSource
import com.inverdata.fcmarket.login.data.network.source.LoginRemoteSourceImpl
import com.inverdata.fcmarket.login.data.repository.LoginRepositoryImpl
import com.inverdata.fcmarket.login.domain.repository.LoginRepository
import com.inverdata.fcmarket.login.presentation.LoginViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule = module {
    singleOf(::LoginRemoteSourceImpl) bind LoginRemoteSource::class
    singleOf(::LoginRepositoryImpl) bind LoginRepository::class
    factoryOf(::LoginViewModel)
}