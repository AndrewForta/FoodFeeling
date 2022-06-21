package com.soringpenguin.food_datasource.network

import com.soringpenguin.food_datasource.network.model.*
import com.soringpenguin.food_domain.Food
import com.soringpenguin.food_domain.FoodEntry
import io.ktor.client.*
import io.ktor.client.request.*


class FoodServiceImpl(
    private val httpClient: HttpClient
) : FoodService {
    override suspend fun getFood(): List<Food> {
        return httpClient.get<List<FoodDto>> {
            url(EndPoints.GET_FOODS)
        }.map { it.toFood() }
    }

    override suspend fun getFoodEntries(): List<FoodEntry> {
        return httpClient.get<List<FoodEntryDto>> {
            url(EndPoints.GET_FOOD_ENTRIES)
        }.map { it.toFoodEntry() }
    }

    override suspend fun addFood(foodName: String) {
        return httpClient.post {
            url(EndPoints.ADD_FOOD + "/$foodName")
        }
    }

    override suspend fun addFoodEntry(food: List<FoodEntry>) {
        val foodDto = FoodListDto((food.map { FoodEntryDto(
            id = it.id,
            foodId = it.foodId,
            userId = it.userId,
            quantity = it.quantity,
            createdOn = it.createdOn
        ) }))
        return httpClient.post {
            url(EndPoints.ADD_FOOD_ENTRY)
            body = foodDto
        }
    }

}