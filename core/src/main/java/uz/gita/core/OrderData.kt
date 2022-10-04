package uz.gita.core

import java.util.*

data class OrderData(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val image: String,
    val cost: String,
    val count: String,
    val status: String
)