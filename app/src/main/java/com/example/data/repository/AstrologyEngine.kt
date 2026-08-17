package com.example.data.repository

import com.example.data.model.AstrologyProfile
import com.example.data.model.DailyHoroscope
import com.example.data.model.SynastryMatch
import com.example.data.model.ZodiacElement
import com.example.data.model.ZodiacSign
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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

    fun generateDailyHoroscope(sign: ZodiacSign, dateMillis: Long = System.currentTimeMillis()): DailyHoroscope {
        val cal = Calendar.getInstance().apply { timeInMillis = dateMillis }
        val dayOfYear = cal.get(Calendar.DAY_OF_YEAR)
        val year = cal.get(Calendar.YEAR)
        val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
        val dateString = dateFormat.format(cal.time)

        // Seeded pseudo-randomness based on date and sign ordinal for consistent daily results
        val seed = (dayOfYear * 31 + year * 7 + sign.ordinal * 13)
        val cycleIndex = Math.abs(seed % 5)

        val headlines = when (sign) {
            ZodiacSign.ARIES -> listOf(
                "Dynamic Momentum & Fearless Initiative",
                "Decisive Clarity: Charge Toward Your Goals",
                "Pioneering Energy Sparks Breakthroughs",
                "Channel Passion into Purposeful Action",
                "Unstoppable Drive & Creative Spark"
            )
            ZodiacSign.TAURUS -> listOf(
                "Grounded Abundance & Sensual Harmony",
                "Patience Yields Tangible Rewards",
                "Steadfast Focus Solidifies Your Foundations",
                "Embrace Inner Peace & Comfort",
                "Practical Wisdom & Financial Harmony"
            )
            ZodiacSign.GEMINI -> listOf(
                "Electric Curiosity & Sparkling Wit",
                "Brilliant Ideas Flow Seamlessly",
                "Meaningful Connections & Inspiring Dialogue",
                "Adaptive Intellect Opens New Portals",
                "Express Your Authentic Truth Today"
            )
            ZodiacSign.CANCER -> listOf(
                "Deep Intuition & Emotional Sanctuary",
                "Trust the Subtle Whispers of Your Soul",
                "Nurturing Your Heart Heals Old Patterns",
                "Cosmic Protection Around Your Inner Circle",
                "Creative Flow & Emotional Strength"
            )
            ZodiacSign.LEO -> listOf(
                "Radiant Charisma & Creative Power",
                "Step Confidently into the Spotlight",
                "Generosity of Spirit Attracts Miracles",
                "Your Warmth Inspires Everyone Around You",
                "Authentic Self-Expression Magnified"
            )
            ZodiacSign.VIRGO -> listOf(
                "Meticulous Clarity & Healing Grace",
                "Strategic Order Brings Deep Satisfaction",
                "Small Daily Tweaks Yield Monumental Shifts",
                "Discerning Insight Solves Complex Riddles",
                "Mind-Body Alignment & Restorative Flow"
            )
            ZodiacSign.LIBRA -> listOf(
                "Harmonious Equilibrium & Aesthetic Flow",
                "Graceful Diplomacy Resolves Lingering Tension",
                "Beauty Inspires Your Heart & Thoughts",
                "Equal Partnerships Flourish in Truth",
                "Inner Peace Radiates Outward"
            )
            ZodiacSign.SCORPIO -> listOf(
                "Transformative Power & Laser Intuition",
                "Unmasking Hidden Depths & Truths",
                "Shedding Old Layers to Rise Anew",
                "Magnetic Presence & Unshakeable Focus",
                "Profound Emotional Breakthroughs"
            )
            ZodiacSign.SAGITTARIUS -> listOf(
                "Expansive Horizons & Cosmic Optimism",
                "Adventure Beckons Beyond Your Comfort Zone",
                "Philosophical Clarity Illumination",
                "Enthusiastic Faith Attracts Synchronicity",
                "Freedom to Expand Your Mind"
            )
            ZodiacSign.CAPRICORN -> listOf(
                "Masterful Ambition & Sovereign Discipline",
                "Climbing Steady Toward Your Long-Term Summit",
                "Quiet Authority & Practical Excellence",
                "Building Enduring Legacy with Patience",
                "Grounding Your Highest Aspirations"
            )
            ZodiacSign.AQUARIUS -> listOf(
                "Visionary Innovation & Universal Insight",
                "Break Free from Conventional Boxes",
                "Humanitarian Sparks & Community Resonance",
                "Inventive Problem-Solving at its Peak",
                "Authentic Individuality Celebrated"
            )
            ZodiacSign.PISCES -> listOf(
                "Mystical Inspiration & Compassionate Waves",
                "Dreams Hold Potent Keys to Reality",
                "Artistic Muse Whispers directly to Your Heart",
                "Spiritual Flow & Healing Serenity",
                "Boundless Love & Intuitive Attunement"
            )
        }

        val overviews = when (sign) {
            ZodiacSign.ARIES -> listOf(
                "Mars aligns favorably today, supercharging your motivation and cutting through lingering mental fog. Take bold, direct action on projects you've placed on the back burner.",
                "Your instincts are exceptionally sharp. Trust your initial impulses rather than overthinking every turn. A courageous move will command respect and open fresh doors.",
                "High energy demands constructive direction. Tackle high-friction tasks before noon to ride the wave of momentum straight to victory.",
                "An exciting opportunity requires quick thinking. Your fiery determination gives you the decisive edge over sluggish competition.",
                "Embrace leadership today. When others hesitate, your confidence will serve as the guiding beacon."
            )
            ZodiacSign.TAURUS -> listOf(
                "Venus bathes your day in tranquil, grounding energy. Focus on quality over quantity in both your craft and personal conversations.",
                "Steady perseverance is your superpower today. While others rush and stumble, your deliberate pace guarantees impeccable craftsmanship and lasting security.",
                "A gentle financial or material blessing is within reach. Trust the investments you've cultivated over time; root yourself in gratitude.",
                "Take time to nourish all five senses. Good food, calming surroundings, and physical comfort replenish your internal reservoir of strength.",
                "Stay true to your core values today. Stability is found within, not in external turbulence."
            )
            ZodiacSign.GEMINI -> listOf(
                "Mercury amplifies your wit and communicative gifts. Conversations carry serendipitous sparks that could ignite a promising new chapter.",
                "Your mental agility is firing on all cylinders. Synthesis between disparate ideas comes naturally—write down every epiphany immediately.",
                "A social connection or unexpected message brings uplifting perspective. Keep your mind open to diverse viewpoints.",
                "Multitasking comes easily today, but directing your focus toward a single high-impact priority will yield remarkable breakthroughs.",
                "Playfulness and curiosity are your greatest allies. Approach challenges as stimulating puzzles rather than burdens."
            )
            ZodiacSign.CANCER -> listOf(
                "The Moon gently reflects upon your deep emotional intelligence. Prioritize creating a safe, peaceful environment where your spirit can breathe and restore.",
                "Your intuitive radar is operating at maximum sensitivity. Pay close attention to subtle vibes and body language; your instincts are spot-on.",
                "Nurture both yourself and those you hold dear. A heartfelt conversation will heal a subtle rift and deepen mutual loyalty.",
                "Creative waters run deep today. Expressing feelings through writing, art, or thoughtful gestures unlocks immense serenity.",
                "Release what no longer serves your emotional sanctuary. Protecting your inner peace is a sacred priority."
            )
            ZodiacSign.LEO -> listOf(
                "The Sun infuses your presence with magnetic warmth and optimism. Step boldly into the spotlight; your authentic expression inspires those around you.",
                "Creative projects receive a massive boost of vitality. Lead with generosity and celebrate the talents of your collaborators.",
                "Confidence is your natural state today. Use this energetic crest to pitch ideas, express romantic affection, or embark on a passionate quest.",
                "Your innate nobility shines brightly. When you lead from the heart, you naturally attract loyalty and enthusiastic support.",
                "Radiate joy without restraint. Your infectious spirit has the power to uplift the entire room."
            )
            ZodiacSign.VIRGO -> listOf(
                "Mercury grants you laser-like analytical precision. You will effortlessly spot details and optimizations that others overlook.",
                "Organizing your environment or streamlining a complex process brings immense inner satisfaction and mental clarity today.",
                "Remember that perfection is the enemy of completion. Celebrate your tangible progress and offer yourself compassionate grace.",
                "Health and wellness take center stage. Gentle stretches, mindful hydration, and wholesome nourishment restore your vitality.",
                "Your practical counsel is invaluable to someone seeking direction. Share your wisdom with gentle tact."
            )
            ZodiacSign.LIBRA -> listOf(
                "Venus wraps your day in harmonious balance and aesthetic charm. You have an exceptional talent for facilitating understanding between opposing views.",
                "Seek beauty in your surroundings and in your relationships. A peaceful environment elevates your mental acuity and emotional well-being.",
                "Decisions become easier when you weigh them against your long-term inner peace rather than short-term approval.",
                "Collaborative ventures are blessed with smooth synchronization today. Mutual respect brings effortless progress.",
                "Treat yourself with the same courtesy and consideration you so freely extend to others."
            )
            ZodiacSign.SCORPIO -> listOf(
                "Your inner radar is piercing and profoundly perceptive today. You see right through illusions to the fundamental truth of every situation.",
                "Channel your intense emotional focus into a meaningful breakthrough or transformative creative pursuit. Nothing is out of reach.",
                "A moment of vulnerability with someone you trust will forge an unbreakable bond. Release the armor where it's safe to do so.",
                "Old emotional weights are ready to be transmuted into wisdom. Step forward into your sovereign personal power.",
                "Trust your inner knowing. The universe is aligning puzzle pieces quietly behind the scenes."
            )
            ZodiacSign.SAGITTARIUS -> listOf(
                "Jupiter expands your vision and fills your sails with infectious enthusiasm. Dare to dream bigger and explore concepts beyond the everyday horizon.",
                "Your optimism is a magnetic force today. An adventurous mindset will turn ordinary routines into thrilling learning experiences.",
                "Seek the deeper meaning behind recent events. Philosophical reflection yields clarity and renews your sense of adventure.",
                "Share your jokes, stories, and visionary ideas. Your authentic joy is a potent catalyst for collective upliftment.",
                "Trust the journey. Every twist and detour is equipping you with valuable wisdom."
            )
            ZodiacSign.CAPRICORN -> listOf(
                "Saturn grounds your ambitious vision with relentless focus and methodical strategy. Brick by brick, your empire is taking shape.",
                "Your dedication to excellence does not go unnoticed. High-level discipline today will yield lasting security and respect.",
                "Balance hard work with conscious pacing. Sustainable progress requires honoring your physical and emotional reserves.",
                "Take pride in how far you've climbed. Ground yourself in quiet self-assurance and keep moving steadily forward.",
                "Practical solutions arise effortlessly when you trust your hard-won experience."
            )
            ZodiacSign.AQUARIUS -> listOf(
                "Uranus electrifies your mind with unconventional insights and visionary concepts. Don't be afraid to think differently—your uniqueness is your magic.",
                "Community connection and shared ideals bring deep inspiration. You are a catalyst for positive progressive change.",
                "Break free from outdated expectations. When you honor your authentic eccentricities, you magnetize your true tribe.",
                "A collaborative brainstorm sparks an innovative solution to an old impasse. Trust your progressive vision.",
                "Observe situations from a high vantage point to see the grand cosmic tapestry unfold."
            )
            ZodiacSign.PISCES -> listOf(
                "Neptune invites you into a realm of rich imagination and spiritual grace. Your dreams and creative musings carry potent messages today.",
                "Compassion flows naturally from your heart. Be sure to establish gentle energetic boundaries so you don't absorb outside stress.",
                "Immerse yourself in music, art, or quiet reflection near water. Soulful activities replenish your boundless creative spirit.",
                "Synchronicities abound today. Keep your eyes open for meaningful signs, numbers, and serendipitous encounters.",
                "Trust your heart's wisdom. It knows the path forward even when logic cannot yet explain it."
            )
        }

        val focusThemes = listOf(
            "Inner Alignment & Manifestation",
            "Courageous Action & Truth",
            "Harmonious Collaboration",
            "Transformative Healing",
            "Creative Expansion & Joy"
        )

        val loveVibes = listOf(
            "Sparks of deep mutual understanding; great day for honest romantic expression.",
            "Magnetic chemistry and gentle warmth; prioritize quality one-on-one time.",
            "Healing conversations dissolve past distance; trust is blossoming.",
            "Playful banter and spontaneous affection bring delightful joy.",
            "Unconditional acceptance creates a peaceful, sanctuary-like connection."
        )

        val careerVibes = listOf(
            "Strategic focus and high productivity; your proposals carry strong weight.",
            "Smooth teamwork and creative problem-solving overcome any obstacles.",
            "An unexpected breakthrough or recognition for your consistent hard work.",
            "Great time to refine strategies, organize plans, and set ambitious goals.",
            "Intuitive insights guide smart decisions and high-value opportunities."
        )

        val cosmicAdvices = listOf(
            "Breathe deeply before making major decisions; your core intuition already knows the answer.",
            "Honor your physical energy levels today—rest is an essential part of sustainable mastery.",
            "Speak your authentic truth with kindness; the universe responds generously to clarity.",
            "Celebrate how much you have grown over the past season. You are exactly where you need to be.",
            "Embrace the beauty of the present moment without rushing ahead into tomorrow."
        )

        val luckyColors = listOf(
            "Celestial Gold", "Nebula Teal", "Mystic Violet", "Cosmic Amber", "Deep Azure", "Ruby Fire", "Emerald Green", "Silver Moonlight"
        )

        val luckyNumber = ((seed * 7 + 3) % 99) + 1
        val energyRating = 78 + (Math.abs(seed) % 21) // 78% to 98%
        val allSigns = ZodiacSign.values()
        val compatibleSign = allSigns[(sign.ordinal + 4 + (dayOfYear % 3)) % allSigns.size]

        return DailyHoroscope(
            sign = sign,
            dateString = dateString,
            headline = headlines[cycleIndex % headlines.size],
            overview = overviews[cycleIndex % overviews.size],
            focusTheme = focusThemes[cycleIndex % focusThemes.size],
            energyRating = energyRating,
            loveVibe = loveVibes[cycleIndex % loveVibes.size],
            careerVibe = careerVibes[cycleIndex % careerVibes.size],
            cosmicAdvice = cosmicAdvices[cycleIndex % cosmicAdvices.size],
            luckyNumber = luckyNumber,
            luckyColor = luckyColors[Math.abs(seed) % luckyColors.size],
            compatibleSignToday = compatibleSign
        )
    }
}

