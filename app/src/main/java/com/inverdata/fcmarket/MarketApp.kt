package com.inverdata.fcmarket

import android.app.Application
import com.inverdata.fcmarket.core.di.appModule
import com.inverdata.fcmarket.login.di.loginModule
import com.inverdata.fcmarket.product.di.productModule
import com.inverdata.fcmarket.session.di.sessionModule
import com.inverdata.fcmarket.stock.di.stockModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarketApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarketApp)
            androidLogger()
            modules(appModule + loginModule + sessionModule + stockModule + productModule)
        }
    }
}