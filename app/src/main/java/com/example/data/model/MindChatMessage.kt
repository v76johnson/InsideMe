package com.example.data.model

import java.util.UUID

data class MindChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val sender: String, // "user" or "companion"
    val text: String,
    val timestampMillis: Long = System.currentTimeMillis()
)
