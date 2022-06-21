package com.soringpenguin.food_domain

data class FoodEntry(
    val id: Int,
    val foodId: Int,
    val userId: Int,
    val quantity: Int,
    val createdOn: String

) {
//    val createdOn: String = LocalDateTime.now().toString()
}