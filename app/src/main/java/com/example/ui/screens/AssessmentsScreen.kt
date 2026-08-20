package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.data.model.DeepSynthesisReport
import com.example.data.model.PsychologyTest
import com.example.data.model.TestCategory
import com.example.data.repository.TestCatalog
import com.example.ui.components.ProfessionalLocatorDialog
import com.example.ui.components.ReportExportDialog
import com.example.ui.components.ReportReaderView
import com.example.ui.components.TestCard
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal

@Composable
fun AssessmentsScreen(
    testResults: List<TestResultEntity>,
    savedReports: List<DeepSynthesisReport> = emptyList(),
    selectedReport: DeepSynthesisReport? = null,
    astrologyProfile: AstrologyProfile? = null,
    isGenerating: Boolean = false,
    isPremium: Boolean = false,
    gemsBalance: Int = 0,
    onStartTest: (PsychologyTest) -> Unit,
    onGenerateMetaReportClicked: () -> Unit = {},
    onSelectReport: (DeepSynthesisReport?) -> Unit = {},
    onToggleBookmark: (String, Boolean) -> Unit = { _, _ -> },
    onToggleHabit: (DeepSynthesisReport, Int) -> Unit = { _, _ -> },
    onOpenFreeMindChat: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showReportExportDialog by remember { mutableStateOf(false) }
    var reportToExport by remember { mutableStateOf<DeepSynthesisReport?>(null) }
    var showCareLocatorDialog by remember { mutableStateOf(false) }

    var isCrisisExpanded by remember { mutableStateOf(false) }

    if (selectedReport != null) {
        ReportReaderView(
            report = selectedReport,
            onBack = { onSelectReport(null) },
            onToggleBookmark = onToggleBookmark,
            onToggleHabit = onToggleHabit,
            testResults = testResults,
            astrologyProfile = astrologyProfile,
            savedReports = savedReports,
            modifier = modifier
        )
    } else {
        var collapsedCategories by remember { mutableStateOf(TestCategory.entries.toSet()) }

        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        val completedTestIds = remember(testResults) {
            testResults.map { it.testId }.toSet()
        }

        val totalTestsCount = TestCatalog.allTests.size
        val completedCount = testResults.size
        val completionPercentage = if (totalTestsCount > 0) ((completedCount.toFloat() / totalTestsCount) * 100).toInt() else 0
        val progressFraction = if (totalTestsCount > 0) (completedCount.toFloat() / totalTestsCount) else 0f

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(DeepSpace),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // Header Section: Title, Free AI Chat, Care Locator, Assessment Progress Box
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
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
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 1.5.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = CelestialGold,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Psychological Assessment Groups",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Select an assessment group to explore psychological evaluations labeled by what each test measures.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // 1. Free AI Mind & Feelings Chat Banner (Top)
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, NebulaTeal.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                            .clickable { onOpenFreeMindChat() }
                            .testTag("assessments_free_mind_chat_banner")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(NebulaTeal.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Psychology,
                                    contentDescription = null,
                                    tint = NebulaTeal,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Free AI Mind Chat",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = NebulaTeal,
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "100% FREE",
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.sp,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Discuss your test scores & feelings with our supportive AI companion",
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 2. Find Care AI Locator Box (Stacked under Free AI Chat)
                    val context = LocalContext.current
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, NebulaTeal.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                            .testTag("assessments_care_locator_card")
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
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
                                            .background(NebulaTeal.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.MedicalServices,
                                            contentDescription = null,
                                            tint = NebulaTeal,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = "Find Care AI & Therapist Locator",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Search licensed therapists, psychologists & clinics near you",
                                            color = Color.White.copy(alpha = 0.8f),
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = { showCareLocatorDialog = true },
                                colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = DeepSpace),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .testTag("assessments_open_care_locator_btn")
                            ) {
                                Icon(Icons.Default.MedicalServices, contentDescription = null, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Find Care AI Locator", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 3. Assessment Progress Box (Under Care Locator)
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, CelestialGold.copy(alpha = 0.45f), RoundedCornerShape(18.dp))
                            .testTag("assessments_progress_meter_card")
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
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
                                            .size(34.dp)
                                            .clip(CircleShape)
                                            .background(CelestialGold.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.BarChart,
                                            contentDescription = null,
                                            tint = CelestialGold,
                                            modifier = Modifier.size(18.dp)
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

                            Spacer(modifier = Modifier.height(10.dp))

                            LinearProgressIndicator(
                                progress = { progressFraction },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = NebulaTeal,
                                trackColor = MysticViolet.copy(alpha = 0.35f),
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = if (completedCount == 0) "Complete your first assessment below to reveal your psychological score profile!"
                                else if (completedCount < totalTestsCount) "Keep completing assessments to refine your personality blueprint and AI reports."
                                else "All assessments completed! You have unlocked your full multi-test meta-analysis.",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            // Grouped Assessment Categories (without horizontal scrolling filter)
            TestCategory.entries.forEach { category ->
                val categoryTests = TestCatalog.allTests.filter { it.category == category }
                if (categoryTests.isNotEmpty()) {
                    val isCollapsed = collapsedCategories.contains(category)

                    // Category Group Header Item
                    item(key = "group_header_${category.name}") {
                        val groupDoneCount = categoryTests.count { completedTestIds.contains(it.id) }
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.6f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 6.dp)
                                .border(1.dp, CelestialGold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                                .clickable {
                                    collapsedCategories = if (isCollapsed) {
                                        collapsedCategories - category
                                    } else {
                                        collapsedCategories + category
                                    }
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                    Box(
                                        modifier = Modifier
                                            .size(34.dp)
                                            .clip(CircleShape)
                                            .background(CelestialGold.copy(alpha = 0.2f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Psychology,
                                            contentDescription = null,
                                            tint = CelestialGold,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = category.displayName,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = CelestialGold
                                        )
                                        Text(
                                            text = "${categoryTests.size} Assessment${if (categoryTests.size > 1) "s" else ""} • ${categoryTests.sumOf { it.questions.size }} Questions",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.White.copy(alpha = 0.7f)
                                        )
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (groupDoneCount > 0) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(NebulaTeal.copy(alpha = 0.25f))
                                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = "Completed ✓",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = NebulaTeal
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                    }
                                    Icon(
                                        imageVector = if (isCollapsed) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                                        contentDescription = if (isCollapsed) "Expand category" else "Collapse category",
                                        tint = CelestialGold,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Items under this Category Group (only shown if not collapsed)
                    if (!isCollapsed) {
                        if (isLandscape) {
                            items(categoryTests.chunked(2), key = { chunk -> "test_chunk_${chunk.first().id}" }) { chunk ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp, vertical = 6.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    chunk.forEach { test ->
                                        val isDone = completedTestIds.contains(test.id)
                                        TestCard(
                                            test = test,
                                            isCompleted = isDone,
                                            onStartClicked = { onStartTest(test) },
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                    if (chunk.size == 1) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        } else {
                            items(categoryTests, key = { "test_${it.id}" }) { test ->
                                val isDone = completedTestIds.contains(test.id)
                                TestCard(
                                    test = test,
                                    isCompleted = isDone,
                                    onStartClicked = { onStartTest(test) },
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    item(key = "group_spacer_${category.name}") {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            // Feature Section at Bottom of Assessment Page: Analyze All Personal Reports Together
            item(key = "meta_analysis_section") {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.85f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .border(1.5.dp, CelestialGold, RoundedCornerShape(24.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Psychology, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "MULTI-REPORT META-ANALYSIS",
                                style = MaterialTheme.typography.labelMedium,
                                color = CelestialGold,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Analyze All Personal Reports Together",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Synthesizes all saved personal reports & assessment results together to discover overarching trait convergences, reconciled shadow insights, and what they mean as a unified blueprint.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (isGenerating) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = NebulaTeal)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Cross-analyzing all personal reports...", color = NebulaTeal, fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Button(
                                onClick = onGenerateMetaReportClicked,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NebulaTeal,
                                    contentColor = DeepSpace
                                ),
                                shape = RoundedCornerShape(14.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("analyze_all_reports_together_button")
                            ) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = when {
                                        isPremium -> "Analyze All Reports (Psyche+ Included)"
                                        gemsBalance >= 10 -> "Analyze All Reports (1 Credit Ready)"
                                        else -> "Analyze All Reports ($1.00 or Sub)"
                                    },
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Saved Meta-Analysis Reports List (if any exist)
            val metaReports = savedReports.filter { it.archetypeSummary.contains("Meta-Analysis") || it.title.contains("Master") }
            if (metaReports.isNotEmpty()) {
                item(key = "meta_reports_header") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Saved Meta-Analysis Reports (${metaReports.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = "Export All",
                            style = MaterialTheme.typography.labelMedium,
                            color = CelestialGold,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
                                    reportToExport = null
                                    showReportExportDialog = true
                                }
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }

                items(metaReports, key = { "meta_report_${it.id}" }) { report ->
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 6.dp)
                            .border(1.5.dp, CelestialGold, RoundedCornerShape(18.dp))
                            .clickable { onSelectReport(report) }
                            .testTag("saved_meta_report_card_${report.id}")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(CelestialGold.copy(alpha = 0.2f))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "✨ MASTER META-ANALYSIS",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = CelestialGold,
                                        fontSize = 10.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = report.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = report.archetypeSummary,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            }

                            Row {
                                val localContext = LocalContext.current
                                IconButton(onClick = {
                                    val shareIntent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, "✨ ${report.title}\n\n${report.archetypeSummary}")
                                    }
                                    localContext.startActivity(Intent.createChooser(shareIntent, "Share Report"))
                                }) {
                                    Icon(Icons.Default.Share, contentDescription = "Share Report", tint = CelestialGold)
                                }

                                IconButton(onClick = {
                                    reportToExport = report
                                    showReportExportDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.CloudUpload,
                                        contentDescription = "Export Report",
                                        tint = CelestialGold
                                    )
                                }

                                IconButton(onClick = { onToggleBookmark(report.id, report.isBookmarked) }) {
                                    Icon(
                                        imageVector = if (report.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                        contentDescription = "Bookmark",
                                        tint = CelestialGold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showReportExportDialog) {
        ReportExportDialog(
            reports = savedReports,
            initialSelectedReport = reportToExport,
            onDismiss = {
                showReportExportDialog = false
                reportToExport = null
            }
        )
    }

    if (showCareLocatorDialog) {
        ProfessionalLocatorDialog(
            onDismiss = { showCareLocatorDialog = false }
        )
    }
}
