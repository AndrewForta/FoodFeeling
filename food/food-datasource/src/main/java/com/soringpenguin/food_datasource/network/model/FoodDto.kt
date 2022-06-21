package com.soringpenguin.food_datasource.network.model

import com.soringpenguin.food_domain.Food
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class FoodDto(

    @SerialName("food_id")
    val id: Int,

    @SerialName("food_name")
    val name: String

)

fun FoodDto.toFood(): Food {
    return Food(
        id = id,
        name = name
    )
}
