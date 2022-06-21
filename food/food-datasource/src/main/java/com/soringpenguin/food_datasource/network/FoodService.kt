package com.soringpenguin.food_datasource.network

import com.soringpenguin.food_domain.Food
import com.soringpenguin.food_domain.FoodEntry
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

interface FoodService {

    suspend fun getFood(): List<Food>

    suspend fun getFoodEntries(): List<FoodEntry>

    suspend fun addFood(foodName: String): Unit

    suspend fun addFoodEntry(food: List<FoodEntry>): Unit

    companion object Factory {
        fun build(): FoodService {
            return FoodServiceImpl(
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