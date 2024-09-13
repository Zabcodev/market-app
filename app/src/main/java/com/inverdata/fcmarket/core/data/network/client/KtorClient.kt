package com.inverdata.fcmarket.core.data.network.client

import com.inverdata.fcmarket.core.commom.Constants
import com.inverdata.fcmarket.core.commom.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createClient(baseUrl: String? = null): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }
        )
    }

    install(Logging) {
        level = LogLevel.ALL
        logger = Logger.SIMPLE
    }

    install(DefaultRequest) {
        url(
            urlString = baseUrl ?: BASE_URL
        )
    }
}