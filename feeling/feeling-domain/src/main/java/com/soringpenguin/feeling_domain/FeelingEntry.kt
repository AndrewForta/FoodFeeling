package com.soringpenguin.feeling_domain

import java.time.LocalDateTime

data class FeelingEntry(
    val id: Int,
    val feelingId: Int,
    val userId: Int,
    val severity: Int,
    val createdOn: String

) {
//    val createdOn: String = LocalDateTime.now().toString()
}