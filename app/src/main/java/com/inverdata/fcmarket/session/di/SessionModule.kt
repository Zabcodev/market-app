package com.inverdata.fcmarket.session.di

import com.inverdata.fcmarket.session.data.local.source.SessionLocalSource
import com.inverdata.fcmarket.session.data.local.source.SessionLocalSourceImpl
import com.inverdata.fcmarket.session.data.repository.SessionRepositoryImpl
import com.inverdata.fcmarket.session.domain.repository.SessionRepository
import com.inverdata.fcmarket.session.presentation.SplashViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sessionModule = module {
    singleOf(::SessionLocalSourceImpl) bind SessionLocalSource::class
    singleOf(::SessionRepositoryImpl) bind SessionRepository::class
    factoryOf(::SplashViewModel)
}