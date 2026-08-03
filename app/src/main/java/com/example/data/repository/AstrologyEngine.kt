package com.example.data.repository

import com.example.data.model.AstrologyProfile
import com.example.data.model.SynastryMatch
import com.example.data.model.ZodiacElement
import com.example.data.model.ZodiacSign
import java.util.Calendar

data class NumerologyInfo(
    val lifePathNumber: Int,
    val title: String,
    val archetype: String,
    val keywords: String,
    val description: String,
    val coreStrengths: List<String>,
    val growthAreas: List<String>,
    val compatibleLifePaths: String
)

data class ChineseZodiacInfo(
    val birthYear: Int,
    val animal: String,
    val animalEmoji: String,
    val element: String,
    val elementSymbol: String,
    val yinYang: String,
    val fullSign: String,
    val personalityTraits: String,
    val luckyNumbers: String,
    val luckyColors: String,
    val bestMatches: String,
    val elementMeaning: String
)

object AstrologyEngine {

    fun calculateSunSign(month: Int, day: Int): ZodiacSign {
        return when (month) {
            1 -> if (day <= 19) ZodiacSign.CAPRICORN else ZodiacSign.AQUARIUS
            2 -> if (day <= 18) ZodiacSign.AQUARIUS else ZodiacSign.PISCES
            3 -> if (day <= 20) ZodiacSign.PISCES else ZodiacSign.ARIES
            4 -> if (day <= 19) ZodiacSign.ARIES else ZodiacSign.TAURUS
            5 -> if (day <= 20) ZodiacSign.TAURUS else ZodiacSign.GEMINI
            6 -> if (day <= 20) ZodiacSign.GEMINI else ZodiacSign.CANCER
            7 -> if (day <= 22) ZodiacSign.CANCER else ZodiacSign.LEO
            8 -> if (day <= 22) ZodiacSign.LEO else ZodiacSign.VIRGO
            9 -> if (day <= 22) ZodiacSign.VIRGO else ZodiacSign.LIBRA
            10 -> if (day <= 21) ZodiacSign.LIBRA else ZodiacSign.SCORPIO
            11 -> if (day <= 21) ZodiacSign.SCORPIO else ZodiacSign.SAGITTARIUS
            12 -> if (day <= 21) ZodiacSign.SAGITTARIUS else ZodiacSign.CAPRICORN
            else -> ZodiacSign.SCORPIO
        }
    }

    fun calculateProfileFromDate(dateMillis: Long, timeStr: String, cityStr: String): AstrologyProfile {
        val cal = Calendar.getInstance().apply { timeInMillis = dateMillis }
        val month = cal.get(Calendar.MONTH) + 1 // 1-12
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val hour = try { timeStr.split(":")[0].toInt() } catch (e: Exception) { 12 }

        val sunSign = calculateSunSign(month, day)

        // Calculate Moon sign offset based on day
        val moonIndex = (sunSign.ordinal + (day % 4) + 2) % 12
        val moonSign = ZodiacSign.entries[moonIndex]

        // Calculate Rising/Ascendant based on birth hour
        val risingIndex = (sunSign.ordinal + (hour / 2)) % 12
        val risingSign = ZodiacSign.entries[risingIndex]

        return AstrologyProfile(
            birthDateMillis = dateMillis,
            birthTime = timeStr,
            birthCity = cityStr,
            sunSign = sunSign,
            moonSign = moonSign,
            risingSign = risingSign
        )
    }

    fun getDailyHoroscope(sign: ZodiacSign): String {
        val days = listOf("Jupiter's transit highlights your creative expansion.", "Mercury encourages clear strategic communication today.", "The Moon illuminates your inner emotional depths, bringing intuitive clarity.", "Venus aligns with your financial and aesthetic ambitions.", "Saturn brings grounded discipline to your personal goals.")
        val hash = (sign.ordinal + Calendar.getInstance().get(Calendar.DAY_OF_YEAR)) % days.size
        return "Today for ${sign.displayName} (${sign.symbol}): ${days[hash]} Focus on balancing your core ${sign.element.displayName} element energy."
    }

