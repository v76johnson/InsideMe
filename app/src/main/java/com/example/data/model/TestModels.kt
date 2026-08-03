package com.example.data.model

enum class TestCategory(val displayName: String, val iconName: String) {
    NEURODEVELOPMENTAL("Neurodevelopmental Screeners (ADHD & ASD)", "Psychology"),
    MOOD_ANXIETY("Mood & Anxiety Screeners", "Psychology"),
    TRAUMA_OBSESSIONS_PERSONALITY("Trauma, Obsessions & Personality Screeners", "Psychology"),
    SUBSTANCE_USE("Substance Use Screeners", "Psychology"),
    MAJOR_PERSONALITY("Major Personality & Archetypes", "Psychology"),
    RELATIONSHIP("Relationship, Bonding & Attachment", "Favorite"),
    EMOTIONAL_INTELLIGENCE("EQ & Resilience Skills", "SelfImprovement"),
    PROJECTIVE("Projective Depth & Diagnostic Tools", "Psychology")
}

data class Choice(
    val id: String,
    val text: String,
    val traitKey: String, // e.g. "OCEAN_OPENNESS", "MBTI_E", "ENNEAGRAM_4"
    val weight: Int = 1
)

data class Question(
    val id: Int,
    val text: String,
    val choices: List<Choice>
)

data class PsychologyTest(
    val id: String,
    val title: String,
    val category: TestCategory,
    val description: String,
    val durationMinutes: Int,
    val questions: List<Question>,
    val badgeText: String = "Comprehensive",
    val testsForLabel: String = ""
)
