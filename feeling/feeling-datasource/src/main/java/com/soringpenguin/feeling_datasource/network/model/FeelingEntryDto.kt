package com.soringpenguin.feeling_datasource.network.model

import com.soringpenguin.feeling_domain.FeelingEntry
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FeelingEntryDto (
    @SerialName("feeling_entry_id")
    val id: Int,

    @SerialName("feeling_entry_feeling_id")
    val feelingId: Int,

    @SerialName("feeling_entry_user_id")
    val userId: Int,

    @SerialName("feeling_entry_severity")
    val severity: Int,

    @SerialName("feeling_entry_created_on")
    val createdOn: String
        )
fun FeelingEntryDto.toFeelingEntry(): FeelingEntry {
    return FeelingEntry(
        id = id,
        feelingId = feelingId,
        userId = userId,
        severity = severity,
        createdOn = createdOn
    )
}
