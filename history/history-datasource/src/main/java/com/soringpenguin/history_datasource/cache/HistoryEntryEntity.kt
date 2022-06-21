package com.soringpenguin.history_datasource.cache

import com.soringpenguin.history_domain.HistoryEntry
import com.soringpenguin.historydatasource.cache.History_Entry_Entity

fun History_Entry_Entity.toHistoryEntry(): HistoryEntry {
    return HistoryEntry(
        id = history_entry_id.toInt(),
        username = history_entry_username,
        name = history_entry_name,
        type = history_entry_type,
        createdOn = history_entry_created_on
    )
}