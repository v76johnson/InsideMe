package com.example.data.model

data class DailyHabitItem(
    val dayNumber: Int,
    val title: String,
    val description: String,
    val category: String, // "Mindset", "Emotional", "Astrological Action", "Shadow Work"
    var isCompleted: Boolean = false
)

data class DeepSynthesisReport(
    val id: String,
    val createdAtMillis: Long = System.currentTimeMillis(),
    val title: String,
    val archetypeSummary: String,
    val coreTraits: List<String>,
    val psychologicalBreakdown: String,
    val astrologicalSynthesis: String,
    val shadowWorkInsights: List<String>,
    val careerAndPurposeAdvice: String,
    val relationshipPlaybook: String,
    val dailyActionPlan: List<DailyHabitItem>,
    val isBookmarked: Boolean = false,
    val isGeneratedByAi: Boolean = true
)
