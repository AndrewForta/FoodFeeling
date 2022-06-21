package com.soringpenguin.foodfeeling.di

import android.app.Application
import com.soringpenguin.food_interactors.FoodInteractors
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
object FoodInteractorsModule {

    @Provides
    @Singleton
    @Named("foodAndroidSqlDriver")
    fun provideAndroidDriver(app: Application): SqlDriver {
        return  AndroidSqliteDriver(
            schema = FoodInteractors.schema,
            context = app,
            name = FoodInteractors.dbName

        )
    }

    @Provides
    @Singleton
    fun provideFoodInteractors(
        @Named("foodAndroidSqlDriver") sqlDriver: SqlDriver
    ): FoodInteractors {
        return FoodInteractors.build(
            sqlDriver = sqlDriver
        )
    }
}