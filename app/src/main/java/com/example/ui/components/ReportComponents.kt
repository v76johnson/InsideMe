package com.example.ui.components

import android.content.Intent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DeepSynthesisReport
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.ShadowRose

import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Shield
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.ui.components.CollapsibleBlock
import com.example.ui.components.ProfessionalLocatorDialog
import com.example.ui.components.RawDataExportDialog
import com.example.ui.components.ReportExportDialog

@Composable
fun ReportReaderView(
    report: DeepSynthesisReport,
    onBack: () -> Unit,
    onToggleBookmark: (String, Boolean) -> Unit,
    onToggleHabit: (DeepSynthesisReport, Int) -> Unit,
    testResults: List<TestResultEntity> = emptyList(),
    astrologyProfile: AstrologyProfile? = null,
    savedReports: List<DeepSynthesisReport> = emptyList(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isMetaReport = report.archetypeSummary.contains("Meta-Analysis") || report.title.contains("Master")

    var showRawDataExportDialog by remember { mutableStateOf(false) }
    var showReportExportDialog by remember { mutableStateOf(false) }
    var showCareLocatorDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpace)
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 20.dp, bottom = 90.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "← Back",
                    style = MaterialTheme.typography.labelMedium,
                    color = CelestialGold,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onBack() }
                        .testTag("back_to_reports_library_button")
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { showReportExportDialog = true }, modifier = Modifier.testTag("report_export_cloud_header_button")) {
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

                    IconButton(onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "✨ ${report.title}\n\n${report.archetypeSummary}\n\nCore Traits: ${report.coreTraits.joinToString(", ")}")
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share Report"))
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Report Title Card
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = CelestialGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isMetaReport) "MASTER META-ANALYSIS REPORT" else "MIND & COSMOS DEEP REPORT",
                            style = MaterialTheme.typography.labelMedium,
                            color = CelestialGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = report.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = report.archetypeSummary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Core Traits Tags
        item {
            Text(
                text = "Core Psychological Traits",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                report.coreTraits.forEach { trait ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MysticViolet.copy(alpha = 0.25f))
                            .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = trait,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Detailed Breakdown
        item {
            ReportSectionCard(
                title = if (isMetaReport) "Multi-Test Meta Analysis & Synthesis" else "Psychological & Astrological Synthesis",
                content = report.psychologicalBreakdown,
                accentColor = CelestialGold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Shadow Work & Blindspots
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, ShadowRose.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Shadow Work & Latent Blindspots",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = ShadowRose
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    report.shadowWorkInsights.forEach { item ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text("• ", color = ShadowRose, fontWeight = FontWeight.Bold)
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Career & Relationship Playbook
        item {
            ReportSectionCard(
                title = "Career, Calling & Strategic Purpose",
                content = report.careerAndPurposeAdvice,
                accentColor = NebulaTeal
            )
            Spacer(modifier = Modifier.height(16.dp))

            ReportSectionCard(
                title = "Relationship & Intimacy Playbook",
                content = report.relationshipPlaybook,
                accentColor = CelestialGold
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        // 7-Day Actionable Micro-Habits Plan
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
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = NebulaTeal)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "7-Day Actionable Self-Improvement Roadmap",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    report.dailyActionPlan.forEachIndexed { idx, habit ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(DeepSpace)
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = habit.isCompleted,
                                onCheckedChange = { onToggleHabit(report, idx) },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = NebulaTeal,
                                    uncheckedColor = Color.White.copy(alpha = 0.5f)
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Day ${habit.dayNumber}: ${habit.title}",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (habit.isCompleted) NebulaTeal else Color.White,
                                        textDecoration = if (habit.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                                    )
                                }
                                Text(
                                    text = habit.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Action Buttons & Disclaimer Footer
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Next Steps & Professional Care",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Discuss these findings with a licensed mental health professional or export your raw data records.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        androidx.compose.material3.Button(
                            onClick = { showCareLocatorDialog = true },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = DeepSpace),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("report_locate_professional_button")
                        ) {
                            Icon(Icons.Default.MedicalServices, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Care AI", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }

                        androidx.compose.material3.OutlinedButton(
                            onClick = { showRawDataExportDialog = true },
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold),
                            modifier = Modifier
                                .weight(1.1f)
                                .height(44.dp)
                                .testTag("report_download_raw_data_button")
                        ) {
                            Icon(Icons.Default.Download, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Raw Data (Free)", color = CelestialGold, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }

                        androidx.compose.material3.Button(
                            onClick = { showReportExportDialog = true },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1.1f)
                                .height(44.dp)
                                .testTag("report_export_cloud_button")
                        ) {
                            Icon(Icons.Default.CloudUpload, contentDescription = null, tint = Color.Black, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Export Report", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(DeepSpace)
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(Icons.Default.Shield, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "*This app is intended for entertainment and educational purposes and that results should be discussed with a professional for diagnosis and treatment.",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 10.sp,
                                lineHeight = 14.sp
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

    if (showReportExportDialog) {
        ReportExportDialog(
            reports = if (savedReports.isNotEmpty()) savedReports else listOf(report),
            initialSelectedReport = report,
            onDismiss = { showReportExportDialog = false }
        )
    }

    if (showCareLocatorDialog) {
        ProfessionalLocatorDialog(
            onDismiss = { showCareLocatorDialog = false }
        )
    }
}

@Composable
fun ReportSectionCard(title: String, content: String, accentColor: Color) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, accentColor.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f),
                lineHeight = 22.sp
            )
        }
    }
}
