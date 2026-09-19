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
) {
    fun toAiCompanionChatMessage(): String {
        val sb = StringBuilder()
        sb.append("✨ **Comprehensive Onomastic & Name Analysis for \"$name\":**\n\n")
        sb.append("### 🏛️ Etymological Origins & Meanings\n")
        etymologies.forEach { ety ->
            sb.append("• **${ety.languageOrCulture}:** \"${ety.literalMeaning}\"\n")
            sb.append("  *${ety.historicalContext}*\n\n")
        }
        sb.append("### 🧠 Parental Intent & Psychology ($parentalIntentCategory)\n")
        sb.append("$parentalIntentPsychology\n\n")
        sb.append("### 🔮 Personality & Behavioral Effects\n")
        sb.append("$personalityEffects\n\n")
        sb.append("### 🔢 Numerological Vibration\n")
        sb.append("$numerologicalVibration\n\n")
        sb.append("### 🌑 Shadow Work & Integration\n")
        sb.append("$shadowIntegrationAdvice")
        return sb.toString()
    }

    fun toAiCompanionGreetingMessage(): String {
        val sb = StringBuilder()
        sb.append("👋 I am Ono, your onomastic companion. I know all about names. Like your name, \"$name\", which has a fascinating background!\n\n")
        sb.append("What would you like to dive into? You can ask me about:\n")
        sb.append("• Origins (cultural roots and history)\n")
        sb.append("• Meaning (literal definitions and etymology)\n")
        sb.append("• Personality (behavioral tendencies and traits)\n")
        sb.append("• Numerology (vibrational numbers)\n")
        sb.append("• Shadow Work (serious growth considerations)\n\n")
        sb.append("Just let me know what topic catches your eye, and let's chat!")
        return sb.toString()
    }
}
