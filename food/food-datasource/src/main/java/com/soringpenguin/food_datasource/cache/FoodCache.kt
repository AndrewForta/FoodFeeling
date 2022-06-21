package com.soringpenguin.food_datasource.cache

import com.soringpenguin.food_domain.Food
import com.soringpenguin.food_domain.FoodEntry
import com.squareup.sqldelight.db.SqlDriver

interface FoodCache {

    suspend fun getAllFood(): List<Food>

    suspend fun getAllFoodEntries(): List<FoodEntry>

    suspend fun addFood(food: Food): Unit

    suspend fun addFoodByName(foodName: String): Unit

    suspend fun clearFood(): Unit

    companion object Factory {
        fun build(sqlDriver: SqlDriver): FoodCache {
            return FoodCacheImpl(FoodDatabase(sqlDriver))
        }
        val schema: SqlDriver.Schema = FoodDatabase.Schema

        val dbName: String = "food.db"
    }
}