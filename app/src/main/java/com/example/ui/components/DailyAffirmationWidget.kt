package com.example.ui.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.ShadowRose

data class DailyAffirmationItem(
    val id: String,
    val quote: String,
    val sourceCategory: String,
    val psychologyPrinciple: String,
    val reflectionPrompt: String,
    val isPersonalized: Boolean,
    val relatedTestId: String? = null
)

object AffirmationEngine {
    fun getPersonalizedAffirmations(
        testResults: List<TestResultEntity>,
        astrologyProfile: AstrologyProfile?
    ): List<DailyAffirmationItem> {
        val list = mutableListOf<DailyAffirmationItem>()

        val completedTestIds = testResults.map { it.testId }.toSet()
        val resultsByTestId = testResults.associateBy { it.testId }

        // 1. Myers-Briggs / 16 Personalities
        if (completedTestIds.contains("mbti_16") || completedTestIds.contains("major_personality_combined")) {
            val archetype = resultsByTestId["major_personality_combined"]?.dominantArchetype ?: resultsByTestId["mbti_16"]?.dominantArchetype ?: "Myers-Briggs Profile"
            list.add(
                DailyAffirmationItem(
                    id = "mbti_1",
                    quote = "Your cognitive depth and unique analytical perspective are powerful assets. Honor your natural way of processing thoughts, and trust your internal vision.",
                    sourceCategory = "Myers-Briggs ($archetype)",
                    psychologyPrinciple = "Cognitive Self-Congruence & Temperament Alignment",
                    reflectionPrompt = "Where can you give yourself permission to process thoughts at your own pace today?",
                    isPersonalized = true,
                    relatedTestId = "major_personality_combined"
                )
            )
            list.add(
                DailyAffirmationItem(
                    id = "mbti_2",
                    quote = "You do not need to fit into someone else's rhythm to create value. Your distinct perspective brings clarity to complex situations.",
                    sourceCategory = "Myers-Briggs ($archetype)",
                    psychologyPrinciple = "Cognitive Diversity & Authentic Functioning",
                    reflectionPrompt = "What intuitive insight can you act on with quiet confidence today?",
                    isPersonalized = true,
                    relatedTestId = "major_personality_combined"
                )
            )
        }

        // 2. Enneagram
        if (completedTestIds.contains("enneagram") || completedTestIds.contains("major_personality_combined")) {
            val archetype = resultsByTestId["major_personality_combined"]?.dominantArchetype ?: resultsByTestId["enneagram"]?.dominantArchetype ?: "Enneagram Core"
            list.add(
                DailyAffirmationItem(
                    id = "ennea_1",
                    quote = "Your worth is inherent, constant, and complete. You do not need to perform, perfect, or prove yourself to deserve peace and belonging.",
                    sourceCategory = "Enneagram ($archetype)",
                    psychologyPrinciple = "Ego Freedom & Unconditional Self-Worth",
                    reflectionPrompt = "Where can you release the compulsion for external perfection today?",
                    isPersonalized = true,
                    relatedTestId = "major_personality_combined"
                )
            )
            list.add(
                DailyAffirmationItem(
                    id = "ennea_2",
                    quote = "Vulnerability is not weakness; it is the courage to be fully human. Embrace your feelings with gentle curiosity.",
                    sourceCategory = "Enneagram Integration",
                    psychologyPrinciple = "Emotional Integration & Shadow Acceptance",
                    reflectionPrompt = "What emotion are you ready to acknowledge with compassion right now?",
                    isPersonalized = true,
                    relatedTestId = "major_personality_combined"
                )
            )
        }

        // 3. Attachment Style
        if (completedTestIds.contains("attachment_style") || completedTestIds.contains("relationship_attachment_combined")) {
            val archetype = resultsByTestId["relationship_attachment_combined"]?.dominantArchetype ?: resultsByTestId["attachment_style"]?.dominantArchetype ?: "Attachment Blueprint"
            list.add(
                DailyAffirmationItem(
                    id = "attach_1",
                    quote = "Authentic security begins within your own mind. You are capable of establishing healthy boundaries while staying open to mutual connection.",
                    sourceCategory = "Attachment Style ($archetype)",
                    psychologyPrinciple = "Relational Security & Self-Soothing",
                    reflectionPrompt = "How can you honor your emotional boundaries in a relationship today?",
                    isPersonalized = true,
                    relatedTestId = "relationship_attachment_combined"
                )
            )
            list.add(
                DailyAffirmationItem(
                    id = "attach_2",
                    quote = "Expressing your relational needs directly is an act of self-respect. Safe connections welcome your honest voice.",
                    sourceCategory = "Attachment Security",
                    psychologyPrinciple = "Direct Emotional Communication",
                    reflectionPrompt = "What emotional requirement can you communicate clearly and calmly today?",
                    isPersonalized = true,
                    relatedTestId = "relationship_attachment_combined"
                )
            )
        }

        // 4. Big Five / NEO-PI-R / HEXACO
        if (completedTestIds.contains("bfi_big_five") || completedTestIds.contains("neo_pi_r") || completedTestIds.contains("hexaco_pi_r") || completedTestIds.contains("major_personality_combined")) {
            list.add(
                DailyAffirmationItem(
                    id = "bigfive_1",
                    quote = "Every trait dimension you possess serves a meaningful function. Align your environment with your temperament to flourish naturally.",
                    sourceCategory = "Big Five Traits",
                    psychologyPrinciple = "Personality-Environment Fit & Agency",
                    reflectionPrompt = "How can you structure your day to suit your natural energy levels?",
                    isPersonalized = true,
                    relatedTestId = "major_personality_combined"
                )
            )
        }

        // 5. Emotional Intelligence & Resilience (EQ)
        if (completedTestIds.contains("eq_resilience") || completedTestIds.contains("eq_resilience_combined")) {
            list.add(
                DailyAffirmationItem(
                    id = "eq_1",
                    quote = "Emotions are informative data signals, not final dictates. Pausing between impulse and action is the essence of emotional mastery.",
                    sourceCategory = "Emotional Intelligence (EQ)",
                    psychologyPrinciple = "Affective Regulation & Metacognitive Pause",
                    reflectionPrompt = "When you encounter tension today, how will you take a breath before responding?",
                    isPersonalized = true,
                    relatedTestId = "eq_resilience_combined"
                )
            )
        }

        // 6. Relational & Love Languages
        if (completedTestIds.contains("love_languages") || completedTestIds.contains("relationship_attachment_combined")) {
            list.add(
                DailyAffirmationItem(
                    id = "love_1",
                    quote = "Giving and receiving love with open clarity fosters genuine intimacy. Honor how you feel most valued and appreciated.",
                    sourceCategory = "Love & Relational Languages",
                    psychologyPrinciple = "Relational Reciprocity & Emotional Validation",
                    reflectionPrompt = "How can you express sincere appreciation to someone you care about today?",
                    isPersonalized = true,
                    relatedTestId = "relationship_attachment_combined"
                )
            )
        }

        // 7. Clinical Coping & Stress (BDI-II, STAI, WHODAS)
        if (completedTestIds.contains("stai") || completedTestIds.contains("bdi_ii") || completedTestIds.contains("whodas_2") || completedTestIds.contains("symptom_mood_combined") || completedTestIds.contains("clinical_diagnostics_combined")) {
            list.add(
                DailyAffirmationItem(
                    id = "stress_1",
                    quote = "Be gentle with your inner world. Grounding yourself in the present moment frees cognitive energy to take small, meaningful steps.",
                    sourceCategory = "Mindful Stress Relief",
                    psychologyPrinciple = "Somatosensory Grounding & De-escalation",
                    reflectionPrompt = "What is one small, comforting action you can perform for yourself right now?",
                    isPersonalized = true,
                    relatedTestId = "symptom_mood_combined"
                )
            )
        }

        // 8. Unconscious & Shadow (TAT, Rorschach)
        if (completedTestIds.contains("tat") || completedTestIds.contains("rorschach") || completedTestIds.contains("projective_depth_combined")) {
            list.add(
                DailyAffirmationItem(
                    id = "shadow_1",
                    quote = "Shining awareness into the hidden depths of your mind turns unconscious habits into intentional choices. Welcome your full self.",
                    sourceCategory = "Unconscious Depth & Shadow Work",
                    psychologyPrinciple = "Psychic Integration & Shadow Wholeness",
                    reflectionPrompt = "What aspect of yourself deserves acceptance and compassion today?",
                    isPersonalized = true,
                    relatedTestId = "projective_depth_combined"
                )
            )
        }

        // 9. Astrological Sun Sign (if profile available)
        if (astrologyProfile != null) {
            val sunSign = astrologyProfile.sunSign
            list.add(
                DailyAffirmationItem(
                    id = "astro_sun",
                    quote = "With your Sun in ${sunSign.displayName} (${sunSign.element.displayName} Element), your core vitality flourishes when you align your daily actions with authentic purpose.",
                    sourceCategory = "Astrological Sun Sign",
                    psychologyPrinciple = "Elemental Core Vitality & Purpose Alignment",
                    reflectionPrompt = "How can you express your ${sunSign.keywords.firstOrNull() ?: "inner drive"} with clarity today?",
                    isPersonalized = true
                )
            )
        }

        // 10. Universal Psychology Grounding Affirmations (Fallback / Baseline)
        list.add(
            DailyAffirmationItem(
                id = "univ_1",
                quote = "Self-awareness is the foundation of all psychological growth. Today, observe your thoughts with compassion and act with clear intention.",
                sourceCategory = "Psychological Self-Awareness",
                psychologyPrinciple = "Metacognitive Awareness & Mindfulness",
                reflectionPrompt = "What recurring thought can you observe today without taking it as absolute truth?",
                isPersonalized = false
            )
        )

        list.add(
            DailyAffirmationItem(
                id = "univ_2",
                quote = "Your mind is an evolving sanctuary. Every small step toward self-understanding builds unshakeable internal resilience.",
                sourceCategory = "Mind & Soul Resilience",
                psychologyPrinciple = "Incremental Growth Mindset",
                reflectionPrompt = "What small win or insight can you acknowledge and celebrate today?",
                isPersonalized = false
            )
        )

        list.add(
            DailyAffirmationItem(
                id = "univ_3",
                quote = "True confidence isn't the absence of self-doubt, but the courage to honor your inner wisdom despite life's uncertainties.",
                sourceCategory = "Intrinsic Self-Efficacy",
                psychologyPrinciple = "Self-Efficacy & Courageous Action",
                reflectionPrompt = "Where in your life can you trust your judgment a little more today?",
                isPersonalized = false
            )
        )

        return list
    }
}

