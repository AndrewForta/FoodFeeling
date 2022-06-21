package com.soringpenguin.feeling_datasource.network

import com.soringpenguin.feeling_domain.Feeling
import com.soringpenguin.feeling_domain.FeelingEntry
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

interface FeelingService {

    suspend fun getFeelings(): List<Feeling>

    suspend fun getFeelingEntries(): List<FeelingEntry>

    suspend fun addFeeling(feelingName: String): Unit

    suspend fun addFeelingEntry(feelings: List<FeelingEntry>): Unit

    companion object Factory {
        fun build(): FeelingService {
            return FeelingServiceImpl(
                httpClient = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            kotlinx.serialization.json.Json {
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                    install(HttpTimeout) {
                        requestTimeoutMillis = 5000L
                        connectTimeoutMillis = 5000L
                        socketTimeoutMillis = 5000L
                    }
                    install(ResponseObserver) {
                        onResponse { response ->
                            println("HTTP status:${response.status.value}")
                        }
                    }
                    install(DefaultRequest) {
                        header(HttpHeaders.ContentType, ContentType.Application.Json)
                    }
                }
            )
        }
    }
}