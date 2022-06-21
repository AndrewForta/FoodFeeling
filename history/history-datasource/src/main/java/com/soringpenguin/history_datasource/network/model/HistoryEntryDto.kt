package com.soringpenguin.history_datasource.network.model

import com.soringpenguin.history_domain.HistoryEntry
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class HistoryEntryDto (
    @SerialName("history_entry_id")
    val id: Int,

    @SerialName("history_entry_username")
    val username: String,

    @SerialName("history_entry_name")
    val name: String,

    @SerialName("history_entry_type")
    val type: String,

    @SerialName("history_entry_created_on")
    val createdOn: String
        )
fun HistoryEntryDto.toHistoryEntry(): HistoryEntry {
    return HistoryEntry(
        id = id,
        username = username,
        name = name,
        type = type,
        createdOn = createdOn
    )
}