@Composable
fun DailyAffirmationWidget(
    testResults: List<TestResultEntity>,
    astrologyProfile: AstrologyProfile?,
    onTakeAssessmentClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    val affirmations = remember(testResults, astrologyProfile) {
        AffirmationEngine.getPersonalizedAffirmations(testResults, astrologyProfile)
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentItem = affirmations.getOrElse(currentIndex % affirmations.size) { affirmations.first() }

    var isBookmarked by remember { mutableStateOf(false) }
    var showReflectionPrompt by remember { mutableStateOf(true) }

    val personalizedCount = remember(affirmations) { affirmations.count { it.isPersonalized } }

    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(CelestialGold, NebulaTeal, MysticViolet)
                ),
                shape = RoundedCornerShape(22.dp)
            )
            .testTag("daily_affirmation_widget")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header Row: Widget Title & Personalization Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(CelestialGold.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription = "Affirmation Icon",
                            tint = CelestialGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "DAILY PSYCHOLOGY AFFIRMATION",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = CelestialGold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Mind & Soul Grounding",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 11.sp
                        )
                    }
                }

                // Personalization Pill Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (currentItem.isPersonalized) NebulaTeal.copy(alpha = 0.25f) else MysticViolet.copy(alpha = 0.4f)
                        )
                        .border(
                            width = 1.dp,
                            color = if (currentItem.isPersonalized) NebulaTeal else MysticViolet,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (currentItem.isPersonalized) "✨ Tailored" else "💡 Baseline",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (currentItem.isPersonalized) NebulaTeal else Color.White,
                        fontSize = 10.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Source Category Label
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                Icon(
                    Icons.Default.Psychology,
                    contentDescription = null,
                    tint = NebulaTeal,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = currentItem.sourceCategory,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = NebulaTeal
                )
            }

            // Main Quote Display Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(DeepSpace.copy(alpha = 0.6f))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "“",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold,
                        lineHeight = 20.sp
                    )

                    Text(
                        text = currentItem.quote,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 22.sp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "— Psychology Principle: ${currentItem.psychologyPrinciple}",
                        style = MaterialTheme.typography.labelSmall,
                        color = CelestialGold.copy(alpha = 0.9f),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Toggleable Reflection Prompt
            AnimatedVisibility(
                visible = showReflectionPrompt,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MysticViolet.copy(alpha = 0.25f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.SelfImprovement,
                                contentDescription = null,
                                tint = CelestialGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Daily Reflection Prompt:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentItem.reflectionPrompt,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f),
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action Section: Elements arranged cleanly one under another
            // 1. Sub-header with Reflection Toggle and Progress Counter
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { showReflectionPrompt = !showReflectionPrompt },
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Icon(
                        Icons.Default.SelfImprovement,
                        contentDescription = null,
                        tint = CelestialGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (showReflectionPrompt) "Hide Reflection" else "Reflection Prompt",
                        style = MaterialTheme.typography.labelSmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(DeepSpace.copy(alpha = 0.6f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "${currentIndex + 1} of ${affirmations.size}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 2. Action Buttons Row (Next Quote, Copy, Favorite)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Next Affirmation Button
                Button(
                    onClick = {
                        currentIndex = (currentIndex + 1) % affirmations.size
                        isBookmarked = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CelestialGold,
                        contentColor = DeepSpace
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(42.dp)
                        .testTag("next_affirmation_button")
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Next Affirmation",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Next Quote", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Copy Quote Button
                IconButton(
                    onClick = {
                        val copyText = "“${currentItem.quote}”\n— ${currentItem.sourceCategory} Affirmation\nPrinciple: ${currentItem.psychologyPrinciple}"
                        clipboardManager.setText(AnnotatedString(copyText))
                        Toast.makeText(context, "Affirmation copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MysticViolet.copy(alpha = 0.4f))
                        .testTag("copy_affirmation_button")
                ) {
                    Icon(
                        Icons.Default.ContentCopy,
                        contentDescription = "Copy Quote",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Bookmark / Favorite Button
                IconButton(
                    onClick = {
                        isBookmarked = !isBookmarked
                        val msg = if (isBookmarked) "Saved to favorite affirmations!" else "Removed from favorites."
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isBookmarked) ShadowRose.copy(alpha = 0.3f) else MysticViolet.copy(alpha = 0.4f)
                        )
                        .testTag("favorite_affirmation_button")
                ) {
                    Icon(
                        if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) ShadowRose else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // 3. Notice / CTA if user has not completed assessments yet (Stacked vertically)
            if (testResults.isEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MysticViolet.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = CelestialGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Complete assessments to unlock quotes tailored to your MBTI, Enneagram, & Attachment profile!",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = onTakeAssessmentClicked,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CelestialGold.copy(alpha = 0.2f),
                                contentColor = CelestialGold
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(38.dp)
                        ) {
                            Text(
                                text = "Take Assessment →",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "✨ Tailored from $personalizedCount psychology & astrology assessment insights.",
                    style = MaterialTheme.typography.labelSmall,
                    color = NebulaTeal.copy(alpha = 0.9f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
