package com.soringpenguin.ui_feelingentry.ui

import android.app.Application
import android.content.Context
import android.hardware.camera2.params.DeviceStateSensorOrientationMap
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soringpenguin.core.DataState
import com.soringpenguin.core.Logger
import com.soringpenguin.core.UIComponent
import com.soringpenguin.feeling_domain.Feeling
import com.soringpenguin.feeling_interactors.AddFeeling
import com.soringpenguin.feeling_interactors.GetFeelings
import com.soringpenguin.feeling_interactors.SubmitFeelingEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FeelingListViewModel
@Inject
constructor(
    private val getFeelings: GetFeelings,
    private val addFeeling: AddFeeling,
    private val submitFeelingEntry: SubmitFeelingEntry
    //private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val logger = Logger("FeelingListViewModel")
    val state: MutableState<FeelingEntryState> = mutableStateOf(FeelingEntryState())


    init {
        onTriggerEvent(FeelingListEvents.GetFeelings)
    }

    fun onTriggerEvent(event: FeelingListEvents) {
        when(event) {
            is FeelingListEvents.GetFeelings -> {
                getFeelings()
            }
            is FeelingListEvents.FilterFeelings -> {
                filterFeelings()
            }
            is FeelingListEvents.UpdateFeelingName -> {
                updateFeelingName(event.feelingName)
            }
            is FeelingListEvents.SelectFeeling -> {
                selectFeeling(event.feelingId)
            }
            is FeelingListEvents.DeselectFeeling -> {
                deselectFeeling(event.feelingId)
            }
            is FeelingListEvents.AddFeeling -> {
                addFeeling(state.value.feelingName)
            }
            is FeelingListEvents.SubmitFeelingEntry -> {
                submitFeelingEntry(state.value.selectedFeelings)
            }
        }
    }

    private fun submitFeelingEntry(selectedFeelings: List<Feeling>) {
        submitFeelingEntry.execute(selectedFeelings).onEach { dataState ->
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
        for(feeling: Feeling in state.value.selectedFeelings) {
            deselectFeeling(feelingId = feeling.id)
        }
    }

    private fun addFeeling(feelingName: String) {
        addFeeling.execute(feelingName).onEach { dataState ->
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
                    state.value = state.value.copy(feelings = dataState.data?: listOf())
                    filterFeelings()
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
        updateFeelingName("")
    }

    private fun deselectFeeling(feelingId: Int) {
        val newSelectedList: MutableList<Feeling> = state.value.selectedFeelings.filter {
            it.id != feelingId
        }.toMutableList()

        val feeling: Feeling ?= state.value.feelings.find {
            it.id == feelingId
        }
        val newFilterList: MutableList<Feeling> = state.value.filteredFeelings.toMutableList()

        if(feeling != null) {
            newFilterList.add(feeling)
            state.value = state.value.copy(filteredFeelings = newFilterList)
        }

        state.value = state.value.copy(selectedFeelings = newSelectedList)
    }

    private fun selectFeeling(feelingId: Int) {
        val newFilterList: MutableList<Feeling> = state.value.filteredFeelings.filter {
            it.id != feelingId
        }.toMutableList()

        val feeling: Feeling ?= state.value.feelings.find {
            it.id == feelingId
        }
        val newSelectedList: MutableList<Feeling> = state.value.selectedFeelings.toMutableList()

        if(feeling != null) {
            newSelectedList.add(feeling)
            state.value = state.value.copy(selectedFeelings = newSelectedList)
        }

        state.value = state.value.copy(filteredFeelings = newFilterList)
    }

    private fun filterFeelings() {
        val filteredList: MutableList<Feeling> = state.value.feelings.filter {
            it.name.lowercase().contains(state.value.feelingName.lowercase()) && !state.value.selectedFeelings.contains(it)
        }.toMutableList()

        state.value = state.value.copy(filteredFeelings = filteredList)
    }

    private fun updateFeelingName(feelingName: String) {
        state.value = state.value.copy(feelingName = feelingName)
    }

    private fun getFeelings() {

        getFeelings.execute().onEach { dataState ->

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
                    state.value = state.value.copy(feelings = dataState.data?: listOf())
                    filterFeelings()
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }
}