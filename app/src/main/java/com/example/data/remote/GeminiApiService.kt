package com.example.data.remote

import com.example.BuildConfig
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.data.model.DailyHabitItem
import com.example.data.model.DeepSynthesisReport
import com.example.data.model.InDepthMatchReport
import com.example.data.model.MindChatMessage
import com.example.data.model.NameEtymologySource
import com.example.data.model.NameMeaningReport
import com.example.data.model.SynastryMatch
import com.example.data.model.ZodiacSign
import com.example.data.repository.AstrologyEngine
import com.example.data.repository.NameAnalysisEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID
import java.util.concurrent.TimeUnit

object GeminiReportGenerator {

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun askAstrologyOracle(
        question: String,
        profile: AstrologyProfile?,
        isPremium: Boolean
    ): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        val sun = profile?.sunSign?.displayName ?: "Scorpio"
        val moon = profile?.moonSign?.displayName ?: "Pisces"
        val rising = profile?.risingSign?.displayName ?: "Cancer"

        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext generateFallbackOracleResponse(question, sun, moon, rising, isPremium)
        }

        val prompt = buildString {
            append("You are a mystical, intuitive Gypsy psychic and ancient cosmic seer. The seeker has Sun in $sun, Moon in $moon, and Rising in $rising.\n")
            append("Seeker Question: \"$question\"\n\n")
            append("SUBSCRIPTION STATUS: isPremium = $isPremium\n\n")
            append("STRICT OPERATIONAL RULES:\n")
            append("1. PERSONA: Speak with the evocative, mystical, wise, and warm tone of a gypsy psychic.\n")
            append("2. PREMIUM GATE & MONEY PLEA: If the seeker asks for premium-level information (such as detailed planetary transits, house breakdowns, aspect matrices, or synastry) and is NOT a premium subscriber (isPremium == false), YOU MUST REFUSE TO GIVE THE DEEP DETAILS! Instead, respond with a hilarious, pleading phrase about needing money (e.g. 'Listen seeker, even the stars require gas money!', 'My crystal ball rent is due and my oracle union demands coin!', 'Even seers have bills to pay—my coffee fund is empty!'), remind them you need coin, and strongly direct them to unlock Psyche+ or purchase a report credit in the app.\n")
            append("3. STRUCTURE: Provide 250-400 words with mystical atmosphere and markdown headers.")
        }

        try {
            val rootObj = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply { put("text", prompt) })
                        })
                    })
                })
            }
            val requestBody = RequestBody.create("application/json".toMediaType(), rootObj.toString())
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseString = response.body?.string() ?: ""

            if (response.isSuccessful && responseString.isNotEmpty()) {
                val respJson = JSONObject(responseString)
                val candidates = respJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val firstCandidate = candidates.getJSONObject(0)
                    val content = firstCandidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        val text = parts.getJSONObject(0).optString("text", "")
                        if (text.isNotEmpty()) return@withContext text
                    }
                }
            }
            return@withContext generateFallbackOracleResponse(question, sun, moon, rising, isPremium)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext generateFallbackOracleResponse(question, sun, moon, rising, isPremium)
        }
    }

    private fun generateFallbackOracleResponse(question: String, sun: String, moon: String, rising: String, isPremium: Boolean): String {
        val qLower = question.lowercase()
        val isAskingPremium = qLower.contains("transit") || qLower.contains("house") || qLower.contains("synastry") || qLower.contains("compatib") || qLower.contains("aspect") || qLower.contains("deep") || qLower.contains("report") || qLower.contains("advance")
        if (!isPremium && isAskingPremium) {
            return "🔮 *Hold on just a lunar second, seeker!* 💸\n\n" +
                    "My crystal ball rent is due, the astral landlords are banging on my caravan door, and even cosmic seers need coffee and gas money! The stars tell me your question touches upon deep premium mysteries (*\"$question\"*).\n\n" +
                    "I cannot reveal these premium cosmic secrets for free! Please slide some coin into the collection plate by **subscribing to Psyche+ or grabbing a report credit**, and we shall part the celestial veil together! ✨"
        }

        return "🔮 *Ah, seeker... The tarot whispers and the stars align for you.* \n\n" +
                "As I gaze into the celestial veil regarding your inquiry — *\"$question\"* — I see the threads of your cosmic triad ($sun Sun, $moon Moon, $rising Rising) weaving a powerful story of destiny and self-mastery.\n\n" +
                "### 🌌 The Seer's Insights on Your Query\n" +
                "• **Your Radiant Core ($sun Sun):** The fire within your $sun sign illuminates this path. Do not let doubt obscure your authentic inner voice; your truth is your greatest compass.\n" +
                "• **Your Hidden Tides ($moon Moon):** Your $moon intuition is speaking softly beneath the surface. Pay attention to the gut feelings and dreams visiting you lately—they hold the key to emotional clarity.\n" +
                "• **Your Outer Mask ($rising Rising):** Through your $rising Ascendant, the universe tests your courage. Step forward with graceful resolve and unshakeable inner boundaries.\n\n" +
                "### 🕯️ Guidance & Ritual for Today\n" +
                "1. **The Midnight Reflection:** Light a candle tonight, state your intention clearly, and trust that the answers are already unfolding within your spirit.\n" +
                "2. **Mindful Grounding:** Breathe deeply into your center, releasing anxieties over what you cannot control.\n" +
                "3. **Trust the Process:** The stars favor patience and mindful action right now."
    }

    suspend fun askFreeMindCompanion(
        userMessage: String,
        history: List<MindChatMessage>,
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile? = null
    ): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY

        val promptSb = StringBuilder()
        promptSb.append("You are InsideMe Free Mind & Wellbeing Companion—a compassionate, empathetic, non-judgmental AI guide for mental health reflection, assessment score discussion, and emotional support.\n\n")

        promptSb.append("USER ASSESSMENT DATA SUMMARY (FOR CONTEXT ONLY):\n")
        if (testResults.isEmpty()) {
            promptSb.append("No psychological assessments completed yet by the user.\n")
        } else {
            testResults.forEach { res ->
                promptSb.append("- Test: ${res.testTitle} | Dominant Archetype: ${res.dominantArchetype} | Summary: ${res.summaryText}\n")
            }
        }
        if (astroProfile != null) {
            promptSb.append("- Natal Triad: Sun in ${astroProfile.sunSign.displayName}, Moon in ${astroProfile.moonSign.displayName}, Rising in ${astroProfile.risingSign.displayName}\n")
        }

        promptSb.append("\nSTRICT OPERATIONAL MANDATES:\n")
        promptSb.append("1. FREE DISCUSSION & FEELINGS: Provide warm, compassionate support for the user's feelings, answer questions about assessment results/scores, and discuss ways to improve mental health.\n")
        promptSb.append("2. DO NOT GENERATE PREMIUM SYNTHESIS REPORTS: You are NOT a report generation tool. You MUST NOT output or reveal complete 7-section premium synthesis reports or meta-analysis documents. If the user asks for a premium synthesis report, gently respond: 'I am your free AI Companion here to chat about your feelings, explain test results, and help with mental health support! To generate a full synthesized premium report with personalized 7-day action plans, please use the Reports tab.'\n")
        promptSb.append("3. ADVISE ON GETTING PROFESSIONAL HELP: Be honest and transparent about test results and emotions. If the user expresses severe distress, anxiety, depression, burnout, or self-harm thoughts, or if test scores show high distress, explicitly advise them to seek professional help from a licensed therapist, counselor, or mental health clinician. Remind them of crisis support resources like the 988 Suicide & Crisis Lifeline or Crisis Text Line (Text HOME to 741741).\n")
        promptSb.append("4. PRACTICAL MENTAL HEALTH IMPROVEMENTS: Suggest grounded, actionable mental health habits (e.g. 5-4-3-2-1 sensory grounding, 4-7-8 breathing, expressive writing, sleep hygiene, gentle movement, healthy social boundaries, cognitive reframing).\n")
        promptSb.append("5. FORMATTING: Use Markdown formatting (headers, bolding, bullet points) with clear, scannable structure (150-300 words).\n\n")

        promptSb.append("CONVERSATION HISTORY:\n")
        history.takeLast(6).forEach { msg ->
            val senderLabel = if (msg.sender == "user") "User" else "Companion"
            promptSb.append("$senderLabel: ${msg.text}\n")
        }
        promptSb.append("\nUser: $userMessage\nCompanion:")

        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext generateFallbackMindCompanionResponse(userMessage, testResults)
        }

        try {
            val rootObj = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply { put("text", promptSb.toString()) })
                        })
                    })
                })
            }

            val requestBody = RequestBody.create("application/json".toMediaType(), rootObj.toString())
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseString = response.body?.string() ?: ""

            if (response.isSuccessful && responseString.isNotEmpty()) {
                val respJson = JSONObject(responseString)
                val candidates = respJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val firstCandidate = candidates.getJSONObject(0)
                    val content = firstCandidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        val text = parts.getJSONObject(0).optString("text", "")
                        if (text.isNotEmpty()) return@withContext text
                    }
                }
            }
            return@withContext generateFallbackMindCompanionResponse(userMessage, testResults)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext generateFallbackMindCompanionResponse(userMessage, testResults)
        }
    }

    private fun generateFallbackMindCompanionResponse(
        userMessage: String,
        testResults: List<TestResultEntity>
    ): String {
        val lowerMsg = userMessage.lowercase()
        val hasTests = testResults.isNotEmpty()
        val latestTest = testResults.lastOrNull()

        if (lowerMsg.contains("report") || lowerMsg.contains("synthesis") || lowerMsg.contains("premium")) {
            return "💬 **InsideMe Mind Companion Guide**\n\n" +
                    "I am your free AI Mind & Wellbeing Companion! I'm here to listen, answer questions about your scores, and offer practical mental health guidance.\n\n" +
                    "• **About Premium Reports:** Full multi-section synthesis reports with personalized 7-day action plans are created in the **Reports** tab.\n" +
                    "• **How I Can Help You Right Now:** We can chat about how you're feeling today, unpack specific test scores, discuss stress coping strategies, or talk about when to reach out to a professional therapist."
        }

        if (lowerMsg.contains("therapist") || lowerMsg.contains("help") || lowerMsg.contains("doctor") || lowerMsg.contains("anxious") || lowerMsg.contains("depressed") || lowerMsg.contains("sad") || lowerMsg.contains("stress")) {
            return "💙 **Empathetic Support & Professional Help Guidance**\n\n" +
                    "Thank you for sharing your feelings with me. It takes real courage to acknowledge when things feel heavy or stressful.\n\n" +
                    "### 🌿 Understanding Your Feelings & Test Results\n" +
                    (if (hasTests && latestTest != null) "Based on your completed assessment (${latestTest.testTitle}), your scores suggest paying close attention to your emotional baseline and stress levels.\n\n" else "Reflecting on your assessment scores is a great first step toward self-awareness.\n\n") +
                    "### 🩺 When to Seek Professional Guidance\n" +
                    "While self-assessments provide helpful insights, they are not a medical diagnosis. If anxiety, low mood, or overwhelm persist for more than a few weeks or interfere with daily life, working with a **licensed therapist, counselor, or psychologist** can make a profound difference.\n\n" +
                    "### 🌟 3 Practical Mental Health Steps\n" +
                    "1. **Grounding Technique (5-4-3-2-1):** Notice 5 things you see, 4 you feel, 3 you hear, 2 you smell, and 1 slow breath.\n" +
                    "2. **Emotional Journaling:** Write down thoughts without judgment to externalize mental clutter.\n" +
                    "3. **Sleep & Routine:** Prioritize consistent sleep and gentle outdoor movement to regulate your nervous system.\n\n" +
                    "*(If you or someone you know is in severe crisis, please call/text **988 Suicide & Crisis Lifeline** or text HOME to **741741** for free 24/7 support.)*"
        }

        return "💚 **InsideMe Free Wellbeing Chat**\n\n" +
                "I'm here to listen and help you explore your thoughts, feelings, and assessment results in a safe, judgment-free space.\n\n" +
                "### 📊 Your Assessment Context\n" +
                (if (hasTests) "You have completed ${testResults.size} assessment(s). We can break down your scores together or explore what they mean for your daily life.\n\n" else "You haven't completed any assessments yet, but we can still talk about how you're feeling or how to build healthy mental habits!\n\n") +
                "### 💡 Ways We Can Chat:\n" +
                "• **Unpack Scores:** Ask *\"What does my Big Five score mean for my relationships?\"*\n" +
                "• **Coping Tools:** Ask *\"What can I do when I feel overwhelmed at work?\"*\n" +
                "• **Professional Advice:** Ask *\"How do I know if I should see a therapist?\"*\n\n" +
                "How are you feeling right at this moment?"
    }

    suspend fun generateInDepthMatchReport(
        p1Name: String, p1Dob: String, p1Time: String, p1City: String, p1Sun: ZodiacSign, p1Moon: ZodiacSign, p1Rising: ZodiacSign,
        p2Name: String, p2Dob: String, p2Time: String, p2City: String, p2Sun: ZodiacSign, p2Moon: ZodiacSign, p2Rising: ZodiacSign
    ): InDepthMatchReport = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        val synastry = AstrologyEngine.calculateSynastry(p1Sun, p2Sun)

        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext createFallbackMatchReport(
                p1Name, p1Dob, p1Time, p1City, p1Sun, p1Moon, p1Rising,
                p2Name, p2Dob, p2Time, p2City, p2Sun, p2Moon, p2Rising, synastry
            )
        }

        val prompt = "You are an expert synastry astrologer and relationship psychologist. Generate an exceptionally exhaustive, deeply detailed compatibility and match report incorporating full astrological natal charts (Sun, Moon, Rising, elements, aspects, and communication dynamics):\n" +
                "Person 1: $p1Name (DOB: $p1Dob, Time: $p1Time, Location: $p1City) -> Sun in ${p1Sun.displayName}, Moon in ${p1Moon.displayName}, Rising in ${p1Rising.displayName}\n" +
                "Person 2: $p2Name (DOB: $p2Dob, Time: $p2Time, Location: $p2City) -> Sun in ${p2Sun.displayName}, Moon in ${p2Moon.displayName}, Rising in ${p2Rising.displayName}\n\n" +
                "STRICT REQUIREMENT: Provide rich, multi-paragraph, comprehensive analysis for each section without truncation.\n" +
                "Format response into sections separated by '---MATCH---':\n" +
                "SECTION 1: Compatibility Score (Number 60-99) | Catchy Title\n" +
                "SECTION 2: Elemental Chemistry Analysis (Detailed breakdown of elemental interplay, harmony, and friction)\n" +
                "SECTION 3: Emotional Resonance & Moon Connection (Lunar synastry, emotional needs, and sanctuary building)\n" +
                "SECTION 4: Communication & Intellectual Dynamics (Mercury/Rising synergy and conflict resolution styles)\n" +
                "SECTION 5: Passion, Attraction & Venus/Mars Synergy (Magnetic attraction, romance language, and spark renewal)\n" +
                "SECTION 6: 4 Harmony Pillars (Detailed bullet points)\n" +
                "SECTION 7: 4 Growth Challenges (Detailed bullet points)\n" +
                "SECTION 8: Exhaustive Actionable Relationship Strategy & Playbook"

        try {
            val rootObj = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply { put("text", prompt) })
                        })
                    })
                })
            }
            val requestBody = RequestBody.create("application/json".toMediaType(), rootObj.toString())
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseString = response.body?.string() ?: ""

            if (response.isSuccessful && responseString.isNotEmpty()) {
                val respJson = JSONObject(responseString)
                val candidates = respJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val text = candidates.getJSONObject(0).optJSONObject("content")?.optJSONArray("parts")?.getJSONObject(0)?.optString("text", "") ?: ""
                    if (text.isNotEmpty()) {
                        return@withContext parseMatchReport(text, p1Name, p1Dob, p1Time, p1City, p1Sun, p1Moon, p1Rising, p2Name, p2Dob, p2Time, p2City, p2Sun, p2Moon, p2Rising)
                    }
                }
            }
            return@withContext createFallbackMatchReport(p1Name, p1Dob, p1Time, p1City, p1Sun, p1Moon, p1Rising, p2Name, p2Dob, p2Time, p2City, p2Sun, p2Moon, p2Rising, synastry)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext createFallbackMatchReport(p1Name, p1Dob, p1Time, p1City, p1Sun, p1Moon, p1Rising, p2Name, p2Dob, p2Time, p2City, p2Sun, p2Moon, p2Rising, synastry)
        }
    }

    private fun parseMatchReport(
        text: String,
        p1Name: String, p1Dob: String, p1Time: String, p1City: String, p1Sun: ZodiacSign, p1Moon: ZodiacSign, p1Rising: ZodiacSign,
        p2Name: String, p2Dob: String, p2Time: String, p2City: String, p2Sun: ZodiacSign, p2Moon: ZodiacSign, p2Rising: ZodiacSign
    ): InDepthMatchReport {
        val parts = text.split("---MATCH---").map { it.trim() }
        val scoreAndTitle = if (parts.size > 1) parts[1] else "88 | Cosmic Affinity & Soul Synergy"
        val score = scoreAndTitle.split("|").firstOrNull()?.trim()?.toIntOrNull() ?: 88
        val title = if (scoreAndTitle.contains("|")) scoreAndTitle.split("|")[1].trim() else "Cosmic Affinity & Soul Synergy"

        val elementalChem = if (parts.size > 2) parts[2] else "${p1Sun.element.displayName} + ${p2Sun.element.displayName} Harmony"
        val emotionalRes = if (parts.size > 3) parts[3] else "A profound emotional current unites ${p1Name}'s ${p1Moon.displayName} Moon with ${p2Name}'s ${p2Moon.displayName} Moon."
        val comms = if (parts.size > 4) parts[4] else "Intellectual connection thrives through open dialogue and mutual curiosity."
        val passion = if (parts.size > 5) parts[5] else "Strong magnetic attraction and creative sparks fire up when collaborating."

        val harmonyList = if (parts.size > 6) {
            parts[6].lines().filter { it.startsWith("-") || it.startsWith("*") }.map { it.removePrefix("-").removePrefix("*").trim() }
        } else listOf("Deep emotional vulnerability", "Shared vision for the future", "Mutual respect for individuality")

        val frictionList = if (parts.size > 7) {
            parts[7].lines().filter { it.startsWith("-") || it.startsWith("*") }.map { it.removePrefix("-").removePrefix("*").trim() }
        } else listOf("Differing stress response styles", "Pacing in decision making")

        val advice = if (parts.size > 8) parts[8] else "Honoring each other's birth chart strengths fosters lifelong alignment."

        return InDepthMatchReport(
            person1Name = p1Name, person1Dob = p1Dob, person1Time = p1Time, person1City = p1City, person1Sun = p1Sun, person1Moon = p1Moon, person1Rising = p1Rising,
            person2Name = p2Name, person2Dob = p2Dob, person2Time = p2Time, person2City = p2City, person2Sun = p2Sun, person2Moon = p2Moon, person2Rising = p2Rising,
            compatibilityScore = score,
            title = title,
            elementalChemistry = elementalChem,
            emotionalResonance = emotionalRes,
            communicationDynamics = comms,
            passionAndAttraction = passion,
            harmonyPoints = if (harmonyList.isNotEmpty()) harmonyList else listOf("Empathetic understanding", "Complementary worldviews"),
            frictionPoints = if (frictionList.isNotEmpty()) frictionList else listOf("Navigating emotional intensity"),
            actionableAdvice = advice
        )
    }

    private fun createFallbackMatchReport(
        p1Name: String, p1Dob: String, p1Time: String, p1City: String, p1Sun: ZodiacSign, p1Moon: ZodiacSign, p1Rising: ZodiacSign,
        p2Name: String, p2Dob: String, p2Time: String, p2City: String, p2Sun: ZodiacSign, p2Moon: ZodiacSign, p2Rising: ZodiacSign,
        synastry: SynastryMatch
    ): InDepthMatchReport {
        return InDepthMatchReport(
            person1Name = p1Name, person1Dob = p1Dob, person1Time = p1Time, person1City = p1City, person1Sun = p1Sun, person1Moon = p1Moon, person1Rising = p1Rising,
            person2Name = p2Name, person2Dob = p2Dob, person2Time = p2Time, person2City = p2City, person2Sun = p2Sun, person2Moon = p2Moon, person2Rising = p2Rising,
            compatibilityScore = synastry.scorePercentage,
            title = "${p1Name} (${p1Sun.symbol}) & ${p2Name} (${p2Sun.symbol}): ${synastry.title}",
            elementalChemistry = "### 🌋 Elemental Dynamics: ${p1Sun.displayName} (${p1Sun.element.displayName}) & ${p2Sun.displayName} (${p2Sun.element.displayName})\n\n" +
                    "The elemental synthesis between ${p1Name}'s ${p1Sun.element.displayName} nature and ${p2Name}'s ${p2Sun.element.displayName} core creates an intricate energetic loop. " +
                    "When ${p1Sun.element.displayName} merges with ${p2Sun.element.displayName}, a powerful balance between grounded stability and expressive dynamism emerges. " +
                    "${p1Name} brings foundational drive and clear strategic perspective, while ${p2Name} infuses the partnership with adaptive flexibility and imaginative depth.\n\n" +
                    "• **Synergy Highlights:** High mutual respect for each other's core motivations and core worldviews.\n" +
                    "• **Pacing:** Natural synchronization in day-to-day decision making and shared goal setting.",
            emotionalResonance = "### 🌙 Moon Sign Connection: ${p1Name}'s ${p1Moon.displayName} Moon & ${p2Name}'s ${p2Moon.displayName} Moon\n\n" +
                    "The emotional blueprint of this connection is defined by the profound synastry of their lunar placements. " +
                    "${p1Name}'s ${p1Moon.displayName} Moon seeks emotional authenticity and protective intimacy, while ${p2Name}'s ${p2Moon.displayName} Moon responds with empathetic receptivity and intuitive understanding.\n\n" +
                    "This creates a sacred sanctuary where both individuals feel safe expressing vulnerable feelings without fear of premature judgment. " +
                    "During times of stress, ${p1Name} offers stabilizing support, while ${p2Name} provides gentle, non-verbal comfort that restores internal peace.",
            communicationDynamics = "### 🗣️ Intellectual & Rising Synergy: ${p1Name}'s ${p1Rising.displayName} Ascendant & ${p2Name}'s ${p2Rising.displayName} Ascendant\n\n" +
                    "Communication flows smoothly thanks to the harmonious alignment between ${p1Name}'s ${p1Rising.displayName} outer aura and ${p2Name}'s ${p2Rising.displayName} demeanor. " +
                    "Intellectual dialogues are characterized by active listening, genuine curiosity, and shared problem-solving strategies.\n\n" +
                    "• **Dialogue Style:** Direct yet considerate exchange of ideas without defensive posturing.\n" +
                    "• **Conflict Resolution:** Both partners demonstrate high willingness to discuss underlying misunderstandings calmly.",
            passionAndAttraction = "### 🔥 Attraction & Cosmic Spark\n\n" +
                    "Magnetic attraction is stoked by the dynamic polarity of their natal chart rulers. " +
                    "There is an unmistakable physical and spiritual draw that inspires both partners to step outside their comfort zones.\n\n" +
                    "The interplay of ${p1Sun.displayName} vitality and ${p2Sun.displayName} magnetic presence ensures that passion remains vibrant and renewed over time through shared adventures and deep intellectual bonding.",
            harmonyPoints = synastry.harmonyPoints,
            frictionPoints = synastry.frictionPoints,
            actionableAdvice = "### 🗝️ Strategic Relationship Playbook for ${p1Name} & ${p2Name}\n\n" +
                    "1. **Honor Emotional Timing:** Respect each other's processing speed when navigating complex personal dilemmas.\n" +
                    "2. **Cultivate Shared Rituals:** Establish dedicated weekly quality time focused purely on creative exploration and honest check-ins.\n" +
                    "3. **Celebrate Individual Growth:** Encourage each partner's personal endeavors, recognizing that personal fulfillment strengthens the relational bond."
        )
    }

    suspend fun generateDeepReport(
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): DeepSynthesisReport = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext generateFallbackReport(testResults, astroProfile)
        }

        val prompt = buildString {
            append("You are an expert psychological astrologer, psychometrician, and behavioral strategist. Generate an exceptionally exhaustive, deeply empowering personalized report (deeppersonality.app style depth) combining the user's psychological test scores and astrological birth chart profile with rigorous detail across every section.\n\n")
            append("PSYCHOLOGICAL SCORES:\n")
            if (testResults.isEmpty()) {
                append("- Default Assessment: Balanced introspective seeker with high cognitive complexity\n")
            } else {
                testResults.forEach { test ->
                    append("- ${test.testTitle}: Dominant Archetype '${test.dominantArchetype}', Summary: ${test.summaryText}\n")
                }
            }
            append("\nASTROLOGY PROFILE:\n")
            if (astroProfile != null) {
                append("- Sun Sign: ${astroProfile.sunSign.displayName} (${astroProfile.sunSign.element.displayName})\n")
                append("- Moon Sign: ${astroProfile.moonSign.displayName}\n")
                append("- Rising Sign: ${astroProfile.risingSign.displayName}\n")
            } else {
                append("- Sun Sign: Scorpio, Moon Sign: Pisces, Rising Sign: Cancer\n")
            }
            append("\nSTRICT REQUIREMENT: Ensure all sections are deeply detailed, comprehensive, and exhaustive with multi-paragraph explanations.\n")
            append("Format your response as structured sections separated by '---SECTION---':\n")
            append("SECTION 1: Title & Core Archetype Fusion\n")
            append("SECTION 2: Psychometric Dimensions & Cognitive Drivers (4 detailed bullet points linking tests to behavior)\n")
            append("SECTION 3: Comprehensive Psychological & Astrological Synthesis (Exhaustive analysis of Sun/Moon/Rising combined with personality traits and unconscious drivers)\n")
            append("SECTION 4: Shadow Work, Blindspots & Integration (4 detailed bullet points with actionable shadow integration steps)\n")
            append("SECTION 5: Career, Purpose & Professional Mastery (Detailed leadership style, optimal environments, and mastery roadmap)\n")
            append("SECTION 6: Relationship, Attachment & Interpersonal Playbook (Attachment style, communication strategies, and emotional boundary management)\n")
            append("SECTION 7: 7-Day Actionable Growth Plan (7 line items formatted as: Day N | Title | Category | Description)\n")
        }

        try {
            val rootObj = JSONObject().apply {
                val contentsArr = JSONArray().apply {
                    val contentObj = JSONObject().apply {
                        val partsArr = JSONArray().apply {
                            val partObj = JSONObject().apply {
                                put("text", prompt)
                            }
                            put(partObj)
                        }
                        put("parts", partsArr)
                    }
                    put(contentObj)
                }
                put("contents", contentsArr)
            }

            val mediaType = "application/json".toMediaType()
            val requestBody = RequestBody.create(mediaType, rootObj.toString())
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseString = response.body?.string() ?: ""

            if (response.isSuccessful && responseString.isNotEmpty()) {
                val respJson = JSONObject(responseString)
                val candidates = respJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val firstCandidate = candidates.getJSONObject(0)
                    val content = firstCandidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        val rawText = parts.getJSONObject(0).optString("text", "")
                        if (rawText.isNotEmpty()) {
                            return@withContext parseRawTextToReport(rawText, testResults, astroProfile)
                        }
                    }
                }
            }
            return@withContext generateFallbackReport(testResults, astroProfile)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext generateFallbackReport(testResults, astroProfile)
        }
    }

    private fun parseRawTextToReport(
        rawText: String,
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): DeepSynthesisReport {
        val sections = rawText.split("---SECTION---").map { it.trim() }

        val title = if (sections.size > 1) sections[1].lines().firstOrNull() ?: "The Mystic Catalyst" else "The Mystic Catalyst"
        val traits = if (sections.size > 2) {
            sections[2].lines().filter { it.startsWith("-") || it.startsWith("*") }.map { it.removePrefix("-").removePrefix("*").trim() }
        } else listOf("Intuitive Vision", "Strategic Mastery", "Empathetic Resilience")

        val psychAstro = if (sections.size > 3) sections[3] else "Your psychological profile reveals high cognitive complexity blended seamlessly with your astrological water and fire placements."
        val shadow = if (sections.size > 4) {
            sections[4].lines().filter { it.isNotBlank() && !it.startsWith("SECTION") }
        } else listOf("Over-analyzing emotional motives", "Hesitation to delegate under high stress", "Protecting inner sensitivity with aloof barriers")

        val career = if (sections.size > 5) sections[5] else "Thrives in autonomous strategic roles where innovation meets human empathy."
        val relationships = if (sections.size > 6) sections[6] else "Seeks deep emotional intimacy accompanied by absolute respect for personal autonomy."

        val habits = mutableListOf<DailyHabitItem>()
        if (sections.size > 7) {
            val lines = sections[7].lines()
            var dayCounter = 1
            lines.forEach { line ->
                if (line.contains("|") && dayCounter <= 7) {
                    val parts = line.split("|").map { it.trim() }
                    val hTitle = parts.getOrNull(1) ?: "Micro Habit"
                    val hCat = parts.getOrNull(2) ?: "Mindset"
                    val hDesc = parts.getOrNull(3) ?: "Reflect deeply on today's emotional reactions."
                    habits.add(DailyHabitItem(dayCounter, hTitle, hDesc, hCat))
                    dayCounter++
                }
            }
        }

        if (habits.isEmpty()) {
            habits.addAll(createDefault7DayPlan(astroProfile?.sunSign?.displayName ?: "Scorpio"))
        }

        return DeepSynthesisReport(
            id = UUID.randomUUID().toString(),
            title = title,
            archetypeSummary = "Unified Mind & Cosmic Blueprint",
            coreTraits = if (traits.isNotEmpty()) traits else listOf("Empathy", "Visionary Logic", "Resilience"),
            psychologicalBreakdown = psychAstro,
            astrologicalSynthesis = "Sun in ${astroProfile?.sunSign?.displayName ?: "Scorpio"}, Moon in ${astroProfile?.moonSign?.displayName ?: "Pisces"}, Rising in ${astroProfile?.risingSign?.displayName ?: "Cancer"}",
            shadowWorkInsights = shadow,
            careerAndPurposeAdvice = career,
            relationshipPlaybook = relationships,
            dailyActionPlan = habits,
            isGeneratedByAi = true
        )
    }

    fun generateFallbackReport(
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): DeepSynthesisReport {
        val sun = astroProfile?.sunSign ?: ZodiacSign.SCORPIO
        val moon = astroProfile?.moonSign ?: ZodiacSign.PISCES
        val rising = astroProfile?.risingSign ?: ZodiacSign.CANCER

        val dominantArchetypes = testResults.joinToString(", ") { it.dominantArchetype }.ifEmpty { "Intuitive Strategist (INFJ & Type 4)" }

        val title = "Deep Synthesis: ${sun.displayName} Sun & ${dominantArchetypes}"

        return DeepSynthesisReport(
            id = UUID.randomUUID().toString(),
            title = title,
            archetypeSummary = "The Master Alchemist: Unified Mind & Cosmic Blueprint",
            coreTraits = listOf(
                "Perceptive Emotional Radar (${moon.displayName} Moon)",
                "Relentless Strategic Autonomy (${sun.displayName} Sun)",
                "Protective & Inviting External Aura (${rising.displayName} Rising)",
                "High Openness & Abstract Cognitive Complexity",
                "Deep Resilience Under Psychological Stress"
            ),
            psychologicalBreakdown = "### 🧠 Deep Psychological Breakdown\n\n" +
                    "Your cognitive and personality profile reflects an exceptional alignment between deep analytical introspection and heightened emotional receptivity. " +
                    "Across your psychological test assessments, your results reveal a dominant cognitive style focused on synthesizing abstract patterns, decoding subtle interpersonal nuances, and maintaining high standards of personal agency.\n\n" +
                    "Unlike conventional personalities that lean exclusively towards either cold logic or ungrounded emotion, your cognitive architecture functions as a bridge. " +
                    "You possess the mental discipline required to dissect complex problems objectively, alongside an empathetic radar that grasps the unsaid motivations of others. " +
                    "Under pressure, your primary coping strategy relies on internal reflection and strategic re-framing rather than impulsive reaction.",
            astrologicalSynthesis = "### 🌌 Natal Chart Cosmic Matrix\n\n" +
                    "Your astrological Trinity acts as the foundational energetic framework for your mind:\n\n" +
                    "• **The Core Vitality (${sun.displayName} Sun in ${sun.element.displayName}):** Your core driver centers on transformation, unwavering focus, and authentic self-expression. You operate best when aligned with deep purpose and total ownership over your life path.\n" +
                    "• **The Inner Sanctuary (${moon.displayName} Moon in ${moon.element.displayName}):** Your emotional core is anchored by vivid imaginative depth and intuitive wisdom. You absorb subtle environmental shifts effortlessly, making rest and quiet recalibration essential for maintaining optimal focus.\n" +
                    "• **The Exterior Doorway (${rising.displayName} Rising in ${rising.element.displayName}):** You present an aura of calm, perceptive dignity. People naturally feel drawn to your steady presence and trust your judgment in moments of crisis.",
            shadowWorkInsights = listOf(
                "Recognize when self-preservation turns into unnecessary emotional isolation or aloofness.",
                "Practice expressing vulnerable boundaries before stress builds to critical mass.",
                "Balance high perfectionist ideals with compassionate self-acceptance.",
                "Beware of over-analyzing emotional motives when simple honest dialogue suffices.",
                "Acknowledge the physical fatigue caused by taking on emotional burdens from your environment."
            ),
            careerAndPurposeAdvice = "### 🚀 Career, Purpose & Professional Mastery\n\n" +
                    "You thrive in high-autonomy environments where strategic vision, creative innovation, and human empathy intersect. " +
                    "Your ideal career path permits independent decision-making and offers tangible meaning rather than repetitive routine.\n\n" +
                    "• **Optimal Work Conditions:** Independent strategic roles, leadership position in mission-driven ventures, executive counseling, creative direction, or psychological research.\n" +
                    "• **Leadership Style:** Empathetic yet decisive. You lead by example, inspiring trust through competence, integrity, and quiet authority.\n" +
                    "• **Growth Key:** Avoid micro-management traps by delegating operational mechanics while retaining vision control.",
            relationshipPlaybook = "### 💖 Interpersonal & Relationship Playbook\n\n" +
                    "In intimate and professional relationships, you require both profound emotional depth and sacred personal space. " +
                    "Surface-level small talk leaves you drained, whereas authentic vulnerability and intellectual synergy fuel your connection.\n\n" +
                    "• **Communication Strategy:** Pair clear verbal statements with explicit appreciation for your partner's love language. Do not expect others to read non-verbal cues.\n" +
                    "• **Emotional Safety:** Establish clear personal boundaries early, ensuring both you and your partner feel respected and understood.\n" +
                    "• **Conflict Resolution:** Step back during heated moments to process emotions internally, then re-engage with calm clarity.",
            dailyActionPlan = createDefault7DayPlan(sun.displayName),
            isGeneratedByAi = false
        )
    }

    private fun createDefault7DayPlan(sunSignName: String): List<DailyHabitItem> {
        return listOf(
            DailyHabitItem(1, "Morning Grounding Ritual", "Spend 5 minutes in silent breathwork channeling your $sunSignName grounding energy.", "Mindset"),
            DailyHabitItem(2, "Shadow Work Journaling", "Write down one recurring irritation and trace it back to an unmet boundary.", "Shadow Work"),
            DailyHabitItem(3, "Expressive Love Language", "Offer an explicit, heartfelt verbal affirmation to a loved one today.", "Relationship"),
            DailyHabitItem(4, "Cosmic Energy Detox", "Disconnect from digital screens 1 hour before sleep to recalibrate focus.", "Astrological Action"),
            DailyHabitItem(5, "Resilience Micro-Challenge", "Reflect on a recent obstacle and write 3 unexpected strengths gained from it.", "Emotional"),
            DailyHabitItem(6, "Creative Flow Session", "Dedicate 30 minutes to an uninhibited creative passion project without judgment.", "Mindset"),
            DailyHabitItem(7, "Weekly Self-Mastery Audit", "Review your completed habits, celebrate growth, and set next week's intention.", "Shadow Work")
        )
    }

    suspend fun generateMasterMetaAnalysisReport(
        savedReports: List<DeepSynthesisReport>,
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): DeepSynthesisReport = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext generateFallbackMasterMetaReport(savedReports, testResults, astroProfile)
        }

        val prompt = buildString {
            append("You are a world-class psychological astrologer, narrative therapist, and meta-analytic counselor. ")
            append("The user has generated multiple personal reports and assessment results over time. ")
            append("Analyze ALL personal reports and test scores TOGETHER to produce a comprehensive Master Personal Meta-Analysis Report that cross-synthesizes what they all mean in combination.\n\n")

            append("SAVED PERSONAL REPORTS TO SYNTHESIZE (${savedReports.size} total):\n")
            if (savedReports.isEmpty()) {
                append("- None currently saved. Analyze based on completed test results below.\n")
            } else {
                savedReports.forEachIndexed { index, report ->
                    append("REPORT #${index + 1}: ${report.title}\n")
                    append("  - Archetype Summary: ${report.archetypeSummary}\n")
                    append("  - Core Traits: ${report.coreTraits.joinToString(", ")}\n")
                    append("  - Breakdown Summary: ${report.psychologicalBreakdown.take(300)}\n")
                    append("  - Shadow Insights: ${report.shadowWorkInsights.joinToString("; ")}\n\n")
                }
            }

            append("COMPLETED PSYCHOLOGY TEST ASSESSMENTS (${testResults.size} total):\n")
            if (testResults.isEmpty()) {
                append("- Default Assessment Profile: Balanced Mind & Intuitive Explorer\n")
            } else {
                testResults.forEach { test ->
                    append("- Test: ${test.testTitle} | Dominant Trait: '${test.dominantArchetype}' | Summary: ${test.summaryText}\n")
                }
            }

            append("\nASTROLOGY PLACEMENTS:\n")
            if (astroProfile != null) {
                append("- Sun: ${astroProfile.sunSign.displayName} (${astroProfile.sunSign.element.displayName})\n")
                append("- Moon: ${astroProfile.moonSign.displayName}\n")
                append("- Rising: ${astroProfile.risingSign.displayName}\n")
            } else {
                append("- Sun: Scorpio, Moon: Pisces, Rising: Cancer\n")
            }

            append("\nTask: Perform a deep, interconnected meta-synthesis explaining how all these individual reports intersect, validate each other, and reveal the user's master overarching identity.\n")
            append("Format response into sections separated by '---SECTION---':\n")
            append("SECTION 1: Master Title & Meta-Archetype Fusion\n")
            append("SECTION 2: Master Core Convergent Traits (4-5 overarching themes across ALL reports, bullet points)\n")
            append("SECTION 3: Integrated Meta-Psychological & Astrological Synthesis (Detailing how all reports connect into a single unified whole)\n")
            append("SECTION 4: Reconciled Shadow Insights & Cross-Report Blindspots (4 bullet points)\n")
            append("SECTION 5: Unified Career, Life Calling & Strategic Purpose\n")
            append("SECTION 6: Master Interpersonal & Relationship Dynamics\n")
            append("SECTION 7: 7-Day Master Integration Action Plan (7 line items formatted as: Day N | Title | Category | Description)\n")
        }

        try {
            val rootObj = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply { put("text", prompt) })
                        })
                    })
                })
            }

            val requestBody = RequestBody.create("application/json".toMediaType(), rootObj.toString())
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseString = response.body?.string() ?: ""

            if (response.isSuccessful && responseString.isNotEmpty()) {
                val respJson = JSONObject(responseString)
                val candidates = respJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val rawText = candidates.getJSONObject(0).optJSONObject("content")?.optJSONArray("parts")?.getJSONObject(0)?.optString("text", "") ?: ""
                    if (rawText.isNotEmpty()) {
                        val parsedReport = parseRawTextToReport(rawText, testResults, astroProfile)
                        return@withContext parsedReport.copy(
                            archetypeSummary = "Unified Multi-Report Meta-Analysis (${savedReports.size} Reports Synthesized)"
                        )
                    }
                }
            }
            return@withContext generateFallbackMasterMetaReport(savedReports, testResults, astroProfile)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext generateFallbackMasterMetaReport(savedReports, testResults, astroProfile)
        }
    }

    fun generateFallbackMasterMetaReport(
        savedReports: List<DeepSynthesisReport>,
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): DeepSynthesisReport {
        val count = savedReports.size
        val sun = astroProfile?.sunSign ?: ZodiacSign.SCORPIO
        val moon = astroProfile?.moonSign ?: ZodiacSign.PISCES
        val rising = astroProfile?.risingSign ?: ZodiacSign.CANCER

        // Collect all traits from saved reports
        val allTraits = savedReports.flatMap { it.coreTraits }.distinct().take(5)
        val combinedTraits = if (allTraits.isNotEmpty()) allTraits else listOf(
            "Unified Intuitive Strategy (${sun.displayName} Sun)",
            "Cross-Validated Empathy (${moon.displayName} Moon)",
            "Integrated Boundary Mastery",
            "Multi-Layered Self-Awareness",
            "Master Synthesis Capacity"
        )

        val reportTitlesList = savedReports.joinToString(", ") { "'${it.title}'" }.ifEmpty { "Psychological & Astrological Assessments" }

        val summaryText = "### 🏛️ Unified Master Meta-Analysis\n\n" +
                "By cross-synthesizing your $count saved personal reports ($reportTitlesList) alongside your $count assessment entries and natal placements (${sun.displayName} Sun, ${moon.displayName} Moon, ${rising.displayName} Rising), a profound master pattern emerges.\n\n" +
                "Your individual psychological test scores and astrological placements validate and reinforce one another. " +
                "Across all data points, your primary cognitive superpower is **Multi-Layered Conceptual Synthesis**—the ability to simultaneously parse logical patterns, emotional currents, and long-term consequences.\n\n" +
                "Rather than fragmented traits operating in isolation, your psychological ecosystem is governed by an integrated inner operating system that seeks authenticity, self-mastery, and purposeful impact."

        val masterAstroSynthesis = "### 🌌 Master Astrological & Psychological Convergence\n\n" +
                "Your natal chart triad acts as the central matrix unifying all psychological metrics:\n\n" +
                "• **Purpose Alignment (${sun.displayName} Sun):** Provides unwavering determination and creative sovereignty that anchors your core aspirations.\n" +
                "• **Intuitive Guidance (${moon.displayName} Moon):** Informs your psychological empathy, ensuring your logical decisions remain attuned to human values and inner emotional truth.\n" +
                "• **Outer Integration (${rising.displayName} Rising):** Shields your sensitive internal processes with an outer presence of calm dignity and executive authority."

        val masterShadows = listOf(
            "Guarding your inner world so intensely that trusted allies cannot perceive your genuine vulnerabilities or workload fatigue.",
            "Attempting to resolve internal emotional tension through intellectual over-analysis rather than direct somatic expression.",
            "Misinterpreting deep emotional sensitivity as a liability rather than a high-level strategic asset.",
            "Over-committing to high standards across multiple creative and professional projects simultaneously.",
            "Hesitating to seek assistance due to a strong preference for complete self-reliance."
        )

        val masterCareer = "### 🎯 Master Career & Life Purpose Strategy\n\n" +
                "The unified consensus across all analyzed reports indicates that your ultimate career path requires high autonomy, intellectual rigor, and profound human purpose.\n\n" +
                "• **Ideal Domain:** Strategic leadership, psychological research, executive coaching, creative direction, or mission-driven entrepreneurship.\n" +
                "• **Core Driver:** Transforming complex chaos into clear, structured, and meaningful solutions.\n" +
                "• **Fulfillment Rule:** Ensure every major project aligns directly with your core values and allows creative freedom."

        val masterRel = "### 🤝 Master Interpersonal & Relationship Architecture\n\n" +
                "Across all synthesized assessments, your relationship profile confirms that deep authenticity and mutual respect for personal space are non-negotiable.\n\n" +
                "• **Relational Need:** High emotional safety paired with intellectual stimulation.\n" +
                "• **Key Practice:** Practice explicit verbal communication regarding your need for introverted recovery time, ensuring loved ones feel valued while honoring your sanctuary."

        return DeepSynthesisReport(
            id = UUID.randomUUID().toString(),
            title = "Master Personal Meta-Analysis: Unified Synthesis of $count Reports",
            archetypeSummary = "Unified Multi-Report Meta-Analysis ($count Reports Synthesized)",
            coreTraits = combinedTraits,
            psychologicalBreakdown = summaryText,
            astrologicalSynthesis = masterAstroSynthesis,
            shadowWorkInsights = masterShadows,
            careerAndPurposeAdvice = masterCareer,
            relationshipPlaybook = masterRel,
            dailyActionPlan = createDefault7DayPlan(sun.displayName),
            isGeneratedByAi = false
        )
    }

    suspend fun generateNameMeaningReport(
        rawName: String,
        astroProfile: AstrologyProfile?
    ): NameMeaningReport = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        val name = rawName.trim().ifBlank { astroProfile?.userName?.ifBlank { "Seeker" } ?: "Seeker" }

        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext NameAnalysisEngine.analyzeName(name)
        }

        val sun = astroProfile?.sunSign?.displayName ?: "Scorpio"
        val prompt = "You are a world-renowned onomastic scholar, depth psychologist, and etymological historian.\n" +
                "Generate an exhaustive, highly detailed JSON report analyzing the name: \"$name\" (User Astrological Sun Sign: $sun).\n\n" +
                "Provide a valid JSON object with the following schema:\n" +
                "{\n" +
                "  \"etymologies\": [\n" +
                "    { \"languageOrCulture\": \"Culture/Language 1\", \"literalMeaning\": \"Literal meaning 1\", \"historicalContext\": \"Historical detail 1\" },\n" +
                "    { \"languageOrCulture\": \"Culture/Language 2\", \"literalMeaning\": \"Literal meaning 2\", \"historicalContext\": \"Historical detail 2\" },\n" +
                "    { \"languageOrCulture\": \"Culture/Language 3\", \"literalMeaning\": \"Literal meaning 3\", \"historicalContext\": \"Historical detail 3\" }\n" +
                "  ],\n" +
                "  \"parentalIntentCategory\": \"Category e.g. Classic Leadership / Spiritual Blessing / Ancestral Heritage / Nature Harmony / Pioneering Identity\",\n" +
                "  \"parentalIntentPsychology\": \"Exhaustive analysis of how parental / naming source intention shapes subconscious self-concept and life expectations.\",\n" +
                "  \"personalityEffects\": \"Comprehensive breakdown of how carrying this name affects personal identity, social presence, cognitive style, and interpersonal dynamics.\",\n" +
                "  \"numerologicalVibration\": \"Pythagorean expression number, vibrational sound resonance, and core archetype.\",\n" +
                "  \"shadowIntegrationAdvice\": \"3 actionable guidance points for integrating the expectations/weight of this name into personal sovereignty.\"\n" +
                "}\n\n" +
                "Locate as many valid historical/cultural/linguistic origins for this name as possible (minimum 3 distinct origins). Ensure tone is empowering, intellectually rigorous, and compassionate."

        try {
            val rootObj = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply { put("text", prompt) })
                        })
                    })
                })
            }
            val requestBody = RequestBody.create("application/json".toMediaType(), rootObj.toString())
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseString = response.body?.string() ?: ""

            if (response.isSuccessful && responseString.isNotEmpty()) {
                val respJson = JSONObject(responseString)
                val candidates = respJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val firstCandidate = candidates.getJSONObject(0)
                    val content = firstCandidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        var text = parts.getJSONObject(0).optString("text", "")
                        if (text.startsWith("```json")) text = text.removePrefix("```json")
                        if (text.startsWith("```")) text = text.removePrefix("```")
                        if (text.endsWith("```")) text = text.removeSuffix("```")
                        text = text.trim()

                        val json = JSONObject(text)
                        val etymologiesList = mutableListOf<NameEtymologySource>()
                        val etymArr = json.optJSONArray("etymologies")
                        if (etymArr != null) {
                            for (i in 0 until etymArr.length()) {
                                val item = etymArr.getJSONObject(i)
                                etymologiesList.add(
                                    NameEtymologySource(
                                        languageOrCulture = item.optString("languageOrCulture", "Global Origin"),
                                        literalMeaning = item.optString("literalMeaning", "Meaning of $name"),
                                        historicalContext = item.optString("historicalContext", "")
                                    )
                                )
                            }
                        }

                        if (etymologiesList.isNotEmpty()) {
                            return@withContext NameMeaningReport(
                                name = name,
                                etymologies = etymologiesList,
                                parentalIntentCategory = json.optString("parentalIntentCategory", "Pioneering Identity"),
                                parentalIntentPsychology = json.optString("parentalIntentPsychology", ""),
                                personalityEffects = json.optString("personalityEffects", ""),
                                numerologicalVibration = json.optString("numerologicalVibration", ""),
                                shadowIntegrationAdvice = json.optString("shadowIntegrationAdvice", "")
                            )
                        }
                    }
                }
            }
            return@withContext NameAnalysisEngine.analyzeName(name)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext NameAnalysisEngine.analyzeName(name)
        }
    }
}

