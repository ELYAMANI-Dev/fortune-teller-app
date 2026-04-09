package com.moroccan.fortuneteller.data.model

data class ChatMessage(
    val id: Int,
    val text: String,
    val isUser: Boolean,
    val timestamp: String = ""
)

data class HoroscopeSign(
    val name: String,
    val emoji: String,
    val dateRange: String,
    val dailyReading: String,
    val luckNumber: Int,
    val luckColor: String
)

data class TarotCard(
    val name: String,
    val emoji: String,
    val meaning: String,
    val reversed: Boolean = false
)
