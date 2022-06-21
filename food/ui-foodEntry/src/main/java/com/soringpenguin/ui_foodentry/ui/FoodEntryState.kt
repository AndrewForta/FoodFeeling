package com.soringpenguin.ui_foodentry.ui

import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.food_domain.Food

data class FoodEntryState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val food: List<Food> = listOf(),
    val filteredFood: List<Food> = listOf(),
    val selectedFood: List<Food> = listOf(),
    val foodName: String = ""
)
