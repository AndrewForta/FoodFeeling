package com.soringpenguin.feeling_datasource.network.model

@kotlinx.serialization.Serializable
data class FeelingListDto(
    val feelings: List<FeelingEntryDto>
)
