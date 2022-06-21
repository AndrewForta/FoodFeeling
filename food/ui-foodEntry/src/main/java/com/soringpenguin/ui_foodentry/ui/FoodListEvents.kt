package com.soringpenguin.ui_foodentry.ui

import com.soringpenguin.food_domain.Food

sealed class FoodListEvents {

    object GetFood: FoodListEvents()

    object FilterFood: FoodListEvents()

    data class UpdateFoodName(
        val foodName: String
    ): FoodListEvents()

    data class SelectFood(
        val foodId: Int
    ): FoodListEvents()

    data class DeselectFood(
        val foodId: Int
    ): FoodListEvents()

    data class AddFood(
        val foodName: String
    ): FoodListEvents()

    data class SubmitFoodEntry(
        val food: List<Food>
    ): FoodListEvents()
}
