package com.soringpenguin.feeling_datasource.network

object EndPoints {
    const val BASE_URL = "http://192.168.0.2:80"
    const val GET_FEELINGS = "$BASE_URL/feeling/getAll"
    const val ADD_FEELING = "$BASE_URL/feeling/add"
    const val ADD_FEELING_ENTRY = "$BASE_URL/feelingEntry/add"
    const val GET_FEELING_ENTRIES = "$BASE_URL/feelingEntry/getAll"
}