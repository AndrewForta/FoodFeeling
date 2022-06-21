package com.soringpenguin.ui_foodentry.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soringpenguin.core.DataState
import com.soringpenguin.core.Logger
import com.soringpenguin.core.UIComponent
import com.soringpenguin.food_domain.Food
import com.soringpenguin.food_interactors.AddFood
import com.soringpenguin.food_interactors.GetFood
import com.soringpenguin.food_interactors.SubmitFoodEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel
@Inject
constructor(
    private val getFood: GetFood,
    private val addFood: AddFood,
    private val submitFoodEntry: SubmitFoodEntry
    //private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val logger = Logger("FoodListViewModel")
    val state: MutableState<FoodEntryState> = mutableStateOf(FoodEntryState())


    init {
        onTriggerEvent(FoodListEvents.GetFood)
    }

    fun onTriggerEvent(event: FoodListEvents) {
        when(event) {
            is FoodListEvents.GetFood -> {
                getFood()
            }
            is FoodListEvents.FilterFood -> {
                filterFood()
            }
            is FoodListEvents.UpdateFoodName -> {
                updateFoodName(event.foodName)
            }
            is FoodListEvents.SelectFood -> {
                selectFood(event.foodId)
            }
            is FoodListEvents.DeselectFood -> {
                deselectFood(event.foodId)
            }
            is FoodListEvents.AddFood -> {
                addFood(state.value.foodName)
            }
            is FoodListEvents.SubmitFoodEntry -> {
                submitFoodEntry(state.value.selectedFood)
            }
        }
    }

    private fun submitFoodEntry(selectedFood: List<Food>) {
        submitFoodEntry.execute(selectedFood).onEach { dataState ->
            when(dataState) {

                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
        for(food: Food in state.value.selectedFood) {
            deselectFood(foodId = food.id)
        }
    }

    private fun addFood(foodName: String) {
        addFood.execute(foodName).onEach { dataState ->
            when(dataState) {

                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    state.value = state.value.copy(food = dataState.data?: listOf())
                    filterFood()
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
        updateFoodName("")
    }

    private fun deselectFood(foodId: Int) {
        val newSelectedList: MutableList<Food> = state.value.selectedFood.filter {
            it.id != foodId
        }.toMutableList()

        val food: Food ?= state.value.food.find {
            it.id == foodId
        }
        val newFilterList: MutableList<Food> = state.value.filteredFood.toMutableList()

        if(food != null) {
            newFilterList.add(food)
            state.value = state.value.copy(filteredFood = newFilterList)
        }

        state.value = state.value.copy(selectedFood = newSelectedList)
    }

    private fun selectFood(foodId: Int) {
        val newFilterList: MutableList<Food> = state.value.filteredFood.filter {
            it.id != foodId
        }.toMutableList()

        val food: Food ?= state.value.food.find {
            it.id == foodId
        }
        val newSelectedList: MutableList<Food> = state.value.selectedFood.toMutableList()

        if(food != null) {
            newSelectedList.add(food)
            state.value = state.value.copy(selectedFood = newSelectedList)
        }

        state.value = state.value.copy(filteredFood = newFilterList)
    }

    private fun filterFood() {
        val filteredList: MutableList<Food> = state.value.food.filter {
            it.name.lowercase().contains(state.value.foodName.lowercase()) && !state.value.selectedFood.contains(it)
        }.toMutableList()

        state.value = state.value.copy(filteredFood = filteredList)
    }

    private fun updateFoodName(foodName: String) {
        state.value = state.value.copy(foodName = foodName)
    }

    private fun getFood() {

        getFood.execute().onEach { dataState ->

            when(dataState) {

                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    state.value = state.value.copy(food = dataState.data?: listOf())
                    filterFood()
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }
}