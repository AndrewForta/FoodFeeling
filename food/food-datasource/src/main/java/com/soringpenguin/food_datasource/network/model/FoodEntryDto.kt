package com.soringpenguin.food_datasource.network.model

import com.soringpenguin.food_domain.FoodEntry
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FoodEntryDto (
    @SerialName("food_entry_id")
    val id: Int,

    @SerialName("food_entry_food_id")
    val foodId: Int,

    @SerialName("food_entry_user_id")
    val userId: Int,

    @SerialName("food_entry_quantity")
    val quantity: Int,

    @SerialName("food_entry_created_on")
    val createdOn: String
        )
fun FoodEntryDto.toFoodEntry(): FoodEntry {
    return FoodEntry(
        id = id,
        foodId = foodId,
        userId = userId,
        quantity = quantity,
        createdOn = createdOn
    )
}
