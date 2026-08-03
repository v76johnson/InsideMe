package com.example.data.model

enum class ZodiacElement(val displayName: String, val colorHex: String) {
    FIRE("Fire", "#EF476F"),
    EARTH("Earth", "#06D6A0"),
    AIR("Air", "#FFD166"),
    WATER("Water", "#118AB2")
}

enum class ZodiacModality(val displayName: String) {
    CARDINAL("Cardinal"),
    FIXED("Fixed"),
    MUTABLE("Mutable")
}

enum class ZodiacSign(
    val displayName: String,
    val symbol: String,
    val dateRange: String,
    val element: ZodiacElement,
    val modality: ZodiacModality,
    val rulingPlanet: String,
    val keywords: List<String>
) {
    ARIES("Aries", "♈", "Mar 21 - Apr 19", ZodiacElement.FIRE, ZodiacModality.CARDINAL, "Mars", listOf("Pioneer", "Dynamic", "Courageous", "Bold")),
    TAURUS("Taurus", "♉", "Apr 20 - May 20", ZodiacElement.EARTH, ZodiacModality.FIXED, "Venus", listOf("Grounded", "Sensual", "Steadfast", "Patient")),
    GEMINI("Gemini", "♊", "May 21 - Jun 20", ZodiacElement.AIR, ZodiacModality.MUTABLE, "Mercury", listOf("Curious", "Adaptable", "Witty", "Expressive")),
    CANCER("Cancer", "♋", "Jun 21 - Jul 22", ZodiacElement.WATER, ZodiacModality.CARDINAL, "Moon", listOf("Intuitive", "Nurturing", "Empathetic", "Protective")),
    LEO("Leo", "♌", "Jul 23 - Aug 22", ZodiacElement.FIRE, ZodiacModality.FIXED, "Sun", listOf("Charismatic", "Radiant", "Generous", "Creative")),
    VIRGO("Virgo", "♍", "Aug 23 - Sep 22", ZodiacElement.EARTH, ZodiacModality.MUTABLE, "Mercury", listOf("Analytical", "Meticulous", "Healing", "Practical")),
    LIBRA("Libra", "♎", "Sep 23 - Oct 22", ZodiacElement.AIR, ZodiacModality.CARDINAL, "Venus", listOf("Harmonious", "Diplomatic", "Aesthetic", "Fair")),
    SCORPIO("Scorpio", "♏", "Oct 23 - Nov 21", ZodiacElement.WATER, ZodiacModality.FIXED, "Pluto & Mars", listOf("Transformative", "Intense", "Perceptive", "Magnetic")),
    SAGITTARIUS("Sagittarius", "♐", "Nov 22 - Dec 21", ZodiacElement.FIRE, ZodiacModality.MUTABLE, "Jupiter", listOf("Philosophical", "Adventurous", "Optimistic", "Free")),
    CAPRICORN("Capricorn", "♑", "Dec 22 - Jan 19", ZodiacElement.EARTH, ZodiacModality.CARDINAL, "Saturn", listOf("Ambitious", "Disciplined", "Masterful", "Resilient")),
    AQUARIUS("Aquarius", "♒", "Jan 20 - Feb 18", ZodiacElement.AIR, ZodiacModality.FIXED, "Uranus", listOf("Visionary", "Humanitarian", "Original", "Independent")),
    PISCES("Pisces", "♓", "Feb 19 - Mar 20", ZodiacElement.WATER, ZodiacModality.MUTABLE, "Neptune", listOf("Mystical", "Compassionate", "Dreamy", "Artistic"))
}

data class AstrologyProfile(
    val birthDateMillis: Long = System.currentTimeMillis(),
    val birthTime: String = "12:00",
    val birthCity: String = "New York, USA",
    val sunSign: ZodiacSign = ZodiacSign.SCORPIO,
    val moonSign: ZodiacSign = ZodiacSign.PISCES,
    val risingSign: ZodiacSign = ZodiacSign.CANCER,
    val userName: String = "",
    val savedNameAdditions: List<String> = emptyList()
)

data class SynastryMatch(
    val sign1: ZodiacSign,
    val sign2: ZodiacSign,
    val scorePercentage: Int,
    val title: String,
    val elementalChemistry: String,
    val harmonyPoints: List<String>,
    val frictionPoints: List<String>,
    val relationshipAdvice: String
)

data class AstrologyChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val sender: String, // "user" or "oracle"
    val text: String,
    val timestampMillis: Long = System.currentTimeMillis()
)

data class InDepthMatchReport(
    val id: String = java.util.UUID.randomUUID().toString(),
    val person1Name: String,
    val person1Dob: String,
    val person1Time: String,
    val person1City: String,
    val person1Sun: ZodiacSign,
    val person1Moon: ZodiacSign,
    val person1Rising: ZodiacSign,
    val person2Name: String,
    val person2Dob: String,
    val person2Time: String,
    val person2City: String,
    val person2Sun: ZodiacSign,
    val person2Moon: ZodiacSign,
    val person2Rising: ZodiacSign,
    val compatibilityScore: Int,
    val title: String,
    val elementalChemistry: String,
    val emotionalResonance: String,
    val communicationDynamics: String,
    val passionAndAttraction: String,
    val harmonyPoints: List<String>,
    val frictionPoints: List<String>,
    val actionableAdvice: String,
    val createdAtMillis: Long = System.currentTimeMillis()
)

data class CustomNatalChart(
    val id: String = java.util.UUID.randomUUID().toString(),
    val personName: String,
    val birthDateMillis: Long,
    val birthTime: String,
    val birthCity: String,
    val sunSign: ZodiacSign,
    val moonSign: ZodiacSign,
    val risingSign: ZodiacSign,
    val notes: String = "",
    val createdAtMillis: Long = System.currentTimeMillis()
)
