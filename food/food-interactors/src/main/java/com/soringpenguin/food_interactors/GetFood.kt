package com.soringpenguin.food_interactors

import com.soringpenguin.core.DataState
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.core.UIComponent
import com.soringpenguin.food_datasource.cache.FoodCache
import com.soringpenguin.food_datasource.network.FoodService
import com.soringpenguin.food_domain.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFood (
    private val cache: FoodCache,
    private val service: FoodService
        ){
    fun execute(): Flow<DataState<List<Food>>> = flow {
        try{
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val food: List<Food> = try {
                service.getFood()
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
                listOf()
            }

            if(food.isNotEmpty()) {
                cache.clearFood()
            }

            // cache data
            for (food in food) {
                cache.addFood(food)
            }

            //emit cache
            val cachedFood = cache.getAllFood()

            emit(DataState.Data(cachedFood))
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