    fun calculateSynastry(sign1: ZodiacSign, sign2: ZodiacSign): SynastryMatch {
        val elementMatch = sign1.element == sign2.element
        val complementary = (sign1.element == ZodiacElement.FIRE && sign2.element == ZodiacElement.AIR) ||
                (sign1.element == ZodiacElement.AIR && sign2.element == ZodiacElement.FIRE) ||
                (sign1.element == ZodiacElement.EARTH && sign2.element == ZodiacElement.WATER) ||
                (sign1.element == ZodiacElement.WATER && sign2.element == ZodiacElement.EARTH)

        val score = when {
            elementMatch -> 92
            complementary -> 88
            sign1 == sign2 -> 85
            else -> 74
        }

        val title = if (score >= 88) "Cosmic Synergy & Deep Resonance" else "Dynamic Alchemy & Mutual Growth"

        return SynastryMatch(
            sign1 = sign1,
            sign2 = sign2,
            scorePercentage = score,
            title = title,
            elementalChemistry = "${sign1.element.displayName} (${sign1.symbol}) + ${sign2.element.displayName} (${sign2.symbol})",
            harmonyPoints = listOf(
                "Shared desire for authentic connection",
                "Balancing strengths in emotional & intellectual domains",
                "Natural spark in collaborative goals"
            ),
            frictionPoints = listOf(
                "Navigating differing emotional pacing",
                "Communication subtle nuances under stress"
            ),
            relationshipAdvice = "Focus on honoring each other's elemental needs: ${sign1.displayName} thrives on respect, while ${sign2.displayName} values emotional authenticity."
        )
    }

    private fun reduceDigits(n: Int, allowMaster: Boolean = true): Int {
        var current = n
        while (current > 9) {
            if (allowMaster && (current == 11 || current == 22 || current == 33)) return current
            var sum = 0
            var temp = current
            while (temp > 0) {
                sum += temp % 10
                temp /= 10
            }
            current = sum
        }
        return current
    }

