package com.soringpenguin.food_datasource.network

object EndPoints {
    const val BASE_URL = "http://192.168.0.2:80"
    const val GET_FOODS = "$BASE_URL/food/getAll"
    const val ADD_FOOD = "$BASE_URL/food/add"
    const val ADD_FOOD_ENTRY = "$BASE_URL/foodEntry/add"
    const val GET_FOOD_ENTRIES = "$BASE_URL/foodEntry/getAll"
}