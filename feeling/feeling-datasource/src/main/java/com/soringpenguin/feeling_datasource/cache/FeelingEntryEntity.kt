package com.soringpenguin.feeling_datasource.cache

import com.soringpenguin.feeling_domain.FeelingEntry
import com.soringpenguin.feelingdatasource.cache.Feeling_Entry_Entity

fun Feeling_Entry_Entity.toFeelingEntry(): FeelingEntry {
    return FeelingEntry(
        id = feeling_entry_id.toInt(),
        userId = feeling_entry_user_id.toInt(),
        feelingId = feeling_entry_feeling_id.toInt(),
        severity = feeling_entry_severity.toInt(),
        createdOn = feeling_entry_created_on
    )
}