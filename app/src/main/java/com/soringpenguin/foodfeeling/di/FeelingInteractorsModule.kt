package com.soringpenguin.foodfeeling.di

import android.app.Application
import com.soringpenguin.feeling_interactors.FeelingInteractors
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
object FeelingInteractorsModule {

    @Provides
    @Singleton
    @Named("feelingAndroidSqlDriver")
    fun provideAndroidDriver(app: Application): SqlDriver {
        return  AndroidSqliteDriver(
            schema = FeelingInteractors.schema,
            context = app,
            name = FeelingInteractors.dbName

        )
    }

    @Provides
    @Singleton
    fun provideFeelingInteractors(
        @Named("feelingAndroidSqlDriver") sqlDriver: SqlDriver
    ): FeelingInteractors {
        return FeelingInteractors.build(
            sqlDriver = sqlDriver
        )
    }
}