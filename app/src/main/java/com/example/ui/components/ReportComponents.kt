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
import com.example.ui.theme.Spacing

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
            .padding(horizontal = Spacing.pagePadding),
        contentPadding = PaddingValues(top = Spacing.large, bottom = 90.dp)
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

            Spacer(modifier = Modifier.height(Spacing.large))

            // Report Title Card
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
            ) {
                Column(modifier = Modifier.padding(Spacing.cardContent * 2)) {
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

                    Spacer(modifier = Modifier.height(Spacing.medium))

                    Text(
                        text = report.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(Spacing.small))

                    Text(
                        text = report.archetypeSummary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))
        }

        // Core Traits Tags
        item {
            Text(
                text = "Core Psychological Traits",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(Spacing.small))

            Column {
                report.coreTraits.forEach { trait ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Spacing.small)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MysticViolet.copy(alpha = 0.25f))
                            .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                            .padding(Spacing.cardContent)
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

            Spacer(modifier = Modifier.height(Spacing.large))
        }

        // Detailed Breakdown
        item {
            ReportSectionCard(
                title = if (isMetaReport) "Multi-Test Meta Analysis & Synthesis" else "Psychological & Astrological Synthesis",
                content = report.psychologicalBreakdown,
                accentColor = CelestialGold
            )
            Spacer(modifier = Modifier.height(Spacing.medium))
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
                Column(modifier = Modifier.padding(Spacing.cardContent * 2)) {
                    Text(
                        text = "Shadow Work & Latent Blindspots",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = ShadowRose
                    )

                    Spacer(modifier = Modifier.height(Spacing.medium))

{