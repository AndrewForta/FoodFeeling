package com.soringpenguin.ui_foodentry.di

import com.soringpenguin.food_interactors.AddFood
import com.soringpenguin.food_interactors.FoodInteractors
import com.soringpenguin.food_interactors.GetFood
import com.soringpenguin.food_interactors.SubmitFoodEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodListModule {

    @Provides
    @Singleton
    fun provideGetFood(
        interactors: FoodInteractors
    ): GetFood{
        return  interactors.getFood
    }

    @Provides
    @Singleton
    fun provideAddFood(
        interactors: FoodInteractors
    ): AddFood{
        return  interactors.addFood
    }

    @Provides
    @Singleton
    fun provideSubmitFoodEntry(
        interactors: FoodInteractors
    ): SubmitFoodEntry{
        return  interactors.submitFoodEntry
    }
}