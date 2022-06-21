package com.soringpenguin.history_datasource.cache

import com.soringpenguin.history_domain.HistoryEntry
import com.soringpenguin.historydatasource.cache.HistoryDbQueries

class HistoryCacheImpl(
    private val historyDatabase: HistoryDatabase
): HistoryCache {

    private var queries: HistoryDbQueries = historyDatabase.historyDbQueries

    override suspend fun getAllHistoryEntries(): List<HistoryEntry> {
        return queries.selectAllHistoryEntries().executeAsList().map { it.toHistoryEntry() }
    }

    override suspend fun addHistoryEntry(historyEntry: HistoryEntry) {
        print(historyEntry)
        return queries.insertHistoryEntry(
            history_entry_id = historyEntry.id.toLong(),
            history_entry_username = historyEntry.username,
            history_entry_name = historyEntry.name,
            history_entry_type = historyEntry.type,
            history_entry_created_on = historyEntry.createdOn
        )
    }

    override suspend fun clearHistory() {
        return queries.clearHistory()
    }
}