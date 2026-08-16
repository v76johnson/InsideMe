package com.example.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.data.model.UserSubscription
import com.example.data.model.ZodiacSign
import com.example.data.repository.NameAnalysisEngine
import com.example.data.repository.TestCatalog
import com.example.ui.components.DailyAffirmationWidget
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    testResults: List<TestResultEntity>,
    astrologyProfile: AstrologyProfile?,
    userSubscription: UserSubscription,
    onNavigateToAssessments: () -> Unit,
    onNavigateToAstrology: () -> Unit = {},
    onGenerateReportClicked: () -> Unit,
    onUpgradeClicked: () -> Unit,
    onOpenFreeMindChat: () -> Unit = {},
    onGenerateNameReport: (String) -> Unit = {},
    onOpenProfileSetup: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val totalTestsCount = TestCatalog.allTests.size
    val completedCount = testResults.size
    val completionPercentage = if (totalTestsCount > 0) ((completedCount.toFloat() / totalTestsCount) * 100).toInt() else 0
    val progressFraction = if (totalTestsCount > 0) (completedCount.toFloat() / totalTestsCount) else 0f

    val isConfigured = astrologyProfile != null && astrologyProfile.isConfigured && astrologyProfile.birthDateMillis > 0L
    val userName = astrologyProfile?.userName?.ifBlank { "Seeker" } ?: "Seeker"
    val sunSign = astrologyProfile?.sunSign ?: ZodiacSign.SCORPIO
    val moonSign = astrologyProfile?.moonSign ?: ZodiacSign.PISCES
    val risingSign = astrologyProfile?.risingSign ?: ZodiacSign.CANCER

    val nameAnalysis = remember(userName) {
        NameAnalysisEngine.analyzeName(userName)
    }
    val primaryMeaning = nameAnalysis.etymologies.firstOrNull()?.literalMeaning ?: "Luminous Essence & Strength"
    val primaryCulture = nameAnalysis.etymologies.firstOrNull()?.languageOrCulture ?: "Universal"

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpace),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp)
    ) {
        // 1. Personalized Greeting & Selected Name Meaning Box (Top)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                if (!isConfigured) {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, CelestialGold.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                            .clickable { onOpenProfileSetup() }
                            .testTag("personalized_greeting_card")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "WELCOME TO INSIDEME ✨",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = CelestialGold,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (userName != "Seeker") "Greetings, $userName" else "Personalize Your Journey",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Tap to enter birthdate & name to calculate your true signs and name essence",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = onOpenProfileSetup,
                                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                                modifier = Modifier.testTag("home_setup_profile_btn")
                            ) {
                                Text("✨ Setup", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }
                } else {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                            .testTag("personalized_greeting_card")
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "WELCOME BACK ✨",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = CelestialGold,
                                        letterSpacing = 1.sp
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Greetings, $userName",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                }

                                Button(
                                    onClick = { onGenerateNameReport(userName) },
                                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                    modifier = Modifier.testTag("home_name_meaning_report_btn")
                                ) {
                                    Icon(Icons.Default.HistoryEdu, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Name Meaning", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Selected Name Meaning Information
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MysticViolet.copy(alpha = 0.35f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(CircleShape)
                                            .background(CelestialGold.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.HistoryEdu,
                                            contentDescription = "Name Essence",
                                            tint = CelestialGold,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Name Meaning: $primaryMeaning",
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "$primaryCulture Root • Intent: ${nameAnalysis.parentalIntentCategory}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = NebulaTeal,
                                            fontSize = 11.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        // 2. Combined Zodiac Identity & Traits Box (Moved right under Welcome Box)
        item {
            if (!isConfigured) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                        .clickable { onOpenProfileSetup() }
                        .testTag("zodiac_and_traits_card")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(CelestialGold.copy(alpha = 0.2f))
                                .border(1.dp, CelestialGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = CelestialGold,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Zodiac Identity & Traits Blueprint 🔮",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "Enter your birthdate to calculate your exact Sun, Moon, and Rising signs and unlock your strengths and growth blueprint.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 11.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Surface(
                                color = CelestialGold,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.clickable { onOpenProfileSetup() }
                            ) {
                                Text(
                                    text = "✨ Set Birthdate & Calculate",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }
                }
            } else {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                        .testTag("zodiac_and_traits_card")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        // Header: Zodiac Signs & Placement Details
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(NebulaTeal.copy(alpha = 0.2f))
                                        .border(1.dp, NebulaTeal, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = sunSign.symbol,
                                        fontSize = 24.sp,
                                        color = NebulaTeal
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = "${sunSign.displayName} (${sunSign.element.displayName} Element)",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Sun in ${sunSign.displayName} • Moon in ${moonSign.displayName} • Rising in ${risingSign.displayName}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                }
                            }

                            Button(
                                onClick = onNavigateToAstrology,
                                colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = CelestialGold),
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.testTag("home_go_to_astrology_btn")
                            ) {
                                Text("Charts →", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Keywords FlowRow
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            sunSign.keywords.take(4).forEach { kw ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(MysticViolet.copy(alpha = 0.4f))
                                        .padding(horizontal = 7.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = kw,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = CelestialGold,
                                        fontSize = 10.sp,
                                        maxLines = 1,
                                        softWrap = false
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        HorizontalDivider(color = MysticViolet.copy(alpha = 0.4f))
                        Spacer(modifier = Modifier.height(14.dp))

                        // Good Traits Section
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = NebulaTeal, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Key Strengths & Good Traits",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = NebulaTeal
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        val trait1 = if (testResults.isNotEmpty()) "High Trait Alignment: ${testResults.first().dominantArchetype}" else "High Reflective Intelligence & Depth"
                        val trait2 = "Strong Empathetic Intuition (${sunSign.displayName} Placement)"
                        val trait3 = "Strategic Problem-Solving & Focus"

                        listOf(trait1, trait2, trait3).forEach { trait ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("✦ ", color = NebulaTeal, fontSize = 12.sp, modifier = Modifier.padding(top = 1.dp))
                                Text(
                                    text = trait,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.9f),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Areas Needing Work / Improvement Section
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.TrendingUp, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Key Things Needing Work / Growth Focus",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        val growth1 = "Stress Decompression & Recovery under heavy cognitive workloads"
                        val growth2 = "Reconciling inner emotional boundaries with social expectations"
                        val growth3 = "Translating reflective self-insights into concrete daily habits"

                        listOf(growth1, growth2, growth3).forEach { growth ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("👉 ", fontSize = 12.sp, modifier = Modifier.padding(top = 1.dp))
                                Text(
                                    text = growth,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.9f),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
        }



        // 4. Summary of Tests Taken & Scores Section
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Summary of Tests Taken & Scores",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Your completed assessment results and dominant scores",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (testResults.isNotEmpty()) {
                    val dateFormat = remember { SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) }

                    testResults.forEach { result ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.7f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .border(1.dp, MysticViolet.copy(alpha = 0.35f), RoundedCornerShape(16.dp))
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = result.testTitle,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(NebulaTeal.copy(alpha = 0.2f))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = dateFormat.format(Date(result.completedAtMillis)),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = NebulaTeal
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Psychology, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Dominant Trait: ${result.dominantArchetype}",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        color = CelestialGold
                                    )
                                }

                                if (result.summaryText.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = result.summaryText,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White.copy(alpha = 0.8f),
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MysticViolet.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.SelfImprovement, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(32.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No Assessments Taken Yet",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Take your first assessment to reveal your scores, traits, and personalized psychology profile!",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        // 5. Daily Psychology Affirmation Widget
        item {
            DailyAffirmationWidget(
                testResults = testResults,
                astrologyProfile = astrologyProfile,
                onTakeAssessmentClicked = onNavigateToAssessments,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))
        }

        // 6. Free AI Mind & Feelings Chat Card (Moved to bottom of home page)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .border(1.dp, NebulaTeal.copy(alpha = 0.7f), RoundedCornerShape(20.dp))
                    .clickable { onOpenFreeMindChat() }
                    .testTag("home_free_mind_chat_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(NebulaTeal.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Psychology,
                            contentDescription = null,
                            tint = NebulaTeal,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Free AI Mind Companion",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.weight(1f, fill = false),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = NebulaTeal,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "100% FREE",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 9.sp,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                                    maxLines = 1,
                                    softWrap = false
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Discuss assessment scores, feelings & mental health guidance",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 11.sp,
                            lineHeight = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Open Chat",
                        tint = NebulaTeal,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
