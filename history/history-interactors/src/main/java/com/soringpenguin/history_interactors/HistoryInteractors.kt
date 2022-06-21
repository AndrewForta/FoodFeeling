package com.soringpenguin.history_interactors

import com.soringpenguin.history_datasource.cache.HistoryCache
import com.soringpenguin.history_datasource.network.HistoryService
import com.squareup.sqldelight.db.SqlDriver

data class HistoryInteractors(
    val getHistory: GetHistory,
) {
    companion object Factory{
        fun build(
            sqlDriver: SqlDriver,
        ): HistoryInteractors {
            val service = HistoryService.build()
            val cache = HistoryCache.build(sqlDriver)
            return HistoryInteractors(
                getHistory = GetHistory(
                    service = service,
                    cache = cache
                )
            )
        }
        val schema: SqlDriver.Schema = HistoryCache.schema

        val dbName: String = HistoryCache.dbName
    }
}