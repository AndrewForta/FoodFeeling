package com.soringpenguin.history_domain

import java.time.LocalDateTime

data class HistoryEntry(
    val id: Int,
    val username: String,
    val name: String,
    val type: String,
    val createdOn: String

) {
//    val createdOn: String = LocalDateTime.now().toString()
}