package com.soringpenguin.food_datasource.network.model

@kotlinx.serialization.Serializable
data class FoodListDto(
    val foods: List<FoodEntryDto>
)
