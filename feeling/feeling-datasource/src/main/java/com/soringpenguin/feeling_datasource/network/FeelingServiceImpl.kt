package com.soringpenguin.feeling_datasource.network

import com.soringpenguin.feeling_datasource.network.model.*
import com.soringpenguin.feeling_domain.Feeling
import com.soringpenguin.feeling_domain.FeelingEntry
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay


class FeelingServiceImpl(
    private val httpClient: HttpClient
) : FeelingService {
    override suspend fun getFeelings(): List<Feeling> {
        return httpClient.get<List<FeelingDto>> {
            url(EndPoints.GET_FEELINGS)
        }.map { it.toFeeling() }
    }

    override suspend fun getFeelingEntries(): List<FeelingEntry> {
        return httpClient.get<List<FeelingEntryDto>> {
            url(EndPoints.GET_FEELING_ENTRIES)
        }.map { it.toFeelingEntry() }
    }

    override suspend fun addFeeling(feelingName: String) {
        return httpClient.post {
            url(EndPoints.ADD_FEELING + "/$feelingName")
        }
    }

    override suspend fun addFeelingEntry(feelings: List<FeelingEntry>) {
        val feelingsDto = FeelingListDto((feelings.map { FeelingEntryDto(
            id = it.id,
            feelingId = it.feelingId,
            userId = it.userId,
            severity = it.severity,
            createdOn = it.createdOn
        ) }))
        return httpClient.post {
            url(EndPoints.ADD_FEELING_ENTRY)
            body = feelingsDto
        }
    }

}