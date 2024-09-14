package com.inverdata.fcmarket.core.data.database.helper

import com.inverdata.fcmarket.MarketDB
import com.inverdata.fcmarket.core.data.database.driver.AndroidDriverFactory
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DbHelper(
    private val driverFactory: AndroidDriverFactory
) {

    private var db: MarketDB? = null

    private val mutex = Mutex()

    suspend fun <R> withDatabase(block: suspend (MarketDB) -> R): R = mutex.withLock {
        if (db == null) {
            db = createDb(driverFactory)
        }
        return@withLock block(db!!)
    }

    private fun createDb(driverFactory: AndroidDriverFactory): MarketDB {
        return MarketDB(driver = driverFactory.createDriver())
    }

}