package com.soringpenguin.food_datasource.cache

import com.soringpenguin.food_domain.Food
import com.soringpenguin.food_domain.FoodEntry
import com.soringpenguin.fooddatasource.cache.FoodDbQueries

class FoodCacheImpl(
    private val foodDatabase: FoodDatabase
): FoodCache {

    private var queries: FoodDbQueries = foodDatabase.foodDbQueries

    override suspend fun getAllFood(): List<Food> {
        return queries.selectAllFood().executeAsList().map { it.toFood() }
    }

    override suspend fun getAllFoodEntries(): List<FoodEntry> {
        return queries.selectAllFoodEntries().executeAsList().map { it.toFoodEntry() }
    }

    override suspend fun addFood(food: Food) {
        return queries.insertFood(food.id.toLong(), food.name)
    }

    override suspend fun addFoodByName(foodName: String) {
        return queries.insertFoodByName(foodName)
    }

    override suspend fun clearFood() {
        return queries.clearFood()
    }
}