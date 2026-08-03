package com.example.data.model

data class NameEtymologySource(
    val languageOrCulture: String,
    val literalMeaning: String,
    val historicalContext: String
)

data class NameMeaningReport(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val etymologies: List<NameEtymologySource>,
    val parentalIntentCategory: String,
    val parentalIntentPsychology: String,
    val personalityEffects: String,
    val numerologicalVibration: String,
    val shadowIntegrationAdvice: String,
    val createdAtMillis: Long = System.currentTimeMillis()
)
