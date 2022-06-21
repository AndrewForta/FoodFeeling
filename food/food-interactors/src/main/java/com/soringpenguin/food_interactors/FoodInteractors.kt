package com.soringpenguin.food_interactors

import com.soringpenguin.food_datasource.cache.FoodCache
import com.soringpenguin.food_datasource.network.FoodService
import com.squareup.sqldelight.db.SqlDriver

data class FoodInteractors(
    val getFood: GetFood,
    val addFood: AddFood,
    val submitFoodEntry: SubmitFoodEntry,
) {
    companion object Factory{
        fun build(
            sqlDriver: SqlDriver,
        ): FoodInteractors {
            val service = FoodService.build()
            val cache = FoodCache.build(sqlDriver)
            return FoodInteractors(
                getFood = GetFood(
                    service = service,
                    cache = cache
                ),
                addFood = AddFood(
                    cache = cache,
                    service = service,
                ),
                submitFoodEntry = SubmitFoodEntry(
                    service = service
                )

            )
        }
        val schema: SqlDriver.Schema = FoodCache.schema

        val dbName: String = FoodCache.dbName
    }
}