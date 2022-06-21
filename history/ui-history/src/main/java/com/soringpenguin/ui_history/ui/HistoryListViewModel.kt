package com.soringpenguin.ui_history.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soringpenguin.core.DataState
import com.soringpenguin.core.Logger
import com.soringpenguin.core.UIComponent
import com.soringpenguin.history_interactors.GetHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryListViewModel
@Inject
constructor(
    private val getHistory: GetHistory,
): ViewModel(){
    private val logger = Logger("HistoryListViewModel")
    val state: MutableState<HistoryState> = mutableStateOf(HistoryState())

    init {
        onTriggerEvent(HistoryListEvents.GetHistory)
    }

    fun onTriggerEvent(event: HistoryListEvents) {
        when(event) {
            is HistoryListEvents.GetHistory -> {
                getHistory()
            }
        }
    }
    private fun getHistory() {

        getHistory.execute().onEach { dataState ->

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
                    state.value = state.value.copy(history = dataState.data?: listOf())
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }
}