package com.soringpenguin.food_datasource.cache

import com.soringpenguin.food_domain.Food
import com.soringpenguin.fooddatasource.cache.Food_Entity

fun Food_Entity.toFood(): Food {
    return Food(
        id = food_id.toInt(),
        name = food_name
    )
}