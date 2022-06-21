package com.soringpenguin.foodfeeling.di

import android.app.Application
import com.soringpenguin.history_interactors.HistoryInteractors
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HistoryInteractorModule {


        @Provides
        @Singleton
        @Named("historyAndroidSqlDriver")
        fun provideAndroidDriver(app: Application): SqlDriver {
            return  AndroidSqliteDriver(
                schema = HistoryInteractors.schema,
                context = app,
                name = HistoryInteractors.dbName

            )
        }

        @Provides
        @Singleton
        fun provideHistoryInteractors(
            @Named("historyAndroidSqlDriver") sqlDriver: SqlDriver
        ): HistoryInteractors {
            return HistoryInteractors.build(
                sqlDriver = sqlDriver
            )
        }

}