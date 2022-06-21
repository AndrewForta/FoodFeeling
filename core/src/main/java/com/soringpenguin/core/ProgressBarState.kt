package com.soringpenguin.core

sealed class ProgressBarState {

    object Loading: ProgressBarState()

    object Idle: ProgressBarState()
}