    fun calculateLifePath(dateMillis: Long): NumerologyInfo {
        val cal = Calendar.getInstance().apply { timeInMillis = dateMillis }
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val year = cal.get(Calendar.YEAR)

        val monthReduced = reduceDigits(month)
        val dayReduced = reduceDigits(day)
        val yearReduced = reduceDigits(year)

        val totalSum = monthReduced + dayReduced + yearReduced
        val lifePath = reduceDigits(totalSum)

        return when (lifePath) {
            1 -> NumerologyInfo(
                lifePathNumber = 1,
                title = "Life Path 1 — The Pioneer & Leader",
                archetype = "The Leader",
                keywords = "Independence, Innovation, Originality, Determination",
                description = "Driven by an innate impulse to create new paths and take charge. You possess a pioneering spirit, fierce independence, and the courage to forge uncharted territories.",
                coreStrengths = listOf("Self-reliance & initiative", "Pioneering vision", "Strong willpower"),
                growthAreas = listOf("Patience with others", "Delegating responsibility", "Avoiding stubbornness"),
                compatibleLifePaths = "Life Paths 3, 5, 7"
            )
            2 -> NumerologyInfo(
                lifePathNumber = 2,
                title = "Life Path 2 — The Peacemaker & Diplomat",
                archetype = "The Peacemaker",
                keywords = "Harmony, Intuition, Cooperation, Empathy",
                description = "A natural mediator with heightened sensitivity and deep intuition. You excel at fostering harmony, building meaningful relationships, and sensing underlying dynamics.",
                coreStrengths = listOf("Emotional intelligence", "Diplomacy & tact", "Supportive partnership"),
                growthAreas = listOf("Setting clear boundaries", "Overcoming self-doubt", "Expressing personal needs"),
                compatibleLifePaths = "Life Paths 2, 4, 6, 8"
            )
            3 -> NumerologyInfo(
                lifePathNumber = 3,
                title = "Life Path 3 — The Creative Communicator",
                archetype = "The Creator",
                keywords = "Self-Expression, Optimism, Artistry, Charisma",
                description = "Blessed with vibrant creative energy, social magnetic charm, and a gift for communication. You inspire others through art, words, humor, and infectious enthusiasm.",
                coreStrengths = listOf("Artistic brilliance", "Inspirational communication", "Joyful magnetism"),
                growthAreas = listOf("Sustaining deep focus", "Managing emotional highs/lows", "Financial discipline"),
                compatibleLifePaths = "Life Paths 1, 5, 9"
            )
            4 -> NumerologyInfo(
                lifePathNumber = 4,
                title = "Life Path 4 — The Master Architect & Strategist",
                archetype = "The Builder",
                keywords = "Stability, Order, Discipline, Practical Mastery",
                description = "The foundation builder of society. You embody method, integrity, and relentless perseverance, transforming ambitious concepts into concrete, enduring structures.",
                coreStrengths = listOf("Methodical execution", "Rock-solid reliability", "Strategic foundation"),
                growthAreas = listOf("Embracing unexpected change", "Loosening rigid expectations", "Allowing spontaneity"),
                compatibleLifePaths = "Life Paths 2, 4, 6, 8"
            )
            5 -> NumerologyInfo(
                lifePathNumber = 5,
                title = "Life Path 5 — The Freedom Seeker & Catalyst",
                archetype = "The Catalyst",
                keywords = "Versatility, Freedom, Adventure, Curiosity",
                description = "A dynamic agent of change who thrives on freedom, exploration, and sensory experience. You possess an adaptable mind and an unquenchable thirst for life.",
                coreStrengths = listOf("Rapid adaptability", "Fearless curiosity", "Magnetic charisma"),
                growthAreas = listOf("Long-term commitment", "Avoiding restlessness", "Grounding physical energy"),
                compatibleLifePaths = "Life Paths 1, 3, 7"
            )
            6 -> NumerologyInfo(
                lifePathNumber = 6,
                title = "Life Path 6 — The Nurturer & Protector",
                archetype = "The Caregiver",
                keywords = "Compassion, Responsibility, Family, Harmony",
                description = "Deeply devoted to the well-being of loved ones and community. You radiate warmth, aesthetic balance, and a strong sense of protective duty.",
                coreStrengths = listOf("Unconditional empathy", "Aesthetic sensibility", "Nurturing sanctuary"),
                growthAreas = listOf("Avoiding over-sacrificing", "Releasing perfectionism", "Allowing others self-reliance"),
                compatibleLifePaths = "Life Paths 2, 3, 6, 9"
            )
            7 -> NumerologyInfo(
                lifePathNumber = 7,
                title = "Life Path 7 — The Seeker & Mystic",
                archetype = "The Truth Seeker",
                keywords = "Wisdom, Analytical Depth, Intuition, Mystery",
                description = "A quiet seeker of universal truths and deep knowledge. You merge keen analytical precision with profound spiritual intuition, needing solitary time to recharge.",
                coreStrengths = listOf("Sharp analytical intellect", "Spiritual intuition", "Uncovering hidden truths"),
                growthAreas = listOf("Overcoming isolation", "Vulnerable connection", "Trusting emotional instinct"),
                compatibleLifePaths = "Life Paths 1, 5, 7"
            )
            8 -> NumerologyInfo(
                lifePathNumber = 8,
                title = "Life Path 8 — The Powerhouse & Executive",
                archetype = "The Manifestor",
                keywords = "Ambition, Mastery, Authority, Material Success",
                description = "Endowed with natural authority, executive vision, and financial acumen. You are built to master the physical world, build empires, and lead with strength.",
                coreStrengths = listOf("Executive leadership", "Resource manifestation", "Resilient power"),
                growthAreas = listOf("Balancing power & compassion", "Releasing control urges", "Spiritual grounding"),
                compatibleLifePaths = "Life Paths 2, 4, 6, 8"
            )
            9 -> NumerologyInfo(
                lifePathNumber = 9,
                title = "Life Path 9 — The Humanitarian & Visionary",
                archetype = "The Visionary",
                keywords = "Universal Love, Wisdom, Transformation, Completion",
                description = "An old soul possessing vast compassion and global vision. You carry the wisdom of all numbers before you, dedicated to uplifting humanity and completing cycles.",
                coreStrengths = listOf("Universal empathy", "Artistic breadth", "Transformative wisdom"),
                growthAreas = listOf("Releasing past burdens", "Accepting personal boundaries", "Avoiding cynicism"),
                compatibleLifePaths = "Life Paths 3, 6, 9"
            )
            11 -> NumerologyInfo(
                lifePathNumber = 11,
                title = "Life Path 11 — The Intuitive Illuminator (Master)",
                archetype = "The Intuitive Master",
                keywords = "Master Intuition, Spiritual Channel, High Energy, Vision",
                description = "A Master Number of heightened spiritual sensitivity and cosmic insight. You act as a walking conduit for inspiration, illuminating truth for those around you.",
                coreStrengths = listOf("Electric intuition", "Visionary inspiration", "Profound empathy"),
                growthAreas = listOf("Managing nervous sensitivity", "Grounding high frequencies", "Patience with physical pacing"),
                compatibleLifePaths = "Life Paths 2, 7, 11, 22"
            )
            22 -> NumerologyInfo(
                lifePathNumber = 22,
                title = "Life Path 22 — The Master Architect (Master)",
                archetype = "The Master Builder",
                keywords = "Practical Mastery, Grand Vision, Empire Building, Legacy",
                description = "The most powerful builder in numerology. You combine the spiritual vision of 11 with the practical execution of 4 to turn monumental dreams into reality.",
                coreStrengths = listOf("Monumental execution", "Practical genius", "Unwavering stamina"),
                growthAreas = listOf("Avoiding overwhelming stress", "Patience with scale", "Self-compassion"),
                compatibleLifePaths = "Life Paths 4, 8, 11, 22"
            )
            33 -> NumerologyInfo(
                lifePathNumber = 33,
                title = "Life Path 33 — The Master Teacher (Master)",
                archetype = "The Cosmic Guide",
                keywords = "Universal Healing, Spiritual Mastery, Compassion, Light",
                description = "The Master Teacher of universal love and spiritual guidance. You carry a rare frequency of selfless devotion, uplifting souls through wisdom and unconditional compassion.",
                coreStrengths = listOf("Unconditional empathy", "Spiritual healing", "Transformative presence"),
                growthAreas = listOf("Setting healthy boundaries", "Guarding emotional energy", "Self-nurturing"),
                compatibleLifePaths = "Life Paths 6, 9, 33"
            )
            else -> NumerologyInfo(
                lifePathNumber = lifePath,
                title = "Life Path $lifePath — The Cosmic Explorer",
                archetype = "The Explorer",
                keywords = "Balance, Synthesis, Purpose, Wisdom",
                description = "A unique cosmic blueprint combining planetary harmony and spiritual direction.",
                coreStrengths = listOf("Adaptability", "Cosmic resonance", "Intuitive balance"),
                growthAreas = listOf("Focus", "Self-belief"),
                compatibleLifePaths = "All Life Paths"
            )
        }
    }

