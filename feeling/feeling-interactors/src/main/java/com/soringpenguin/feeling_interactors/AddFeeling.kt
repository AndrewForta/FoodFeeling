package com.soringpenguin.feeling_interactors

import com.soringpenguin.core.DataState
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.core.UIComponent
import com.soringpenguin.feeling_datasource.cache.FeelingCache
import com.soringpenguin.feeling_datasource.network.FeelingService
import com.soringpenguin.feeling_domain.Feeling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AddFeeling (
    private val cache: FeelingCache,
    private val service: FeelingService
){
    fun execute(feelingName: String): Flow<DataState<List<Feeling>>> = flow {
        try{
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            try {
                service.addFeeling(feelingName)

                val feelings: List<Feeling> = service.getFeelings()

                if(feelings.isNotEmpty()) {
                    cache.clearFeelings()
                }

                // cache data
                for (feeling in feelings) {
                    cache.addFeeling(feeling)
                }

                //emit cache
                val cachedFeelings = cache.getAllFeelings()

                emit(DataState.Data(cachedFeelings))
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