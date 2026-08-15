package com.example.ui.screens

import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.data.model.UserSubscription
import com.example.data.model.ZodiacSign
import com.example.data.repository.TestCatalog
import com.example.ui.components.DailyAffirmationWidget
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.Spacing
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    modifier: Modifier = Modifier
) {
    val totalTestsCount = TestCatalog.allTests.size
    val completedCount = testResults.size
    val completionPercentage = if (totalTestsCount > 0) ((completedCount.toFloat() / totalTestsCount) * 100).toInt() else 0
    val progressFraction = if (totalTestsCount > 0) (completedCount.toFloat() / totalTestsCount) else 0f

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
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // App Title Top Header & Personal Greeting
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.pagePadding, vertical = Spacing.medium)
            ) {
                // Top Center App Name: InsideMe
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = CelestialGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "InsideMe",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 1.5.sp,
                        modifier = Modifier.testTag("app_title_top_center")
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = CelestialGold,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(Spacing.large))

                // Personalized Greeting Card
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                        .testTag("personalized_greeting_card")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spacing.cardContent),
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
                            Text(
                                text = "Greetings, $userName",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(Spacing.small))
                            Text(
                                text = "$sunSign Sun • $moonSign Moon • $risingSign Rising",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Button(
                            onClick = { onGenerateNameReport(userName) },
                            colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("home_name_meaning_report_btn")
                        ) {
                            Text("📜 Name Report", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.small))
        }

        // Progress Meter Card (Percentage of tests completed)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.pagePadding)
                    .border(1.dp, CelestialGold.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                    .testTag("progress_meter_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.cardContent)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(CelestialGold.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.BarChart,
                                    contentDescription = null,
                                    tint = CelestialGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "ASSESSMENT PROGRESS METER",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = CelestialGold,
                                    letterSpacing = 0.8.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "$completedCount of $totalTestsCount Completed",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White.copy(alpha = 0.85f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(MysticViolet)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$completionPercentage%",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = CelestialGold,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(Spacing.medium))

                    // Styled Progress Bar
                    LinearProgressIndicator(
                        progress = { progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        color = NebulaTeal,
                        trackColor = MysticViolet.copy(alpha = 0.35f),
                    )

                    Spacer(modifier = Modifier.height(Spacing.small))

                    Text(
                        text = if (completedCount == 0) "Complete your first assessment to begin building your deep psychological score profile!"
                        else if (completedCount < totalTestsCount) "Keep completing assessments to refine your personality blueprint and AI reports."
                        else "All assessments completed! You have unlocked your full multi-test meta-analysis.",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.7f),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))
        }

        // Free AI Mind & Feelings Chat Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.pagePadding)
                    .border(1.dp, NebulaTeal.copy(alpha = 0.7f), RoundedCornerShape(20.dp))
                    .clickable { onOpenFreeMindChat() }
                    .testTag("home_free_mind_chat_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.cardContent),
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Free AI Mind Companion",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
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
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "Discuss assessment scores, feelings & mental health guidance",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 11.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
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

            Spacer(modifier = Modifier.height(Spacing.large))
        }

        // Quick Navigation Links (Assessments & Astrology) under Progress Bar
        item {
            if (isLandscape) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spacing.pagePadding),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Link to Assessments Page
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MysticViolet),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onNavigateToAssessments() }
                            .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                            .testTag("quick_link_assessments")
                    ) {
                        Row(
                            modifier = Modifier.padding(Spacing.cardContent),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = "Assessments Page",
                                tint = CelestialGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Assessments",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    // Link to Astrology Page
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onNavigateToAstrology() }
                            .border(1.dp, NebulaTeal.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                            .testTag("quick_link_astrology")
                    ) {
                        Row(
                            modifier = Modifier.padding(Spacing.cardContent),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Astrology Page",
                                tint = NebulaTeal,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Astrology",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spacing.pagePadding),
                    verticalArrangement = Arrangement.spacedBy(Spacing.small)
                ) {
                    // Link to Assessments Page
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MysticViolet),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToAssessments() }
                            .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                            .testTag("quick_link_assessments")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacing.cardContent),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = "Assessments Page",
                                tint = CelestialGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Assessments",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    // Link to Astrology Page
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToAstrology() }
                            .border(1.dp, NebulaTeal.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                            .testTag("quick_link_astrology")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacing.cardContent),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Astrology Page",
                                tint = NebulaTeal,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Astrology",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))
        }

        // Daily Psychology Affirmation Widget
        item {
            DailyAffirmationWidget(
                testResults = testResults,
                astrologyProfile = astrologyProfile,
                onTakeAssessmentClicked = onNavigateToAssessments,
                modifier = Modifier.padding(horizontal = Spacing.pagePadding)
            )

            Spacer(modifier = Modifier.height(Spacing.large))
        }

        // Zodiac Sign Card (Once Available)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.85f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.pagePadding)
                    .border(1.dp, MysticViolet.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                    .testTag("zodiac_sign_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.cardContent),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(NebulaTeal.copy(alpha = 0.2f))
                            .border(1.dp, NebulaTeal, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = sunSign.symbol,
                            fontSize = 28.sp,
                            color = NebulaTeal
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${sunSign.displayName} (${sunSign.element.displayName} Element)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Text(
                            text = "Sun in ${sunSign.displayName} • Moon in ${moonSign.displayName} • Rising in ${risingSign.displayName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

We need to complete content; file too long; ensure create_or_update_file was called earlier? The last create_or_update_file call earlier was for DailyAffirmation and TestCard. Now we need to update HomeScreen; we prepared content in the call — but the create_or_update_file tool call hasn't been made yet. In previous assistant commentary, we called create_or_update_file? We haven't. The user requested 