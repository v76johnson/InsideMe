package com.example.ui.screens

import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Shield
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.data.model.SubscriptionTier
import com.example.data.model.UserSubscription
import com.example.ui.components.ProfessionalLocatorDialog
import com.example.ui.components.RawDataExportDialog
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal

@Composable
fun PremiumScreen(
    userSubscription: UserSubscription,
    onSetSubscriptionTier: (SubscriptionTier) -> Unit,
    onPurchaseSingleReport: () -> Unit = {},
    onOpenReview: () -> Unit = {},
    testResults: List<TestResultEntity> = emptyList(),
    astrologyProfile: AstrologyProfile? = null,
    modifier: Modifier = Modifier
) {
    var selectedTier by remember { mutableStateOf(SubscriptionTier.ANNUAL_PRO) }
    var showRawDataExportDialog by remember { mutableStateOf(false) }
    var showCareLocatorDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpace)
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 20.dp, bottom = 90.dp)
    ) {
        // Top Banner Header
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(CelestialGold.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(36.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Psyche+ Celestial Premium",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Get individual premium AI synthesis reports for $1 each, or subscribe for unlimited access.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.75f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Review Reward Station Card
        item {
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, CelestialGold, RoundedCornerShape(22.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f, fill = false)
                        ) {
                            Icon(Icons.Default.RateReview, contentDescription = null, tint = CelestialGold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "1 Free Report for Review",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(CelestialGold)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = if (userSubscription.hasClaimedReviewBonus) "CLAIMED" else "+1 FREE REPORT",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = DeepSpace,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (userSubscription.hasClaimedReviewBonus)
                            "Thank you for reviewing Psyche! 1 Free AI Report Credit has been added to your account."
                        else
                            "Leave feedback to instantly claim +1 Free Premium AI Synthesis Report Credit.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.85f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onOpenReview,
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
                            .height(48.dp)
                            .testTag("premium_give_review_button")
                    ) {
                        Icon(Icons.Default.RateReview, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (userSubscription.hasClaimedReviewBonus) "Review Reward Claimed ✓" else "Leave a Review (+1 Free Report)",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Single Report Purchase Option ($1.00)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, NebulaTeal, RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f, fill = false)
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = NebulaTeal)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Single Premium Report",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(NebulaTeal)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$1.00 / REPORT",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Don't want a recurring subscription? Purchase a single full AI synthesis report for just $1.00.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.85f)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            onPurchaseSingleReport()
                            Toast.makeText(context, "1 Report Credit Purchased ($1.00)!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = Color.Black),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("buy_single_report_button")
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Purchase 1 Report ($1.00)", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Feature Comparison Checklist (Psyche+ Premium Benefits)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MysticViolet.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Psyche+ Premium Benefits",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    listOf(
                        "✨ 100% Ad-Free Clean Experience & Zero Interruption",
                        "🧠 Unlimited Gemini AI Deep Synthesis Reports ($1/report for non-subscribers)",
                        "💖 Birthdate Synastry & Inter-Chart Compatibility Matching",
                        "🔮 AI Astrological Oracle & Unlimited Natal Chart Analysis",
                        "🔢 Life Path Numerology & Chinese Zodiac Calculator",
                        "📊 Multi-Report Meta-Analysis (Synthesize All Saved Tests)",
                        "📅 7-Day Actionable Micro-Habit Blueprint Tracking",
                        "📥 Export Synthesis Reports as Text or PDF Documents"
                    ).forEach { feature ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = NebulaTeal, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = feature, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.9f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Access Tiers (Subscriptions)
        item {
            Text(
                text = "Unlimited Subscriptions",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Monthly Tier
            SubscriptionTierCard(
                tier = SubscriptionTier.MONTHLY_PRO,
                isSelected = (selectedTier == SubscriptionTier.MONTHLY_PRO),
                isActive = (userSubscription.tier == SubscriptionTier.MONTHLY_PRO),
                badgeText = "50% OFF SALE",
                onSelect = { selectedTier = SubscriptionTier.MONTHLY_PRO }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Annual Tier (Best Value)
            SubscriptionTierCard(
                tier = SubscriptionTier.ANNUAL_PRO,
                isSelected = (selectedTier == SubscriptionTier.ANNUAL_PRO),
                isActive = (userSubscription.tier == SubscriptionTier.ANNUAL_PRO),
                badgeText = "BEST VALUE • POPULAR",
                onSelect = { selectedTier = SubscriptionTier.ANNUAL_PRO }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { onSetSubscriptionTier(selectedTier) },
                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("subscribe_tier_button")
            ) {
                Text(
                    text = if (userSubscription.isPremium) "Switch Subscription Tier" else "Subscribe (${selectedTier.priceDisplay})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            if (userSubscription.isPremium) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onSetSubscriptionTier(SubscriptionTier.FREE) },
                    colors = ButtonDefaults.buttonColors(containerColor = CosmicPurple, contentColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("downgrade_free_button")
                ) {
                    Text("Switch Back to Free Tier")
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }

        // Cancel Subscription Section
        item {
            var showCancelConfirmDialog by remember { mutableStateOf(false) }
            val context = LocalContext.current

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFEF5350).copy(alpha = 0.4f), RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f, fill = false)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = null,
                                tint = Color(0xFFEF5350)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Subscription Management",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (userSubscription.isPremium) NebulaTeal else Color.Gray.copy(alpha = 0.3f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = if (userSubscription.isPremium) userSubscription.tier.title.uppercase() else "FREE TIER",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (userSubscription.isPremium) Color.Black else Color.White,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (userSubscription.isPremium)
                            "You are currently subscribed to ${userSubscription.tier.title} (${userSubscription.tier.priceDisplay}). You can cancel your subscription at any time."
                        else
                            "You are currently on the Free Tier. Upgrading unlocks 100% ad-free unlimited reports and deep psychological synthesis.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (userSubscription.isPremium) {
                        OutlinedButton(
                            onClick = { showCancelConfirmDialog = true },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFFEF5350)
                            ),
                            border = BorderStroke(1.dp, Color(0xFFEF5350)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("cancel_subscription_button")
                        ) {
                            Icon(Icons.Default.Cancel, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Cancel Active Subscription", fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Button(
                            onClick = {
                                Toast.makeText(context, "You are currently on the Free Tier with no active paid subscription.", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CosmicPurple.copy(alpha = 0.6f),
                                contentColor = Color.White.copy(alpha = 0.6f)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("cancel_subscription_free_button")
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("No Active Subscription to Cancel", fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }

            if (showCancelConfirmDialog) {
                AlertDialog(
                    onDismissRequest = { showCancelConfirmDialog = false },
                    containerColor = CosmicPurple,
                    titleContentColor = Color.White,
                    textContentColor = Color.White.copy(alpha = 0.9f),
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Warning, contentDescription = null, tint = Color(0xFFEF5350))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Cancel Subscription?", fontWeight = FontWeight.Bold)
                        }
                    },
                    text = {
                        Text("Are you sure you want to cancel your ${userSubscription.tier.title} subscription? You will be reverted to the Free Tier and will no longer have unlimited AI synthesis.")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                onSetSubscriptionTier(SubscriptionTier.FREE)
                                showCancelConfirmDialog = false
                                Toast.makeText(context, "Subscription canceled. Reverted to Free Tier.", Toast.LENGTH_LONG).show()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350), contentColor = Color.White)
                        ) {
                            Text("Yes, Cancel Plan", fontWeight = FontWeight.Bold)
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showCancelConfirmDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White)
                        ) {
                            Text("Keep Subscription")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Contact & Feedback Section
        item {
            var showFeedbackDialog by remember { mutableStateOf(false) }
            var feedbackText by remember { mutableStateOf("") }
            var selectedCategory by remember { mutableStateOf("General Feedback") }
            val context = LocalContext.current

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, CelestialGold.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Feedback, contentDescription = null, tint = CelestialGold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Contact & Feedback",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Have questions, feature requests, or technical issues? We read every piece of feedback to improve Psyche.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (isLandscape) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { showFeedbackDialog = true },
                                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .testTag("send_feedback_button")
                            ) {
                                Icon(Icons.Default.Feedback, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Send Feedback", fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("mailto:support@psycheapp.com")
                                        putExtra(Intent.EXTRA_SUBJECT, "Psyche App Support & Inquiry")
                                    }
                                    try {
                                        context.startActivity(intent)
                                    } catch (_: Exception) {
                                        Toast.makeText(context, "Support email: support@psycheapp.com", Toast.LENGTH_LONG).show()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .testTag("email_support_button")
                            ) {
                                Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Email Support", fontWeight = FontWeight.Bold)
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { showFeedbackDialog = true },
                                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("send_feedback_button")
                            ) {
                                Icon(Icons.Default.Feedback, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Send Feedback", fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("mailto:support@psycheapp.com")
                                        putExtra(Intent.EXTRA_SUBJECT, "Psyche App Support & Inquiry")
                                    }
                                    try {
                                        context.startActivity(intent)
                                    } catch (_: Exception) {
                                        Toast.makeText(context, "Support email: support@psycheapp.com", Toast.LENGTH_LONG).show()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("email_support_button")
                            ) {
                                Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Email Support", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            if (showFeedbackDialog) {
                AlertDialog(
                    onDismissRequest = { showFeedbackDialog = false },
                    containerColor = CosmicPurple,
                    titleContentColor = Color.White,
                    textContentColor = Color.White,
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Feedback, contentDescription = null, tint = CelestialGold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Send Feedback", fontWeight = FontWeight.Bold)
                        }
                    },
                    text = {
                        Column {
                            Text(
                                "Select category:",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                listOf("General", "Bug Report", "Feature").forEach { cat ->
                                    val isCatSelected = (selectedCategory == cat)
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (isCatSelected) CelestialGold else MysticViolet.copy(alpha = 0.5f))
                                            .clickable { selectedCategory = cat }
                                            .padding(horizontal = 8.dp, vertical = 6.dp)
                                    ) {
                                        Text(
                                            text = cat,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isCatSelected) DeepSpace else Color.White
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            OutlinedTextField(
                                value = feedbackText,
                                onValueChange = { feedbackText = it },
                                label = { Text("Your Message", color = Color.White.copy(alpha = 0.7f)) },
                                placeholder = { Text("Tell us what you love or how we can improve...", color = Color.White.copy(alpha = 0.4f)) },
                                minLines = 3,
                                maxLines = 5,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = CelestialGold,
                                    unfocusedBorderColor = MysticViolet,
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                if (feedbackText.isNotBlank()) {
                                    Toast.makeText(context, "Thank you! Your feedback has been sent.", Toast.LENGTH_LONG).show()
                                    feedbackText = ""
                                    showFeedbackDialog = false
                                } else {
                                    Toast.makeText(context, "Please enter a message before submitting.", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace)
                        ) {
                            Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Submit", fontWeight = FontWeight.Bold)
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showFeedbackDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White)
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Professional Care, Raw Data Export & Settings Disclaimer
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.MedicalServices, contentDescription = null, tint = CelestialGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Professional Resources & Raw Data",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Access tools to connect with verified care professionals or download your raw psychological test records.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (isLandscape) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { showCareLocatorDialog = true },
                                colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = Color.Black),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .testTag("locate_professional_button")
                            ) {
                                Icon(Icons.Default.MedicalServices, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Locate Care", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = { showRawDataExportDialog = true },
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, CelestialGold),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .testTag("download_raw_data_button")
                            ) {
                                Icon(Icons.Default.Download, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Export Data", color = CelestialGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { showCareLocatorDialog = true },
                                colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = Color.Black),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("locate_professional_button")
                            ) {
                                Icon(Icons.Default.MedicalServices, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Locate Care", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = { showRawDataExportDialog = true },
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, CelestialGold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("download_raw_data_button")
                            ) {
                                Icon(Icons.Default.Download, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Export Data", color = CelestialGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // MANDATORY DISCLAIMER
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(DeepSpace.copy(alpha = 0.8f))
                            .border(1.dp, MysticViolet.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                            .padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(Icons.Default.Shield, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "*This app is intended for entertainment and educational purposes and that results should be discussed with a professional for diagnosis and treatment.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (showRawDataExportDialog) {
        RawDataExportDialog(
            testResults = testResults,
            astrologyProfile = astrologyProfile,
            onDismiss = { showRawDataExportDialog = false }
        )
    }

    if (showCareLocatorDialog) {
        ProfessionalLocatorDialog(
            onDismiss = { showCareLocatorDialog = false }
        )
    }
}

@Composable
fun SubscriptionTierCard(
    tier: SubscriptionTier,
    isSelected: Boolean,
    isActive: Boolean,
    badgeText: String? = null,
    onSelect: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MysticViolet.copy(alpha = 0.35f) else CosmicPurple
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) CelestialGold else MysticViolet.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onSelect() }
            .testTag("tier_card_${tier.name}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = tier.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.weight(1f, fill = false),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (badgeText != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(CelestialGold)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badgeText,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                maxLines = 1,
                                softWrap = false,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (tier.regularPriceDisplay != null) {
                        Text(
                            text = tier.regularPriceDisplay,
                            style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough),
                            color = Color.White.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = "${tier.priceDisplay} ${tier.billingPeriod}",
                        style = MaterialTheme.typography.bodySmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (isActive) {
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(NebulaTeal)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "ACTIVE",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1,
                        softWrap = false
                    )
                }
            }
        }
    }
}
