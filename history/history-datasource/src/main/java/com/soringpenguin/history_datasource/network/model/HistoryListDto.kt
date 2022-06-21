package com.soringpenguin.history_datasource.network.model

@kotlinx.serialization.Serializable
data class HistoryListDto(
    val history: List<HistoryEntryDto>
)
