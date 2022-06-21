package com.soringpenguin.ui_feelingentry.di

import com.soringpenguin.feeling_interactors.AddFeeling
import com.soringpenguin.feeling_interactors.FeelingInteractors
import com.soringpenguin.feeling_interactors.GetFeelings
import com.soringpenguin.feeling_interactors.SubmitFeelingEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeelingListModule {

    @Provides
    @Singleton
    fun provideGetFeelings(
        interactors: FeelingInteractors
    ): GetFeelings{
        return  interactors.getFeelings
    }

    @Provides
    @Singleton
    fun provideAddFeeling(
        interactors: FeelingInteractors
    ): AddFeeling{
        return  interactors.addFeeling
    }

    @Provides
    @Singleton
    fun provideSubmitFeelingEntry(
        interactors: FeelingInteractors
    ): SubmitFeelingEntry{
        return  interactors.submitFeelingEntry
    }
}