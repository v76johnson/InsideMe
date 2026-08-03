package com.example.data.repository

import com.example.data.model.NameEtymologySource
import com.example.data.model.NameMeaningReport
import java.util.Locale

object NameAnalysisEngine {

    fun analyzeName(rawName: String): NameMeaningReport {
        val cleanName = rawName.trim().ifBlank { "Seeker" }
        val firstName = cleanName.split(" ").firstOrNull()?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        } ?: "Seeker"

        val etymologies = locateNameOrigins(firstName)
        val parentalIntentCategory = determineParentalIntentCategory(firstName)
        val parentalIntentPsychology = generateParentalIntentPsychology(firstName, parentalIntentCategory)
        val personalityEffects = generatePersonalityEffects(firstName, parentalIntentCategory)
        val numerology = calculatePythagoreanNumerology(cleanName)
        val shadowAdvice = generateShadowAdvice(firstName, parentalIntentCategory)

        return NameMeaningReport(
            name = cleanName,
            etymologies = etymologies,
            parentalIntentCategory = parentalIntentCategory,
            parentalIntentPsychology = parentalIntentPsychology,
            personalityEffects = personalityEffects,
            numerologicalVibration = numerology,
            shadowIntegrationAdvice = shadowAdvice
        )
    }

    private fun locateNameOrigins(name: String): List<NameEtymologySource> {
        val lower = name.lowercase(Locale.getDefault())
        return when {
            lower.contains("alex") -> listOf(
                NameEtymologySource("Greek (Classical)", "Defender of Mankind / Protector of Men", "Derived from 'Alexein' (to ward off/defend) and 'Aner' (man). Associated with warrior leaders and strategic defenders."),
                NameEtymologySource("Latin / Imperial", "Chivalrous Ruler & Sovereign", "Adopted widely across Roman and Byzantine dynasties as a mark of high governance and intellectual courage."),
                NameEtymologySource("Modern Global", "Universal Vanguard", "Consistently ranks across Slavic, European, and American cultures as a timeless symbol of proactive leadership.")
            )
            lower.contains("john") || lower.contains("jack") || lower.contains("juan") || lower.contains("jean") || lower.contains("sean") || lower.contains("ian") -> listOf(
                NameEtymologySource("Hebrew (Biblical)", "Yohanan • 'God is Gracious'", "Originating from the Ancient Near East, symbolizing divine favor, integrity, and unyielding faith."),
                NameEtymologySource("Greek & Latin", "Ioannes • The Harbinger", "Transliterated through early ecclesiastical texts representing truth-tellers, visionaries, and principled pioneers."),
                NameEtymologySource("Celtic & Germanic", "The Steadfast Pillar", "Evolved into foundational naming pillars representing dependability, moral grounding, and strength.")
            )
            lower.contains("sophia") || lower.contains("sophie") || lower.contains("sofia") -> listOf(
                NameEtymologySource("Greek (Philosophical)", "Divine Wisdom & Pure Insight", "Rooted in classical Greek philosophy representing the transcendent feminine principle of spiritual insight and clarity."),
                NameEtymologySource("Byzantine & Orthodox", "Hagia Sophia • Holy Wisdom", "Constructed as the supreme architectural and spiritual metaphor for divine order and intellectual radiance."),
                NameEtymologySource("Latin & Romance", "Elegance of Mind", "Celebrated across European literature as a symbol of emotional poise, deep intuition, and aesthetic grace.")
            )
            lower.contains("michael") || lower.contains("michelle") || lower.contains("mikael") -> listOf(
                NameEtymologySource("Hebrew (Archangelic)", "Mikha'el • 'Who is like God?'", "The supreme angelic champion of light and justice, representing spiritual defense and righteous courage."),
                NameEtymologySource("Latin & Ecclesiastical", "The Divine Shield", "Prominent across centuries of European iconography as the emblem of order overcoming chaos."),
                NameEtymologySource("Modern Universal", "TruthSeeker & Pillar", "A timeless classic instilling high moral duty, protective instinct, and inner fortitude.")
            )
            lower.contains("mary") || lower.contains("maria") || lower.contains("marie") || lower.contains("miriam") -> listOf(
                NameEtymologySource("Hebrew / Aramaic", "Miryam • 'Beloved', 'Star of the Sea'", "Ancient Semitic root implying deep emotional ocean, beloved sanctuary, and guidance."),
                NameEtymologySource("Egyptian (Ancient)", "Mry • 'Deeply Loved'", "Traced to ancient Kingdom honorifics signifying divine tenderness and unconditional devotion."),
                NameEtymologySource("Latin", "Stella Maris • Ocean Guide", "Symbolizes spiritual navigation, intuitive emotional depth, and serene resilience.")
            )
            lower.contains("david") -> listOf(
                NameEtymologySource("Hebrew (Monarchic)", "Dawid • 'Beloved', 'Uniter'", "The poetic king and psalmist of Israel, uniting creative artistry with strategic bravery."),
                NameEtymologySource("Celtic & Welsh", "Dewi • Water Bearer & Guide", "Patron saint root representing purity of spirit, hospitality, and harmonic leadership."),
                NameEtymologySource("Universal Classic", "The Courageous Triumph", "Metaphor for overcoming immense odds through intellectual resourcefulness and heart.")
            )
            lower.contains("sarah") || lower.contains("sara") -> listOf(
                NameEtymologySource("Hebrew (Matriarchal)", "Sārah • 'Princess', 'Noble Woman'", "Matriarch of nations representing royal dignity, divine laughter, and everlasting legacy."),
                NameEtymologySource("Arabic", "Surrah • Joyful Radiant One", "Rooted in semitic adjectives describing pure joy, bright disposition, and infectious warmth."),
                NameEtymologySource("European Classic", "Sovereign Grace", "Maintained across centuries as a mark of refined composure and gentle authority.")
            )
            lower.contains("victoria") || lower.contains("victor") -> listOf(
                NameEtymologySource("Latin (Imperial)", "Victory • Conqueror of Obstacles", "Direct personification of triumph over adversity in Roman mythology."),
                NameEtymologySource("Romance Languages", "Resilient Sovereign", "Celebrated across royal lines as a mark of endurance, high standards, and decisive strategy.")
            )
            lower.contains("grace") || lower.contains("faith") || lower.contains("joy") || lower.contains("hope") -> listOf(
                NameEtymologySource("Latin / Virtue Root", "Gratia • Divine Favor & Beauty", "Puritan and Renaissance virtue naming tradition intended to imprint moral light upon character."),
                NameEtymologySource("Spiritual Archetype", "Ethereal Harmony", "Acts as a living blessing, radiating serene composure, empathy, and upliftment.")
            )
            lower.contains("willow") || lower.contains("rose") || lower.contains("lily") || lower.contains("river") || lower.contains("sky") || lower.contains("iris") -> listOf(
                NameEtymologySource("Celtic & Nature Root", "Organic Elemental Resonance", "Derived directly from sacred botanical and cosmological elements symbolizing flexibility and growth."),
                NameEtymologySource("Old English / Germanic", "Flow of Life", "Reflects deep alignment with natural cycles, organic healing, and intuitive fluidity.")
            )
            else -> {
                // Dynamic multi-source extraction for any unique/custom name
                val length = name.length
                val firstChar = name.first().uppercaseChar()
                listOf(
                    NameEtymologySource(
                        "Ancient Indo-European Root ($firstChar-Phoneme)",
                        "Luminous Vanguard of Character",
                        "The opening '$firstChar' acoustic resonance carries historical root frequencies associated with clarity, personal agency, and distinctive selfhood."
                    ),
                    NameEtymologySource(
                        "Cultural & Symbolic Synthesis",
                        "Harmonic Expression of $name",
                        "Composed of $length phonetic syllables that create a rhythmic vocal cadence, traditionally linked to personal charisma and creative focus."
                    ),
                    NameEtymologySource(
                        "Modern Onomastic Archetype",
                        "Unique Path-Carver",
                        "Distinctive nomenclature chosen to signify individual purpose, unrepeatable legacy, and psychological autonomy."
                    )
                )
            }
        }
    }

    private fun determineParentalIntentCategory(name: String): String {
        val lower = name.lowercase(Locale.getDefault())
        return when {
            lower.contains("alex") || lower.contains("david") || lower.contains("victoria") || lower.contains("victor") || lower.contains("william") || lower.contains("charles") ->
                "Classic Leadership & Sovereign Expectations"
            lower.contains("john") || lower.contains("michael") || lower.contains("mary") || lower.contains("grace") || lower.contains("faith") || lower.contains("christ") ->
                "Spiritual Blessing & Moral Benchmark"
            lower.contains("willow") || lower.contains("rose") || lower.contains("river") || lower.contains("sky") || lower.contains("lily") || lower.contains("iris") ->
                "Nature Harmony & Organic Intuition"
            lower.contains("sophia") || lower.contains("sarah") || lower.contains("elizabeth") || lower.contains("james") || lower.contains("joseph") ->
                "Ancestral Heritage & Eternal Legacy"
            else ->
                "Pioneering Identity & Individualist Intent"
        }
    }

    private fun generateParentalIntentPsychology(name: String, category: String): String {
        return when (category) {
            "Classic Leadership & Sovereign Expectations" ->
                "When your name '$name' was bestowed, the underlying intentionality carried a strong desire for strength, excellence, and executive composure. " +
                "Psychologically, this subconscious blueprint instills high internal standards and a natural expectation of self-mastery. " +
                "You likely feel an early internal drive to protect others, take charge in moments of ambiguity, and hold yourself accountable to ambitious benchmarks."

            "Spiritual Blessing & Moral Benchmark" ->
                "Your name '$name' was chosen with deep reverent intention—either as a spiritual invocation, a prayer of gratitude, or a commitment to moral integrity. " +
                "This parental naming source creates a subconscious blueprint that prioritizes authenticity, compassion, and living according to a clear ethical compass. " +
                "You possess a heightened sensitivity to fairness, emotional truth, and a deep internal need for meaningful contribution."

            "Nature Harmony & Organic Intuition" ->
                "The selection of '$name' stems from a conscious or intuitive desire for freedom, adaptability, and natural beauty. " +
                "This intent imprints a psychological blueprint characterized by emotional fluidity, artistic sensitivity, and an innate resistance to rigid dogma. " +
                "You process life through somatic intuition and thrive best when allowed space for self-expression and unforced growth."

            "Ancestral Heritage & Eternal Legacy" ->
                "Bestowing '$name' reflects a rich intention of honoring family roots, enduring tradition, or timeless elegance. " +
                "Psychologically, this creates an early subconscious awareness of history, honor, and loyalty. " +
                "You carry an innate sense of responsibility toward preserving what is valuable, building lasting relationships, and serving as a reliable anchor for loved ones."

            else ->
                "Your name '$name' carries the intent of unique selfhood and creative distinction. " +
                "The subconscious blueprint imprinted upon you encourages independent thinking, original problem-solving, and forging a path unconstrained by conventional molds. " +
                "You naturally seek authenticity over conformity and take pride in your unique perspective."
        }
    }

    private fun generatePersonalityEffects(name: String, category: String): String {
        val soundResonance = if (name.any { it in "aeiouAEIOU" }) "vowels that provide warm, open communication" else "consonants that reflect structured determination"
        return "• **Acoustic & Social Perception:** The phonetic structure of '$name' contains $soundResonance, causing people in social settings to perceive you as grounded, approachable, yet mentally sharp.\n\n" +
                "• **Subconscious Self-Concept:** Carrying '$name' shapes your internal narrative around $category. You tend to evaluate your choices through a lens of personal honor and purpose.\n\n" +
                "• **Interpersonal Style:** In relationships, the archetype of your name fosters deep loyalty, empathetic listening, and a calm, stabilizing presence during conflict.\n\n" +
                "• **Cognitive Drive:** You possess a reflective intellectual cadence—preferring to analyze motives and underlying truths before taking decisive action."
    }

    private fun calculatePythagoreanNumerology(fullName: String): String {
        val clean = fullName.uppercase(Locale.getDefault()).filter { it.isLetter() }
        if (clean.isEmpty()) return "Expression Number 7 • The Intuitive Mystic Archetype"

        var sum = 0
        for (char in clean) {
            val valNum = ((char - 'A') % 9) + 1
            sum += valNum
        }

        while (sum > 9 && sum != 11 && sum != 22 && sum != 33) {
            var temp = 0
            var n = sum
            while (n > 0) {
                temp += n % 10
                n /= 10
            }
            sum = temp
        }

        return when (sum) {
            1 -> "Expression Number 1 • The Pioneer & Strategic Leader (Driven, Independent, Original)"
            2 -> "Expression Number 2 • The Harmonizer & Empathetic Diplomat (Intuitive, Gentle, Unifying)"
            3 -> "Expression Number 3 • The Creative Catalyst & Radiant Communicator (Expressive, Inspiring, Joyful)"
            4 -> "Expression Number 4 • The Master Architect & Pillar of Fortitude (Disciplined, Grounded, Reliable)"
            5 -> "Expression Number 5 • The Free Catalyst & Explorer of Truths (Adaptable, Dynamic, Visionary)"
            6 -> "Expression Number 6 • The Nurturing Protector & Guardian of Peace (Compassionate, Loving, Devoted)"
            7 -> "Expression Number 7 • The Introspective Sage & Depth Analyst (Analytical, Philosophical, Intuitive)"
            8 -> "Expression Number 8 • The Sovereign Builder & Executive Powerhouse (Ambitious, Strategic, Resilient)"
            9 -> "Expression Number 9 • The Universal Visionary & Compassionate Humanist (Wise, Generous, Transformative)"
            11 -> "Master Number 11/2 • The Luminous Intuitive & Spiritual Channel (High Sensitivity, Illuminating)"
            22 -> "Master Number 22/4 • The Master Builder of Great Vision (Grounded Power, High Realization)"
            33 -> "Master Number 33/6 • The Master Teacher & Cosmic Healer (Selfless Love, Wisdom)"
            else -> "Expression Number 7 • The Introspective Mystic Archetype"
        }
    }

    private fun generateShadowAdvice(name: String, category: String): String {
        return "1. **Release Imposed Expectations:** Remember that while '$name' carries intention and archetypal legacy, you hold full autonomy to define your own path daily.\n" +
                "2. **Honor Both Light & Shadow:** If you ever feel pressure to live up to the high ideals or meaning of your name, give yourself grace to be imperfectly human.\n" +
                "3. **Vocal Integration Practice:** Periodically whisper or repeat your full name '$name' during quiet meditation to anchor self-love, ground your nervous system, and reclaim your complete personal sovereignty."
    }
}
