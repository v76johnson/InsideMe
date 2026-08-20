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
import com.example.ui.components.DailyHoroscopeWidget
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
    onOpenProfileSetup: () -> Unit = {},
    onOpenNameMeaning: (String) -> Unit = {},
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

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpace),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp)
    ) {
        // 1. Personalized Greeting Box (Top)
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Text(
                                text = "WELCOME TO INSIDEME ✨",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = if (userName != "Seeker") "Greetings, $userName" else "Personalize Your Journey",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Enter your birthdate & name to calculate your exact Big 3 signs, daily horoscope, and personal astrology blueprint.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f),
                                lineHeight = 18.sp
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            Button(
                                onClick = onOpenProfileSetup,
                                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("home_setup_profile_btn")
                            ) {
                                Text("✨ Setup Profile & Birthdate", fontWeight = FontWeight.Bold, fontSize = 13.sp)
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
                            Column {
                                Text(
                                    text = "DAILY INSIGHTS ✨",
                                    style = MaterialTheme.typography.titleMedium,
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

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Your cosmic transits, psychological profile, and personalized daily horoscope are ready below.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f),
                                lineHeight = 18.sp
                            )

                            if (userName != "Seeker") {
                                val nameReport = remember(userName) { NameAnalysisEngine.analyzeName(userName) }
                                val primaryEty = nameReport.etymologies.firstOrNull()

                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(DeepSpace.copy(alpha = 0.6f))
                                        .border(1.dp, CelestialGold.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                        .padding(10.dp)
                                ) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(14.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "Name Essence • ${primaryEty?.languageOrCulture ?: "Origin"}",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = CelestialGold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "\"${primaryEty?.literalMeaning ?: ""}\" • ${nameReport.numerologicalVibration}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.White.copy(alpha = 0.9f),
                                            fontSize = 11.sp,
                                            lineHeight = 15.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                androidx.compose.material3.OutlinedButton(
                                    onClick = { onOpenNameMeaning(userName) },
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = CelestialGold),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold.copy(alpha = 0.6f)),
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("detailed_name_search_btn")
                                ) {
                                    Icon(Icons.Default.HistoryEdu, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("✨ Detailed AI Search (Origins, Meanings & Famous Bearers)", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        // 3. Daily Horoscope Widget
        item {
            DailyHoroscopeWidget(
                astrologyProfile = astrologyProfile,
                onNavigateToAstrology = onNavigateToAstrology,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))
        }

        // 4. Daily Psychology Affirmation Widget (Moved above Summary of Tests)
        item {
            DailyAffirmationWidget(
                testResults = testResults,
                astrologyProfile = astrologyProfile,
                onTakeAssessmentClicked = onNavigateToAssessments,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))
        }

        // 5. Summary of Tests Taken & Scores Section
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

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
