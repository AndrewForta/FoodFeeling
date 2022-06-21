package com.soringpenguin.feeling_datasource.network.model

import com.soringpenguin.feeling_domain.Feeling
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FeelingDto(

    @SerialName("feeling_id")
    val id: Int,

    @SerialName("feeling_name")
    val name: String

)

fun FeelingDto.toFeeling(): Feeling {
    return Feeling(
        id = id,
        name = name
    )
}
