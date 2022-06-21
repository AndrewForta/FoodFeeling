package com.soringpenguin.feeling_interactors

import com.soringpenguin.core.DataState
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.core.UIComponent
import com.soringpenguin.feeling_datasource.network.FeelingService
import com.soringpenguin.feeling_domain.Feeling
import com.soringpenguin.feeling_domain.FeelingEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SubmitFeelingEntry(
    private val service: FeelingService
){
    fun execute(feelings: List<Feeling>): Flow<DataState<ProgressBarState.Loading>> = flow {
        val feelingEntries: List<FeelingEntry> = feelings.map { FeelingEntry(
            id = 0,
            feelingId = it.id,
            userId = 2,
            severity = 2,
            createdOn = "DOESNT MATTER"
        ) }
        try{
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            try {
                service.addFeelingEntry(feelingEntries)
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