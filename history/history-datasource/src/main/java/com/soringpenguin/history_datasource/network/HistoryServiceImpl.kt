package com.soringpenguin.history_datasource.network

import com.soringpenguin.history_datasource.network.model.HistoryEntryDto
import com.soringpenguin.history_datasource.network.model.toHistoryEntry
import com.soringpenguin.history_domain.HistoryEntry
import io.ktor.client.*
import io.ktor.client.request.*


class HistoryServiceImpl(
    private val httpClient: HttpClient
) : HistoryService {
    override suspend fun getHistory(): List<HistoryEntry> {
        val list = httpClient.get<List<HistoryEntryDto>> {
            url(EndPoints.GET_HISTORY)
        }.map { it.toHistoryEntry() }
        println(list)
        return list
    }

}