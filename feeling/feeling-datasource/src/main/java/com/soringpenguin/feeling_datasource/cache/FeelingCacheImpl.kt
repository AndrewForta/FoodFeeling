package com.soringpenguin.feeling_datasource.cache

import com.soringpenguin.feeling_domain.Feeling
import com.soringpenguin.feeling_domain.FeelingEntry
import com.soringpenguin.feelingdatasource.cache.FeelingDbQueries

class FeelingCacheImpl(
    private val feelingDatabase: FeelingDatabase
): FeelingCache {

    private var queries: FeelingDbQueries = feelingDatabase.feelingDbQueries

    override suspend fun getAllFeelings(): List<Feeling> {
        return queries.selectAllFeelings().executeAsList().map { it.toFeeling() }
    }

    override suspend fun getAllFeelingEntries(): List<FeelingEntry> {
        return queries.selectAllFeelingEntries().executeAsList().map { it.toFeelingEntry() }
    }

    override suspend fun addFeeling(feeling: Feeling) {
        return queries.insertFeeling(feeling.id.toLong(), feeling.name)
    }

    override suspend fun addFeelingByName(feelingName: String) {
        return queries.insertFeelingByName(feelingName)
    }

    override suspend fun clearFeelings() {
        return queries.clearFeelings()
    }
}