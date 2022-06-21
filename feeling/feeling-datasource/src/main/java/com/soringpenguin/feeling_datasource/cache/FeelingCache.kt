package com.soringpenguin.feeling_datasource.cache

import com.soringpenguin.feeling_domain.Feeling
import com.soringpenguin.feeling_domain.FeelingEntry
import com.squareup.sqldelight.db.SqlDriver

interface FeelingCache {

    suspend fun getAllFeelings(): List<Feeling>

    suspend fun getAllFeelingEntries(): List<FeelingEntry>

    suspend fun addFeeling(feeling: Feeling): Unit

    suspend fun addFeelingByName(feelingName: String): Unit

    suspend fun clearFeelings(): Unit

    companion object Factory {
        fun build(sqlDriver: SqlDriver): FeelingCache {
            return FeelingCacheImpl(FeelingDatabase(sqlDriver))
        }
        val schema: SqlDriver.Schema = FeelingDatabase.Schema

        val dbName: String = "feeling.db"
    }
}