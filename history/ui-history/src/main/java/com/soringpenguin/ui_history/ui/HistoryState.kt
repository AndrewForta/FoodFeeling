package com.soringpenguin.ui_history.ui

import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.history_domain.HistoryEntry

data class HistoryState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val history: List<HistoryEntry> = listOf(),
)
