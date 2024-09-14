package com.inverdata.fcmarket.core.di

import com.inverdata.fcmarket.core.data.database.driver.AndroidDriverFactory
import com.inverdata.fcmarket.core.data.database.helper.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val appModule = module {
    single { AndroidDriverFactory(androidContext()) }
    single { DbHelper(get()) }
    single<CoroutineContext> { Dispatchers.IO }
    singleOf(::CoroutineScope) bind CoroutineScope::class
}