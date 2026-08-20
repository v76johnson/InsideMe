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
}
