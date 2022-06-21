package com.soringpenguin.food_datasource.cache

import com.soringpenguin.food_domain.FoodEntry
import com.soringpenguin.fooddatasource.cache.Food_Entry_Entity

fun Food_Entry_Entity.toFoodEntry(): FoodEntry {
    return FoodEntry(
        id = food_entry_id.toInt(),
        userId = food_entry_user_id.toInt(),
        foodId = food_entry_food_id.toInt(),
        quantity = food_entry_quantity.toInt(),
        createdOn = food_entry_created_on
    )
}