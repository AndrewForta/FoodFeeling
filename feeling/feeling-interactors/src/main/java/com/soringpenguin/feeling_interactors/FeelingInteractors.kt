package com.soringpenguin.feeling_interactors

import com.soringpenguin.feeling_datasource.cache.FeelingCache
import com.soringpenguin.feeling_datasource.network.FeelingService
import com.squareup.sqldelight.db.SqlDriver

data class FeelingInteractors(
    val getFeelings: GetFeelings,
    val addFeeling: AddFeeling,
    val submitFeelingEntry: SubmitFeelingEntry,
) {
    companion object Factory{
        fun build(
            sqlDriver: SqlDriver,
        ): FeelingInteractors {
            val service = FeelingService.build()
            val cache = FeelingCache.build(sqlDriver)
            return FeelingInteractors(
                getFeelings = GetFeelings(
                    service = service,
                    cache = cache
                ),
                addFeeling = AddFeeling(
                    cache = cache,
                    service = service,
                ),
                submitFeelingEntry = SubmitFeelingEntry(
                    service = service
                )

            )
        }
        val schema: SqlDriver.Schema = FeelingCache.schema

        val dbName: String = FeelingCache.dbName
    }
}