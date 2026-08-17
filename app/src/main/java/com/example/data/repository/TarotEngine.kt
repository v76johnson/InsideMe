package com.example.data.repository

import com.example.data.model.DrawnCard
import com.example.data.model.TarotArcana
import com.example.data.model.TarotCard
import com.example.data.model.TarotReadingResult
import com.example.data.model.TarotSpreadType
import com.example.data.model.TarotSuit
import com.example.data.model.ZodiacSign
import kotlin.random.Random

object TarotEngine {

    val allCards: List<TarotCard> = listOf(
        TarotCard(
            id = "major_0",
            name = "0. The Fool",
            arcana = TarotArcana.MAJOR,
            number = 0,
            emoji = "🌟",
            keywordsUpright = listOf("New Beginnings", "Innocence", "Spontaneity", "Free Spirit", "Leap of Faith"),
            keywordsReversed = listOf("Recklessness", "Hesitation", "Risk-taking", "Naivety"),
            uprightMeaning = "Embrace new beginnings with an open heart. A pristine chapter is unfolding that invites courage, curiosity, and boundless trust in the journey ahead.",
            reversedMeaning = "Beware of reckless impulsivity or staying paralyzed by fear of the unknown. Find grounding before taking your next bold leap.",
            astrologicalAssociation = "Uranus / Air • Cosmic Freedom",
            psychologicalArchetype = "The Divine Child / Innocent Explorer",
            reflectionQuestion = "Where in your life are you being asked to trust your intuition and take a courageous fresh start?",
            affirmativeMantra = "I step forward boldly into the unknown with wonder and trust."
        ),
        TarotCard(
            id = "major_1",
            name = "I. The Magician",
            arcana = TarotArcana.MAJOR,
            number = 1,
            emoji = "🪄",
            keywordsUpright = listOf("Manifestation", "Resourcefulness", "Power", "Inspired Action", "Skill"),
            keywordsReversed = listOf("Illusion", "Untapped Potential", "Manipulation", "Scattered Focus"),
            uprightMeaning = "You possess all tools, skills, and resources needed to manifest your intentions into tangible reality. Channel your focus with purpose.",
            reversedMeaning = "Your creative power is being stifled by self-doubt or misaligned intentions. Realign your actions with authentic truth.",
            astrologicalAssociation = "Mercury • Conscious Intellect & Will",
            psychologicalArchetype = "The Alchemist / Conscious Creator",
            reflectionQuestion = "What latent abilities or internal resources can you activate right now to solve your current challenge?",
            affirmativeMantra = "I have the power, clarity, and capability to shape my reality."
        ),
        TarotCard(
            id = "major_2",
            name = "II. The High Priestess",
            arcana = TarotArcana.MAJOR,
            number = 2,
            emoji = "🌙",
            keywordsUpright = listOf("Intuition", "Sacred Knowledge", "Divine Feminine", "Subconscious", "Inner Stillness"),
            keywordsReversed = listOf("Secrets", "Disconnected Intuition", "Surface Judgments", "Repressed Feelings"),
            uprightMeaning = "Trust the subtle whispers of your subconscious. Look beneath the veil of external noise to access your deepest intuitive wisdom.",
            reversedMeaning = "You may be ignoring your inner gut feeling or relying solely on external validation. Carve out quiet time for inner reflection.",
            astrologicalAssociation = "Moon / Cancer • Subconscious Depths",
            psychologicalArchetype = "The Seer / Intuitive Anima",
            reflectionQuestion = "What is your intuition quietly whispering that your analytical mind has been trying to dismiss?",
            affirmativeMantra = "I honor my inner knowing and listen to the stillness within."
        ),
        TarotCard(
            id = "major_3",
            name = "III. The Empress",
            arcana = TarotArcana.MAJOR,
            number = 3,
            emoji = "🌸",
            keywordsUpright = listOf("Abundance", "Nurturing", "Fertility", "Sensuality", "Creative Flowering"),
            keywordsReversed = listOf("Creative Block", "Depletion", "Over-giving", "Smothering"),
            uprightMeaning = "A period of lush creativity, warmth, and abundant growth. Nurture your projects, body, and relationships with compassionate care.",
            reversedMeaning = "You may be giving from an empty cup or neglecting your own self-nourishment. Reconnect with nature and sensory restoration.",
            astrologicalAssociation = "Venus / Taurus & Libra • Universal Harmony",
            psychologicalArchetype = "The Great Mother / Bountiful Nature",
            reflectionQuestion = "How can you bring more gentle self-compassion, pleasure, and artistic beauty into your daily routine?",
            affirmativeMantra = "I am surrounded by abundance, and I allow myself to receive nourishment."
        ),
        TarotCard(
            id = "major_4",
            name = "IV. The Emperor",
            arcana = TarotArcana.MAJOR,
            number = 4,
            emoji = "👑",
            keywordsUpright = listOf("Structure", "Stability", "Authority", "Discipline", "Protective Leadership"),
            keywordsReversed = listOf("Rigidity", "Control Issues", "Chaos", "Dominance"),
            uprightMeaning = "Establish clear boundaries, healthy routines, and strategic order. Your steady determination lays unshakeable foundations for success.",
            reversedMeaning = "Watch for excessive rigidity or letting authority conflicts drain your peace. True strength is grounded, not tyrannical.",
            astrologicalAssociation = "Aries / Mars • Strategic Willpower",
            psychologicalArchetype = "The Sovereign / Protective Father",
            reflectionQuestion = "Where in your life would clearer boundaries and consistent routines create freedom and calm?",
            affirmativeMantra = "I lead my life with calm authority, grounded discipline, and clear boundaries."
        ),
        TarotCard(
            id = "major_5",
            name = "V. The Hierophant",
            arcana = TarotArcana.MAJOR,
            number = 5,
            emoji = "📜",
            keywordsUpright = listOf("Spiritual Wisdom", "Tradition", "Mentorship", "Belief Systems", "Ethical Guidance"),
            keywordsReversed = listOf("Dogma", "Non-conformity", "Challenging Tradition", "Personal Truth"),
            uprightMeaning = "Seek guidance from trusted mentors, ethical frameworks, or proven traditions. Deepen your study and spiritual grounding.",
            reversedMeaning = "Question outdated dogmas or social expectations that no longer resonate with your authentic personal code.",
            astrologicalAssociation = "Taurus • Sacred Knowledge & Structure",
            psychologicalArchetype = "The Wise Teacher / Spiritual Guide",
            reflectionQuestion = "What core values and ethical principles are non-negotiable for you in this season?",
            affirmativeMantra = "I align my actions with higher wisdom and timeless truths."
        ),
        TarotCard(
            id = "major_6",
            name = "VI. The Lovers",
            arcana = TarotArcana.MAJOR,
            number = 6,
            emoji = "💖",
            keywordsUpright = listOf("Soul Connection", "Harmony", "Values Alignment", "Choice", "Unity"),
            keywordsReversed = listOf("Disharmony", "Misaligned Values", "Conflict", "Indecision"),
            uprightMeaning = "Deep heart-centered connection, mutual vulnerability, and harmonious synergy. A pivotal choice guided by your soul's highest values.",
            reversedMeaning = "Internal conflict or relational friction arising from incompatible values. Seek unity first within yourself.",
            astrologicalAssociation = "Gemini • Duality Harmonized into Union",
            psychologicalArchetype = "The Sacred Union / Coniunctio",
            reflectionQuestion = "Does this relationship or decision truly align with who you are becoming at soul depth?",
            affirmativeMantra = "I choose love, truth, and reciprocal harmony in all connections."
        ),
        TarotCard(
            id = "major_7",
            name = "VII. The Chariot",
            arcana = TarotArcana.MAJOR,
            number = 7,
            emoji = "🛡️",
            keywordsUpright = listOf("Determination", "Willpower", "Overcoming Obstacles", "Victory", "Momentum"),
            keywordsReversed = listOf("Lack of Direction", "Aggression", "Obstacles", "Loss of Control"),
            uprightMeaning = "Triumph through focused willpower and emotional mastery. Steer opposing forces in your life toward a unified, victorious goal.",
            reversedMeaning = "Feeling pulled in opposite directions or hitting a roadblock. Regain your inner composure before pushing forward.",
            astrologicalAssociation = "Cancer • Armored Resilience & Tenacity",
            psychologicalArchetype = "The Hero's Quest / Victorious Journey",
            reflectionQuestion = "What conflicting priorities in your life need to be brought into single-minded alignment?",
            affirmativeMantra = "My willpower is unwavering; I steer through challenges with focus and grace."
        ),
        TarotCard(
            id = "major_8",
            name = "VIII. Strength",
            arcana = TarotArcana.MAJOR,
            number = 8,
            emoji = "🦁",
            keywordsUpright = listOf("Inner Courage", "Gentle Mastery", "Patience", "Compassion", "Resilience"),
            keywordsReversed = listOf("Self-Doubt", "Raw Emotion", "Depletion", "Insecurity"),
            uprightMeaning = "True strength is quiet, patient, and compassionate. Tame internal anxieties and raw instincts with gentle self-acceptance rather than force.",
            reversedMeaning = "Temporary self-doubt or emotional exhaustion. Be tender with yourself; your inner flame is resilient and enduring.",
            astrologicalAssociation = "Leo / Sun • Radiant Heart Courage",
            psychologicalArchetype = "The Gentle Beast-Tamer / Heart Sovereign",
            reflectionQuestion = "Where can you replace harsh self-criticism with patient, gentle compassion?",
            affirmativeMantra = "I meet life's difficulties with quiet courage, tenderness, and grace."
        ),
        TarotCard(
            id = "major_9",
            name = "IX. The Hermit",
            arcana = TarotArcana.MAJOR,
            number = 9,
            emoji = "🏮",
            keywordsUpright = listOf("Introspection", "Inner Light", "Solitude", "Soul Searching", "Wisdom"),
            keywordsReversed = listOf("Isolation", "Loneliness", "Withdrawal", "Overthinking"),
            uprightMeaning = "Step back from external commotion into intentional solitude. The answers you seek are illuminated by the light of your own inner lantern.",
            reversedMeaning = "Healthy solitude may be turning into lonely isolation. Remember to rejoin the world and share your light with trusted allies.",
            astrologicalAssociation = "Virgo • Mindful Discrimination & Soul Solitude",
            psychologicalArchetype = "The Wise Old Sage / Lightbearer",
            reflectionQuestion = "What profound insight surfaces when you turn off all screens and sit in complete quiet?",
            affirmativeMantra = "In quiet reflection, my inner wisdom shines with perfect clarity."
        ),
        TarotCard(
            id = "major_10",
            name = "X. Wheel of Fortune",
            arcana = TarotArcana.MAJOR,
            number = 10,
            emoji = "🎡",
            keywordsUpright = listOf("Cycles", "Karma", "Destiny", "Turning Point", "Cosmic Timing"),
            keywordsReversed = listOf("Resisting Change", "Setbacks", "Bad Luck Illusion", "Clinging"),
            uprightMeaning = "The wheel turns favorably. A cosmic shift brings synchronicity, auspicious opportunities, and the start of a fresh, uplifting cycle.",
            reversedMeaning = "Accept that life moves in natural cycles. Resisting transition causes unnecessary friction—adapt with equanimity.",
            astrologicalAssociation = "Jupiter • Cosmic Abundance & Evolution",
            psychologicalArchetype = "The Cosmic Cycle / Synchronicity",
            reflectionQuestion = "How can you surrender resistance to current changes and ride the upward momentum?",
            affirmativeMantra = "I trust the cosmic timing of my life and welcome positive shifts."
        ),
        TarotCard(
            id = "major_11",
            name = "XI. Justice",
            arcana = TarotArcana.MAJOR,
            number = 11,
            emoji = "⚖️",
            keywordsUpright = listOf("Truth", "Fairness", "Cause & Effect", "Clarity", "Integrity"),
            keywordsReversed = listOf("Dishonesty", "Unfairness", "Avoidance of Accountability", "Bias"),
            uprightMeaning = "Truth cuts cleanly through illusion. Take full accountability for your choices and expect equitable, fair outcomes in all dealings.",
            reversedMeaning = "Feelings of injustice or reluctance to face hard facts. Look at the situation with objective honesty.",
            astrologicalAssociation = "Libra / Venus • Cosmic Equilibrium",
            psychologicalArchetype = "The Arbiter of Truth / Ethical Conscience",
            reflectionQuestion = "What situation in your life requires radical clarity, fairness, and objective balance?",
            affirmativeMantra = "I live with integrity, truth, and unwavering fairness."
        ),
        TarotCard(
            id = "major_12",
            name = "XII. The Hanged Man",
            arcana = TarotArcana.MAJOR,
            number = 12,
            emoji = "🌊",
            keywordsUpright = listOf("Surrender", "New Perspective", "Letting Go", "Pause", "Spiritual Release"),
            keywordsReversed = listOf("Stalling", "Needless Sacrifice", "Resistance", "Martyrdom"),
            uprightMeaning = "Surrender the need to control the timeline. By viewing your circumstances from an inverted perspective, breakthrough wisdom emerges.",
            reversedMeaning = "Procrastination disguised as patience, or holding onto martyrdom. Release what is stale and move forward.",
            astrologicalAssociation = "Neptune / Water • Mystical Surrender",
            psychologicalArchetype = "The Willing Mystic / Paradigm Shifter",
            reflectionQuestion = "What would happen if you completely released the need to force an immediate outcome?",
            affirmativeMantra = "I surrender control and embrace a higher, liberating perspective."
        ),
        TarotCard(
            id = "major_13",
            name = "XIII. Death",
            arcana = TarotArcana.MAJOR,
            number = 13,
            emoji = "🦋",
            keywordsUpright = listOf("Transformation", "Endings", "Rebirth", "Metamorphosis", "Clearing Old Karma"),
            keywordsReversed = listOf("Fear of Change", "Holding on to Past", "Decay", "Stagnation"),
            uprightMeaning = "A necessary and profound metamorphosis. An outdated identity, habit, or circumstance closes so that radiant new life can bloom.",
            reversedMeaning = "Clinging to dead leaves prevents spring growth. Gently open your hands and let the past compost into wisdom.",
            astrologicalAssociation = "Scorpio / Pluto • Profound Regeneration",
            psychologicalArchetype = "The Phoenix / Sacred Rebirth",
            reflectionQuestion = "What outdated role, fear, or attachment is ready to be laid to rest so you can evolve?",
            affirmativeMantra = "I gratefully release the past and welcome my sacred rebirth."
        ),
        TarotCard(
            id = "major_14",
            name = "XIV. Temperance",
            arcana = TarotArcana.MAJOR,
            number = 14,
            emoji = "🕊️",
            keywordsUpright = listOf("Alchemy", "Balance", "Moderation", "Patience", "Peace"),
            keywordsReversed = listOf("Imbalance", "Excess", "Impatience", "Extremes"),
            uprightMeaning = "The sacred art of divine alchemy: blending opposites into serene harmony. Practice moderation, patient integration, and emotional peace.",
            reversedMeaning = "Fluctuating between extremes or rushing a delicate process. Restore balance through measured, steady steps.",
            astrologicalAssociation = "Sagittarius • Philosophical Synthesis",
            psychologicalArchetype = "The Alchemical Angel / Harmonizer",
            reflectionQuestion = "How can you bring peace and moderate balance to areas where you have swung into extremes?",
            affirmativeMantra = "I am centered, patient, and peacefully aligned in all that I do."
        ),
        TarotCard(
            id = "major_15",
            name = "XV. The Devil",
            arcana = TarotArcana.MAJOR,
            number = 15,
            emoji = "⛓️",
            keywordsUpright = listOf("Shadow Self", "Attachments", "Unconscious Patterns", "Liberation", "Desire"),
            keywordsReversed = listOf("Releasing Chains", "Overcoming Addiction", "Freedom", "Shadow Integration"),
            uprightMeaning = "Illuminate unconscious attachments, limiting self-beliefs, or compulsive loops. Acknowledging your shadow is the doorway to profound freedom.",
            reversedMeaning = "You are breaking free from self-imposed bondage and reclaiming your sovereignty. Celebrate your awakening.",
            astrologicalAssociation = "Capricorn / Saturn • Material Illusions & Shadow",
            psychologicalArchetype = "The Jungian Shadow / Unconscious Bondage",
            reflectionQuestion = "What fear, attachment, or compulsive habit has held power over you only because it remained unexamined?",
            affirmativeMantra = "I shine light on my shadow and claim complete spiritual freedom."
        ),
        TarotCard(
            id = "major_16",
            name = "XVI. The Tower",
            arcana = TarotArcana.MAJOR,
            number = 16,
            emoji = "⚡",
            keywordsUpright = listOf("Sudden Awakening", "Shaking Foundations", "Revelation", "Breakthrough", "Liberation"),
            keywordsReversed = listOf("Averting Disaster", "Fear of Breakdown", "Delayed Inevitability", "Rebuilding"),
            uprightMeaning = "A lightning flash of sudden truth shatters false illusions and shaky structures. What falls was not built on authentic bedrock—rejoice in the liberation.",
            reversedMeaning = "You may be ignoring warning signs to prevent upheaval. Trust that clearing the ground makes way for genuine truth.",
            astrologicalAssociation = "Mars • Radical Breakthrough",
            psychologicalArchetype = "The Cataclysmic Awakening / Flash of Insight",
            reflectionQuestion = "What false belief or unsustainable structure has collapsed, creating room for authentic truth?",
            affirmativeMantra = "I embrace necessary breakthroughs that dismantle illusion and reveal truth."
        ),
        TarotCard(
            id = "major_17",
            name = "XVII. The Star",
            arcana = TarotArcana.MAJOR,
            number = 17,
            emoji = "✨",
            keywordsUpright = listOf("Hope", "Inspiration", "Healing", "Serenity", "Divine Blessing"),
            keywordsReversed = listOf("Despair", "Discouragement", "Lack of Faith", "Pessimism"),
            uprightMeaning = "A beacon of pure cosmic hope, renewal, and spiritual healing. After the storm, the skies clear to reveal your guiding North Star.",
            reversedMeaning = "Feelings of discouragement or spiritual disconnect. Reconnect with beauty, rest, and faith in your unfolding destiny.",
            astrologicalAssociation = "Aquarius / Uranus • Cosmic Vision & Inspiration",
            psychologicalArchetype = "The Guiding Light / Celestial Muse",
            reflectionQuestion = "What sacred hope or creative dream is rekindling in your heart right now?",
            affirmativeMantra = "My path is blessed with hope, serenity, and boundless inspiration."
        ),
        TarotCard(
            id = "major_18",
            name = "XVIII. The Moon",
            arcana = TarotArcana.MAJOR,
            number = 18,
            emoji = "🐺",
            keywordsUpright = listOf("Illusions", "Subconscious Depths", "Dreams", "Intuitive Mysteries", "Shadow Work"),
            keywordsReversed = listOf("Release of Fear", "Unveiling Secrets", "Clarity Returning", "Anxiety Lifting"),
            uprightMeaning = "Navigate through the misty realm of dreams, deep feelings, and psychic impressions. Do not let projection or fear cloud your vision—walk steadily.",
            reversedMeaning = "The fog is lifting. Misunderstandings, anxieties, and hidden motives are becoming crystal clear.",
            astrologicalAssociation = "Pisces / Neptune • Dream Realm & Illusions",
            psychologicalArchetype = "The Night Journey / Unconscious Dreamer",
            reflectionQuestion = "What irrational fears or projections can you gently acknowledge and release into the light?",
            affirmativeMantra = "I navigate deep waters with courage, trusting my inner clarity."
        ),
        TarotCard(
            id = "major_19",
            name = "XIX. The Sun",
            arcana = TarotArcana.MAJOR,
            number = 19,
            emoji = "☀️",
            keywordsUpright = listOf("Joy", "Success", "Vitality", "Radiance", "Warmth", "Clarity"),
            keywordsReversed = listOf("Temporary Cloudiness", "Dimmed Optimism", "Unrealistic Expectations"),
            uprightMeaning = "Unfiltered radiance, abundant vitality, and triumphant joy! Your authentic self is shining brightly, attracting warmth and success.",
            reversedMeaning = "A temporary cloud over your optimism. The sun is still shining above the mist—reconnect with playful gratitude.",
            astrologicalAssociation = "Sun / Leo • Core Vitality & Supreme Light",
            psychologicalArchetype = "The Radiant Self / Triumphant Light",
            reflectionQuestion = "What brings you pure, uncomplicated joy, and how can you celebrate it today?",
            affirmativeMantra = "I radiate joy, warmth, and vitality in everything I touch."
        ),
        TarotCard(
            id = "major_20",
            name = "XX. Judgement",
            arcana = TarotArcana.MAJOR,
            number = 20,
            emoji = "🎺",
            keywordsUpright = listOf("Awakening", "Higher Calling", "Rebirth", "Absolution", "Soul Purpose"),
            keywordsReversed = listOf("Self-Doubt", "Harsh Self-Criticism", "Ignoring the Call", "Regret"),
            uprightMeaning = "A clarion call to rise into your higher purpose. Forgive the past, awaken from slumber, and step into the elevated life you were born to live.",
            reversedMeaning = "Harsh self-judgment or hesitating to answer your inner calling. Grant yourself full forgiveness and rise.",
            astrologicalAssociation = "Pluto / Fire • Spiritual Resurrection",
            psychologicalArchetype = "The Great Awakening / Soul Reckoning",
            reflectionQuestion = "What higher calling or elevated version of yourself is summoning you forward right now?",
            affirmativeMantra = "I answer my soul's highest calling with forgiveness and courage."
        ),
        TarotCard(
            id = "major_21",
            name = "XXI. The World",
            arcana = TarotArcana.MAJOR,
            number = 21,
            emoji = "🌍",
            keywordsUpright = listOf("Completion", "Wholeness", "Integration", "Achievement", "Cosmic Unity"),
            keywordsReversed = listOf("Unfinished Business", "Delayed Closure", "Seeking External Validation"),
            uprightMeaning = "Triumphant completion of a major life cycle. You have integrated profound lessons and stand in sacred wholeness, ready for the next spiral.",
            reversedMeaning = "A lingering loose end before full closure can be attained. Tie up unfinished chapters with grace.",
            astrologicalAssociation = "Saturn / Earth • Master of Wholeness",
            psychologicalArchetype = "The Cosmic Whole / Integrated Self",
            reflectionQuestion = "What significant milestone, cycle, or lesson can you celebrate having completed with honor?",
            affirmativeMantra = "I am whole, fulfilled, and in perfect harmony with the universe."
        ),
        // Notable Minor Arcana Essentials
        TarotCard(
            id = "ace_cups",
            name = "Ace of Cups",
            arcana = TarotArcana.MINOR,
            suit = TarotSuit.CUPS,
            number = 1,
            emoji = "🏆",
            keywordsUpright = listOf("Emotional Renewal", "New Love", "Compassion", "Spiritual Grace", "Abundant Feelings"),
            keywordsReversed = listOf("Emotional Drain", "Blocked Love", "Repressed Feelings", "Self-Love Deficit"),
            uprightMeaning = "An overflowing chalice of love, empathy, and intuitive awakening. Your heart is opening to profound emotional blessings.",
            reversedMeaning = "Nourish your own heart first before trying to pour love into others.",
            astrologicalAssociation = "Water Signs (Cancer, Scorpio, Pisces)",
            psychologicalArchetype = "The Fountain of Love",
            reflectionQuestion = "How can you open your heart to deeper emotional vulnerability and joy?",
            affirmativeMantra = "My heart overflows with love, grace, and deep compassion."
        ),
        TarotCard(
            id = "ace_swords",
            name = "Ace of Swords",
            arcana = TarotArcana.MINOR,
            suit = TarotSuit.SWORDS,
            number = 1,
            emoji = "⚔️",
            keywordsUpright = listOf("Breakthrough Clarity", "Sharp Truth", "Mental Power", "New Ideas", "Justice"),
            keywordsReversed = listOf("Confusion", "Harsh Words", "Clouded Judgement", "Hostility"),
            uprightMeaning = "A sword of immaculate mental clarity cuts through fog, deceit, and confusion. Seize this breakthrough with courageous honesty.",
            reversedMeaning = "Avoid using intellect to attack or over-analyze into paralysis.",
            astrologicalAssociation = "Air Signs (Gemini, Libra, Aquarius)",
            psychologicalArchetype = "The Sword of Truth",
            reflectionQuestion = "What unequivocal truth needs to be spoken or recognized today?",
            affirmativeMantra = "I see with perfect mental clarity and speak my truth with integrity."
        ),
        TarotCard(
            id = "ace_wands",
            name = "Ace of Wands",
            arcana = TarotArcana.MINOR,
            suit = TarotSuit.WANDS,
            number = 1,
            emoji = "🪄",
            keywordsUpright = listOf("Spark of Passion", "Creative Inspiration", "Bold Initiative", "Vital Energy", "Drive"),
            keywordsReversed = listOf("Creative Block", "Lack of Energy", "Hesitation", "Burnout"),
            uprightMeaning = "A burst of electric creative inspiration and passionate drive. Ignite your boldest initiatives with enthusiastic action.",
            reversedMeaning = "Fan the embers of your passion gently if energy feels low; avoid forcing outcomes.",
            astrologicalAssociation = "Fire Signs (Aries, Leo, Sagittarius)",
            psychologicalArchetype = "The Divine Spark",
            reflectionQuestion = "What exciting idea or creative endeavor is electrifying your spirit right now?",
            affirmativeMantra = "I act upon my creative passions with bold enthusiasm and power."
        ),
        TarotCard(
            id = "ace_pentacles",
            name = "Ace of Pentacles",
            arcana = TarotArcana.MINOR,
            suit = TarotSuit.PENTACLES,
            number = 1,
            emoji = "🪙",
            keywordsUpright = listOf("New Financial Opportunity", "Tangible Abundance", "Grounded Health", "Prosperity", "Manifestation"),
            keywordsReversed = listOf("Missed Opportunity", "Financial Anxiety", "Instability", "Overspending"),
            uprightMeaning = "A golden seed of material prosperity, physical vitality, and tangible success is placed in your hands. Plant and tend it well.",
            reversedMeaning = "Build steady security and audit financial habits rather than seeking instant gratification.",
            astrologicalAssociation = "Earth Signs (Taurus, Virgo, Capricorn)",
            psychologicalArchetype = "The Golden Seed",
            reflectionQuestion = "What practical, grounded action can you take today to build lasting security and wellbeing?",
            affirmativeMantra = "I plant seeds of health, abundance, and prosperity that flourish steadily."
        )
    )

