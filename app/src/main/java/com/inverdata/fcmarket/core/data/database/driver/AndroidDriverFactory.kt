package com.inverdata.fcmarket.core.data.database.driver

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.inverdata.fcmarket.MarketDB

class AndroidDriverFactory(
    private val context: Context
) {
    fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = MarketDB.Schema,
        context = context,
        name = "market.db"
    )
}