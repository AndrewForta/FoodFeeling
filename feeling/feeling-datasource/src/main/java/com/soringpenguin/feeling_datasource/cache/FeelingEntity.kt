package com.soringpenguin.feeling_datasource.cache

import com.soringpenguin.feeling_domain.Feeling
import com.soringpenguin.feelingdatasource.cache.Feeling_Entity

fun Feeling_Entity.toFeeling(): Feeling {
    return Feeling(
        id = feeling_id.toInt(),
        name = feeling_name
    )
}