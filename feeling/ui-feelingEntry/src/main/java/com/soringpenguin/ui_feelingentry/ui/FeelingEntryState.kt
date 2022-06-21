package com.soringpenguin.ui_feelingentry.ui

import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.feeling_domain.Feeling

data class FeelingEntryState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val feelings: List<Feeling> = listOf(),
    val filteredFeelings: List<Feeling> = listOf(),
    val selectedFeelings: List<Feeling> = listOf(),
    val feelingName: String = ""
)
