package com.example.data.model

enum class TarotArcana(val displayName: String) {
    MAJOR("Major Arcana"),
    MINOR("Minor Arcana")
}

enum class TarotSuit(val displayName: String, val element: String, val emoji: String) {
    NONE("Major Arcana", "Ether / Cosmos", "✨"),
    CUPS("Suit of Cups", "Water • Emotions & Love", "🏆"),
    PENTACLES("Suit of Pentacles", "Earth • Abundance & Body", "🪙"),
    SWORDS("Suit of Swords", "Air • Intellect & Truth", "⚔️"),
    WANDS("Suit of Wands", "Fire • Passion & Willpower", "🪄")
}

data class TarotCard(
    val id: String,
    val name: String,
    val arcana: TarotArcana,
    val suit: TarotSuit = TarotSuit.NONE,
    val number: Int,
    val emoji: String,
    val keywordsUpright: List<String>,
    val keywordsReversed: List<String>,
    val uprightMeaning: String,
    val reversedMeaning: String,
    val astrologicalAssociation: String,
    val psychologicalArchetype: String,
    val reflectionQuestion: String,
    val affirmativeMantra: String
)

enum class TarotSpreadType(
    val title: String,
    val subtitle: String,
    val cardCount: Int,
    val icon: String,
    val positions: List<Pair<String, String>>
) {
    DAILY_ONE_CARD(
        title = "Daily Focus Card",
        subtitle = "Single card guidance & cosmic energy for today",
        cardCount = 1,
        icon = "🔮",
        positions = listOf(
            "Present Focus" to "Core energy and mindful awareness for today"
        )
    ),
    PAST_PRESENT_FUTURE(
        title = "Past • Present • Future",
        subtitle = "Timeline of your psychological and energetic evolution",
        cardCount = 3,
        icon = "⏳",
        positions = listOf(
            "Past Roots" to "Foundations and experiences shaping the current cycle",
            "Present Reality" to "Where your conscious energy is actively flowing now",
            "Future Horizon" to "Emerging potential and optimal course of action"
        )
    ),
    MIND_BODY_SPIRIT(
        title = "Mind • Body • Spirit",
        subtitle = "Holistic alignment of intellect, somatic presence & soul",
        cardCount = 3,
        icon = "🌟",
        positions = listOf(
            "Mental Realm" to "Beliefs, thoughts, clarity, and mindset",
            "Physical & Somatic" to "Grounding, vitality, health, and material reality",
            "Spiritual Calling" to "Higher intuition, inner peace, and core purpose"
        )
    ),
    LOVE_RELATIONSHIP(
        title = "Love & Relationship Alignment",
        subtitle = "Interpersonal chemistry, mutual empathy & shared horizon",
        cardCount = 3,
        icon = "💖",
        positions = listOf(
            "Your Inner Heart" to "What you bring to connections and what you crave",
            "Shared Dynamic" to "Current interpersonal energy and mutual lessons",
            "Relationship Horizon" to "Long-term harmony, truth, and growth potential"
        )
    ),
    DECISION_CROSSROADS(
        title = "Decision Crossroads",
        subtitle = "Clarity through confusion, obstacles, and wise advice",
        cardCount = 3,
        icon = "🧭",
        positions = listOf(
            "Current Paradox" to "The fundamental choice or uncertainty at hand",
            "Hidden Obstacle" to "Shadow habits, blind spots, or unconscious doubts",
            "Higher Advice" to "The most empowering mindset and course of action"
        )
    )
}

data class DrawnCard(
    val card: TarotCard,
    val isReversed: Boolean,
    val positionTitle: String,
    val positionDescription: String
)

data class TarotReadingResult(
    val id: String = java.util.UUID.randomUUID().toString(),
    val spreadType: TarotSpreadType,
    val drawnCards: List<DrawnCard>,
    val timestamp: Long = System.currentTimeMillis(),
    val synthesisSummary: String,
    val astrologicalAlignment: String
)