    fun drawSpread(
        spreadType: TarotSpreadType,
        sunSign: ZodiacSign? = null,
        userName: String = "Seeker"
    ): TarotReadingResult {
        val count = spreadType.cardCount
        val shuffledDeck = allCards.shuffled()
        val pickedCards = shuffledDeck.take(count)

        val drawnList = pickedCards.mapIndexed { index, card ->
            val positionPair = spreadType.positions.getOrElse(index) { "Position ${index + 1}" to "Energetic focus" }
            val isReversed = Random.nextFloat() < 0.25f // 25% chance of nuanced reversed perspective
            DrawnCard(
                card = card,
                isReversed = isReversed,
                positionTitle = positionPair.first,
                positionDescription = positionPair.second
            )
        }

        val primaryCard = drawnList.first()
        val astroSignStr = sunSign?.displayName ?: "Cosmic Seeker"

        val synthesis = buildString {
            append("✨ **Tarot Synthesis for $userName ($astroSignStr Energy):**\n\n")
            when (spreadType) {
                TarotSpreadType.DAILY_ONE_CARD -> {
                    val c = primaryCard.card
                    val orient = if (primaryCard.isReversed) "Reversed" else "Upright"
                    append("Your daily focal archetype is **${c.name}** ($orient). ")
                    append("Today, the universe invites you to embody *${if (primaryCard.isReversed) c.keywordsReversed.joinToString(", ") else c.keywordsUpright.joinToString(", ")}*. ")
                    append(if (primaryCard.isReversed) c.reversedMeaning else c.uprightMeaning)
                    append("\n\n💡 **Psychological Inquiry:** ${c.reflectionQuestion}")
                }
                TarotSpreadType.PAST_PRESENT_FUTURE -> {
                    append("Your journey reveals an arc of conscious evolution:\n")
                    drawnList.forEach { dc ->
                        val orient = if (dc.isReversed) " (Reversed)" else ""
                        append("• **${dc.positionTitle} (${dc.card.name}$orient):** ")
                        append(if (dc.isReversed) dc.card.reversedMeaning else dc.card.uprightMeaning)
                        append("\n")
                    }
                    append("\n🔮 **Synthesis:** Integrating the wisdom of **${drawnList[0].card.name}** in your foundation fuels your current alignment with **${drawnList[1].card.name}**, opening a triumphant doorway toward **${drawnList[2].card.name}**.")
                }
                TarotSpreadType.MIND_BODY_SPIRIT -> {
                    append("Holistic inner triad alignment:\n")
                    drawnList.forEach { dc ->
                        val orient = if (dc.isReversed) " (Reversed)" else ""
                        append("• **${dc.positionTitle} (${dc.card.name}$orient):** ")
                        append(if (dc.isReversed) dc.card.reversedMeaning else dc.card.uprightMeaning)
                        append("\n")
                    }
                    append("\n🌟 **Harmonization:** Ground your somatic body with mental clarity and trust your spiritual intuition to achieve complete inner equilibrium.")
                }
                TarotSpreadType.LOVE_RELATIONSHIP -> {
                    append("Relational chemistry & empathy reflection:\n")
                    drawnList.forEach { dc ->
                        val orient = if (dc.isReversed) " (Reversed)" else ""
                        append("• **${dc.positionTitle} (${dc.card.name}$orient):** ")
                        append(if (dc.isReversed) dc.card.reversedMeaning else dc.card.uprightMeaning)
                        append("\n")
                    }
                    append("\n💖 **Heart Wisdom:** Honest communication, mutual respect, and reciprocal vulnerability illuminate your relational horizon.")
                }
                TarotSpreadType.DECISION_CROSSROADS -> {
                    append("Navigating your crossroads with clarity:\n")
                    drawnList.forEach { dc ->
                        val orient = if (dc.isReversed) " (Reversed)" else ""
                        append("• **${dc.positionTitle} (${dc.card.name}$orient):** ")
                        append(if (dc.isReversed) dc.card.reversedMeaning else dc.card.uprightMeaning)
                        append("\n")
                    }
                    append("\n🧭 **Wise Counsel:** Trust the clear guidance of **${drawnList[2].card.name}** to overcome hidden resistance and take empowered action.")
                }
            }
        }

        val astroAlignment = "Aligned with ${sunSign?.displayName ?: "Cosmic Placements"} • ${primaryCard.card.astrologicalAssociation}"

        return TarotReadingResult(
            spreadType = spreadType,
            drawnCards = drawnList,
            synthesisSummary = synthesis,
            astrologicalAlignment = astroAlignment
        )
    }
}