    fun calculateChineseZodiac(dateMillis: Long): ChineseZodiacInfo {
        val cal = Calendar.getInstance().apply { timeInMillis = dateMillis }
        val year = cal.get(Calendar.YEAR)

        val animals = listOf(
            Triple("Rat", "🐀", "Clever, quick-witted, resourceful, and charming. Excellent at identifying golden opportunities."),
            Triple("Ox", "🐂", "Dependable, strong, methodical, and steadfast. Built for long-term endurance and loyalty."),
            Triple("Tiger", "🐅", "Brave, magnetic, competitive, and unpredictable. A courageous born leader with high charisma."),
            Triple("Rabbit", "🐇", "Elegant, gentle, compassionate, and artistic. Values peace, beauty, and harmonious refined spaces."),
            Triple("Dragon", "🐉", "Charismatic, ambitious, powerful, and visionary. Radiates magnetic royal energy and enthusiasm."),
            Triple("Snake", "🐍", "Enigmatic, wise, intuitive, and calm. A deep thinker with intense inner focus and elegance."),
            Triple("Horse", "🐎", "Energetic, independent, free-spirited, and warm. Loves freedom, travel, and active pursuit of passions."),
            Triple("Goat", "🐐", "Artistic, gentle, empathetic, and creative. A gentle soul with deep appreciation for art and nature."),
            Triple("Monkey", "🐒", "Inventive, playful, sharp-minded, and versatile. A brilliant problem-solver with quick wit."),
            Triple("Rooster", "🐓", "Observant, hardworking, expressive, and precise. Values truth, punctuality, and vibrant self-presentation."),
            Triple("Dog", "🐕", "Loyal, honest, protective, and trustworthy. Built on deep moral integrity and unwavering loyalty."),
            Triple("Pig", "🐖", "Generous, warm-hearted, philosophical, and noble. Enjoyer of life's finer pleasures and genuine connections.")
        )

        // Year offset starting from Rat (e.g., 1900 was Rat, 1900 - 4 = 1896 % 12 = 0)
        val animalIndex = Math.floorMod(year - 4, 12)
        val (animalName, emoji, traits) = animals[animalIndex]

        val lastDigit = Math.abs(year % 10)
        val (elementName, symbol, elementDesc) = when (lastDigit) {
            0, 1 -> Triple("Metal", "🪙", "Metal bestows structure, unyielding discipline, clarity, and rock-solid determination.")
            2, 3 -> Triple("Water", "🌊", "Water bestows emotional depth, fluid adaptability, sharp intuition, and wisdom.")
            4, 5 -> Triple("Wood", "🌿", "Wood bestows rapid creative growth, vitality, high morale, and expansive vision.")
            6, 7 -> Triple("Fire", "🔥", "Fire bestows intense passion, dynamism, leadership warmth, and electric inspiration.")
            8, 9 -> Triple("Earth", "⛰️", "Earth bestows grounding stability, patience, practical endurance, and nurturing balance.")
            else -> Triple("Earth", "⛰️", "Earth bestows grounding stability.")
        }

        val isEven = (year % 2 == 0)
        val yinYang = if (isEven) "Yang (Active & Outward)" else "Yin (Receptive & Intuitive)"

        val luckyNumbers = when (animalIndex) {
            0 -> "2, 3"
            1 -> "1, 4"
            2 -> "1, 3, 4"
            3 -> "3, 4, 6"
            4 -> "1, 6, 7"
            5 -> "2, 8, 9"
            6 -> "2, 3, 7"
            7 -> "2, 7"
            8 -> "4, 9"
            9 -> "5, 7, 8"
            10 -> "3, 4, 9"
            else -> "2, 5, 8"
        }

        val luckyColors = when (animalIndex) {
            0 -> "Blue, Gold, Green"
            1 -> "White, Yellow, Green"
            2 -> "Blue, Grey, Orange"
            3 -> "Red, Pink, Purple"
            4 -> "Gold, Silver, Hoary"
            5 -> "Black, Red, Yellow"
            6 -> "Yellow, Green"
            7 -> "Brown, Red, Purple"
            8 -> "White, Blue, Gold"
            9 -> "Gold, Brown, Yellow"
            10 -> "Red, Green, Purple"
            else -> "Yellow, Grey, Gold"
        }

        val bestMatches = when (animalIndex) {
            0 -> "Dragon, Monkey, Ox"
            1 -> "Rat, Snake, Rooster"
            2 -> "Horse, Dog, Dragon"
            3 -> "Goat, Dog, Pig"
            4 -> "Rat, Monkey, Rooster"
            5 -> "Dragon, Rooster, Ox"
            6 -> "Tiger, Goat, Dog"
            7 -> "Rabbit, Horse, Pig"
            8 -> "Rat, Dragon, Snake"
            9 -> "Ox, Dragon, Snake"
            10 -> "Tiger, Rabbit, Horse"
            else -> "Tiger, Rabbit, Goat"
        }

        return ChineseZodiacInfo(
            birthYear = year,
            animal = animalName,
            animalEmoji = emoji,
            element = elementName,
            elementSymbol = symbol,
            yinYang = yinYang,
            fullSign = "$yinYang $elementName $animalName $emoji",
            personalityTraits = traits,
            luckyNumbers = luckyNumbers,
            luckyColors = luckyColors,
            bestMatches = bestMatches,
            elementMeaning = elementDesc
        )
    }
}

