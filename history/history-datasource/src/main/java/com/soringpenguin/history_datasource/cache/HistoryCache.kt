package com.soringpenguin.history_datasource.cache

import com.soringpenguin.history_domain.HistoryEntry
import com.squareup.sqldelight.db.SqlDriver

interface HistoryCache {
    
    suspend fun getAllHistoryEntries(): List<HistoryEntry>

    suspend fun addHistoryEntry(historyEntry: HistoryEntry): Unit

    suspend fun clearHistory(): Unit

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HistoryCache {
            return HistoryCacheImpl(HistoryDatabase(sqlDriver))
        }
        val schema: SqlDriver.Schema = HistoryDatabase.Schema

        val dbName: String = "history.db"
    }
}