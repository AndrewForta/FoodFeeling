package com.soringpenguin.ui_feelingentry.ui

import com.soringpenguin.feeling_domain.Feeling

sealed class FeelingListEvents {

    object GetFeelings: FeelingListEvents()

    object FilterFeelings: FeelingListEvents()

    data class UpdateFeelingName(
        val feelingName: String
    ): FeelingListEvents()

    data class SelectFeeling(
        val feelingId: Int
    ): FeelingListEvents()

    data class DeselectFeeling(
        val feelingId: Int
    ): FeelingListEvents()

    data class AddFeeling(
        val feelingName: String
    ): FeelingListEvents()

    data class SubmitFeelingEntry(
        val feelings: List<Feeling>
    ): FeelingListEvents()
}
