package com.soringpenguin.ui_history.di

import com.soringpenguin.history_interactors.GetHistory
import com.soringpenguin.history_interactors.HistoryInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HistoryListModule {

    @Provides
    @Singleton
    fun provideGetHistory(
        interactors: HistoryInteractors
    ): GetHistory {
        return  interactors.getHistory
    }
}