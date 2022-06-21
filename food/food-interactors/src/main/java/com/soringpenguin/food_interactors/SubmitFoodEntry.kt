package com.soringpenguin.food_interactors

import com.soringpenguin.core.DataState
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.core.UIComponent
import com.soringpenguin.food_datasource.network.FoodService
import com.soringpenguin.food_domain.Food
import com.soringpenguin.food_domain.FoodEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SubmitFoodEntry(
    private val service: FoodService
){
    fun execute(food: List<Food>): Flow<DataState<ProgressBarState.Loading>> = flow {
        val foodEntries: List<FoodEntry> = food.map { FoodEntry(
            id = 0,
            foodId = it.id,
            userId = 2,
            quantity = 1,
            createdOn = "05/31/2022 12:12:12"
        ) }
        try{
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            try {
                service.addFoodEntry(foodEntries)
            }catch (e: Exception) {
                e.printStackTrace()
                emit(
                    DataState.Response (
                        uiComponent = UIComponent.Dialog(
                            title = "Network Data Error",
                            description = e.message ?: "Unknown Error"
                        )
                    )
                )
            }

        }catch(e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response (
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        }
        finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}