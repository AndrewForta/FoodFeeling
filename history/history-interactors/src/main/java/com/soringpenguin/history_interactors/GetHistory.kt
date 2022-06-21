package com.soringpenguin.history_interactors

import com.soringpenguin.core.DataState
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.core.UIComponent
import com.soringpenguin.history_domain.HistoryEntry
import com.soringpenguin.history_datasource.cache.HistoryCache
import com.soringpenguin.history_datasource.network.HistoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHistory (
    private val cache: HistoryCache,
    private val service: HistoryService
        ){
    fun execute(): Flow<DataState<List<HistoryEntry>>> = flow {
        try{
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            try {
                val history: List<HistoryEntry> = service.getHistory()

                if(history.isNotEmpty()) {
                    cache.clearHistory()
                }

                // cache data
                for (h in history) {
                    cache.addHistoryEntry(h)
                }

                //emit cache
                val cachedHistory = cache.getAllHistoryEntries().sortedByDescending { historyEntry ->  historyEntry.createdOn }

                emit(DataState.Data(cachedHistory))
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
                listOf<HistoryEntry>()
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