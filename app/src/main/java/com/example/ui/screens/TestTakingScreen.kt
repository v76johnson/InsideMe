package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.ui.text.style.TextDecoration
import com.example.data.model.Choice
import com.example.data.model.SubscriptionTier
import com.example.data.model.UserSubscription
import com.example.data.viewmodel.TestState
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal

@Composable
fun TestTakingScreen(
    testState: TestState,
    userSubscription: UserSubscription = UserSubscription(),
    onAnswerSelected: (Choice) -> Unit,
    onExitClicked: () -> Unit,
    onGenerateReportClicked: () -> Unit,
    onPurchaseSingleReport: () -> Unit = {},
    onSubscribeClicked: (SubscriptionTier) -> Unit = {},
    onOpenReviewClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val active = testState.activeTest ?: return
    var selectedChoice by remember(testState.currentQuestionIndex) { mutableStateOf<Choice?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpace)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Nav Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onExitClicked,
                modifier = Modifier.testTag("exit_test_button")
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = active.category.displayName,
                    style = MaterialTheme.typography.labelSmall,
                    color = CelestialGold,
                    fontWeight = FontWeight.Bold
                )
                val headingText = if (active.testsForLabel.isNotBlank()) active.testsForLabel else active.title
                Text(
                    text = headingText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (!testState.isCompleted) {
            val qIndex = testState.currentQuestionIndex
            val totalQ = active.questions.size
            val progress = (qIndex + 1) / totalQ.toFloat()
            val currentQ = active.questions[qIndex]

            // Progress Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${qIndex + 1} of $totalQ",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = CelestialGold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = CelestialGold,
                trackColor = CosmicPurple
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Question Text
            Text(
                text = currentQ.text,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                lineHeight = 32.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Choice Options Cards
            currentQ.choices.forEachIndexed { choiceIdx, choice ->
                val isSelected = (selectedChoice == choice)
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MysticViolet.copy(alpha = 0.35f) else CosmicPurple
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .border(
                            width = 1.5.dp,
                            color = if (isSelected) CelestialGold else MysticViolet.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { selectedChoice = choice }
                        .testTag("test_choice_${choiceIdx}")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { selectedChoice = choice },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = CelestialGold,
                                unselectedColor = Color.White.copy(alpha = 0.5f)
                            )
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = choice.text,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    selectedChoice?.let { choice ->
                        onAnswerSelected(choice)
                        selectedChoice = null
                    }
                },
                enabled = (selectedChoice != null),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CelestialGold,
                    contentColor = DeepSpace,
                    disabledContainerColor = CosmicPurple,
                    disabledContentColor = Color.White.copy(alpha = 0.3f)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("submit_answer_button")
            ) {
                Text(
                    text = if (qIndex + 1 == totalQ) "Complete Assessment" else "Next Question",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            // Calculate Score & Initial Summary
            val choices = testState.selectedChoices
            var userScore = 0
            var maxPossibleScore = 0
            val traitScores = mutableMapOf<String, Int>()

            choices.forEach { choice ->
                userScore += choice.weight
                traitScores[choice.traitKey] = traitScores.getOrDefault(choice.traitKey, 0) + choice.weight
            }

            active.questions.forEach { q ->
                val maxWeight = q.choices.maxOfOrNull { it.weight } ?: 3
                maxPossibleScore += maxWeight
            }

            val scorePercentage = if (maxPossibleScore > 0) ((userScore.toFloat() / maxPossibleScore) * 100).toInt() else 85
            val dominantTrait = traitScores.maxByOrNull { it.value }?.key ?: "Balanced Alignment"

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(NebulaTeal.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = NebulaTeal,
                        modifier = Modifier.size(44.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${active.title} Completed!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // INITIAL SCORE & SUMMARY CARD
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(22.dp))
                        .testTag("initial_results_score_card")
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = CelestialGold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "INITIAL SCORE & DIAGNOSIS",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = CelestialGold,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(CelestialGold)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "$scorePercentage% Score",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepSpace
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = "Dominant Trait Archetype",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.6f)
                                )
                                Text(
                                    text = dominantTrait,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Text(
                                text = "$userScore / $maxPossibleScore pts",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Trait score breakdown bars
                        Text(
                            text = "Measured Trait Breakdown",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        traitScores.forEach { (trait, pts) ->
                            val traitMax = active.questions.count { q -> q.choices.any { it.traitKey == trait } } * 3
                            val traitPercent = if (traitMax > 0) ((pts.toFloat() / traitMax) * 100).coerceAtMost(100f).toInt() else 75

                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = trait, style = MaterialTheme.typography.bodySmall, color = Color.White)
                                    Text(text = "$traitPercent%", style = MaterialTheme.typography.bodySmall, color = CelestialGold, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                LinearProgressIndicator(
                                    progress = { traitPercent / 100f },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(3.dp)),
                                    color = NebulaTeal,
                                    trackColor = DeepSpace
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Basic Explanation & What It Indicates
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(DeepSpace)
                                .padding(14.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Score Explanation",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = CelestialGold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Your score of $scorePercentage% ($userScore/$maxPossibleScore pts) reflects your response profile on the ${active.title}. Your dominant orientation is '$dominantTrait', indicating specific psychological preferences across measured dimensions.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.85f),
                                    lineHeight = 18.sp
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "What This Score Indicates",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = NebulaTeal
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "• Primary Focus: Strong resonance with $dominantTrait principles.\n" +
                                            "• Behavioral Style: Consistent tendencies in decision-making and stress response.\n" +
                                            "• Potential Growth Path: Developing secondary balance across lower-scoring dimensions to foster well-rounded adaptability.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.85f),
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // FULL REPORT MONETIZATION & UNLOCK OPTIONS
                Text(
                    text = "Get Complete Deep Synthesis Report",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Includes full 7-section analysis, Shadow Work insights, Career Purpose Playbook & 7-Day Micro-Habits Plan.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 1. FREE REPORT FOR REVIEW CARD
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, CelestialGold, RoundedCornerShape(18.dp))
                        .testTag("review_reward_option_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.RateReview, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "1 Free Report for Review",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(CelestialGold)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = if (userSubscription.hasClaimedReviewBonus) "CLAIMED" else "FREE BONUS",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepSpace
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = if (userSubscription.hasClaimedReviewBonus)
                                "You have claimed your free report reward for reviewing the app!"
                            else
                                "Leave a quick review to instantly earn 1 Free Full AI Synthesis Report credit.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = onOpenReviewClicked,
                            enabled = !userSubscription.hasClaimedReviewBonus,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CelestialGold,
                                contentColor = DeepSpace,
                                disabledContainerColor = CosmicPurple.copy(alpha = 0.6f),
                                disabledContentColor = Color.White.copy(alpha = 0.5f)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("give_review_for_report_button")
                        ) {
                            Icon(Icons.Default.RateReview, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (userSubscription.hasClaimedReviewBonus) "Review Reward Claimed ✓" else "Write a Review (+1 Free Report)",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 2. ONE-TIME FULL ACCESS ($9.99 regular -> $4.99 HALF PRICE SALE)
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, NebulaTeal, RoundedCornerShape(18.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = NebulaTeal, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "One-Time Full Access",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "$9.99",
                                    style = MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.LineThrough),
                                    color = Color.White.copy(alpha = 0.5f)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "$4.99",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = NebulaTeal
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(NebulaTeal.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "🔥 50% OFF HALF-PRICE SALE",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = NebulaTeal
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Get one-time full report purchase with unlimited report access across all personal assessments.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = onPurchaseSingleReport,
                            colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = DeepSpace),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("purchase_single_report_button")
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Buy Full Access ($4.99 - 50% OFF)", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 3. Subscription Option Card
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CelestialGold.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.WorkspacePremium, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Psyche+ Subscription",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "$9.99",
                                    style = MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.LineThrough),
                                    color = Color.White.copy(alpha = 0.5f)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "$4.99/mo",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = CelestialGold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(CelestialGold)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "🔥 50% OFF SALE • UNLIMITED",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = DeepSpace
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Unlimited report access across all assessments and full Psyche+ access ($4.99/mo, reg. $9.99/mo).",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { onSubscribeClicked(SubscriptionTier.MONTHLY_PRO) },
                            colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("subscribe_unlimited_reports_button")
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Subscribe ($4.99/mo - 50% OFF)", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Purchase Single Report ($1.00) Option Card
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, NebulaTeal.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Purchase Single Report ($1.00)",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Unlock this full report instantly without a subscription.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = onPurchaseSingleReport,
                            colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = Color.Black),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("buy_report_single_button")
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Buy ($1.00)", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Generate Report Button
                Spacer(modifier = Modifier.height(16.dp))
                val canGenerate = userSubscription.isPremium || userSubscription.gemsBalance >= 10
                Button(
                    onClick = {
                        if (canGenerate) {
                            onGenerateReportClicked()
                        } else {
                            onPurchaseSingleReport()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (canGenerate) CelestialGold else NebulaTeal,
                        contentColor = if (canGenerate) DeepSpace else Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("generate_unlocked_report_button")
                ) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when {
                            userSubscription.isPremium -> "Synthesize Full Report (Psyche+ Included)"
                            userSubscription.gemsBalance >= 10 -> "Synthesize Full Report (1 Credit Ready)"
                            else -> "Order Report ($1.00 or Psyche+ Sub)"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onExitClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = CosmicPurple, contentColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Return to Tests Library")
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
