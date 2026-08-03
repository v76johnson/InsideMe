package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.DeepSynthesisReport
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReportExportDialog(
    reports: List<DeepSynthesisReport>,
    initialSelectedReport: DeepSynthesisReport? = null,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    // Option to export single report or all reports bulk
    var exportAllMode by remember { mutableStateOf(initialSelectedReport == null || reports.size > 1 && initialSelectedReport == null) }
    var selectedReport by remember { mutableStateOf(initialSelectedReport ?: reports.firstOrNull()) }
    var exportFormat by remember { mutableStateOf("MARKDOWN") } // "MARKDOWN", "JSON"

    val exportedContent = remember(exportAllMode, selectedReport, exportFormat, reports) {
        if (exportAllMode) {
            if (exportFormat == "JSON") formatAllReportsToJson(reports)
            else formatAllReportsToMarkdown(reports)
        } else {
            val r = selectedReport
            if (r != null) {
                if (exportFormat == "JSON") formatReportToJson(r)
                else formatReportToMarkdown(r)
            } else "No report available for export."
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.88f),
            shape = RoundedCornerShape(24.dp),
            color = DeepSpace,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CloudUpload, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Export Premium Reports",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                        }
                        Text(
                            text = "Export summary & full synthesis reports to cloud, drive, email or files",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_report_export_dialog")) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Selector: Single Report vs All Reports (if multiple exist)
                if (reports.size > 1) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(CosmicPurple)
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (!exportAllMode) MysticViolet else Color.Transparent)
                                .clickable { exportAllMode = false }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Single Report",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                fontWeight = if (!exportAllMode) FontWeight.Bold else FontWeight.Normal
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (exportAllMode) MysticViolet else Color.Transparent)
                                .clickable { exportAllMode = true }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Export All (${reports.size} Reports)",
                                style = MaterialTheme.typography.labelMedium,
                                color = CelestialGold,
                                fontWeight = if (exportAllMode) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Format Selector: Formatted Document (Markdown/TXT) vs JSON Package
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (exportFormat == "MARKDOWN") NebulaTeal.copy(alpha = 0.25f) else CosmicPurple)
                            .border(1.dp, if (exportFormat == "MARKDOWN") NebulaTeal else Color.Transparent, RoundedCornerShape(10.dp))
                            .clickable { exportFormat = "MARKDOWN" }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📄 Formatted Document",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (exportFormat == "MARKDOWN") NebulaTeal else Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (exportFormat == "JSON") CelestialGold.copy(alpha = 0.25f) else CosmicPurple)
                            .border(1.dp, if (exportFormat == "JSON") CelestialGold else Color.Transparent, RoundedCornerShape(10.dp))
                            .clickable { exportFormat = "JSON" }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📦 JSON Cloud Package",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (exportFormat == "JSON") CelestialGold else Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Scrollable Preview Container
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                        .background(CosmicPurple.copy(alpha = 0.6f))
                        .padding(14.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = exportedContent,
                            fontFamily = if (exportFormat == "JSON") FontFamily.Monospace else FontFamily.Default,
                            fontSize = 12.sp,
                            color = if (exportFormat == "JSON") Color(0xFFA5D6A7) else Color.White.copy(alpha = 0.95f),
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Action Buttons for Premium Export
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Action 1: Export to Cloud / Drive / External Apps (Share Intent)
                        Button(
                            onClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, exportedContent)
                                    type = if (exportFormat == "JSON") "application/json" else "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, if (exportAllMode) "InsideMe Portfolio Reports Export" else selectedReport?.title ?: "InsideMe Synthesis Report")
                                }
                                val shareIntent = Intent.createChooser(sendIntent, "Export to Cloud Services / Drive / Email")
                                context.startActivity(shareIntent)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("share_to_cloud_button")
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Export / Share", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }

                        // Action 2: Email to Licensed Professional / Self
                        Button(
                            onClick = {
                                try {
                                    val title = selectedReport?.title ?: "Personal Mind & Cosmos Report"
                                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("mailto:")
                                        putExtra(Intent.EXTRA_SUBJECT, "Psychology & Astrology Synthesis Report: $title")
                                        putExtra(Intent.EXTRA_TEXT, exportedContent)
                                    }
                                    context.startActivity(Intent.createChooser(emailIntent, "Send Report via Email"))
                                } catch (e: Exception) {
                                    Toast.makeText(context, "No email client found", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = DeepSpace),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .testTag("email_report_button")
                        ) {
                            Icon(Icons.Default.Email, contentDescription = null, tint = DeepSpace, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Email Caregiver", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                    // Action 3: Copy Text to Clipboard
                    OutlinedButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("InsideMe Report Export", exportedContent)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Copied report content to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .testTag("copy_report_content_button"),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy Full Content to Clipboard", color = CelestialGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

fun formatReportToMarkdown(report: DeepSynthesisReport): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
    val dateStr = sdf.format(Date(report.createdAtMillis))

    val sb = StringBuilder()
    sb.append("# ${report.title.uppercase()}\n")
    sb.append("Generated on: $dateStr\n")
    sb.append("Source: InsideMe - Mind & Astrology Explorer (Premium Report Service)\n\n")
    sb.append("---\n\n")

    sb.append("## EXECUTIVE ARCHETYPE SUMMARY\n")
    sb.append("${report.archetypeSummary}\n\n")

    if (report.coreTraits.isNotEmpty()) {
        sb.append("## CORE TRAITS & CONVERGENCES\n")
        report.coreTraits.forEach { trait ->
            sb.append("- $trait\n")
        }
        sb.append("\n")
    }

    sb.append("## IN-DEPTH PSYCHOLOGICAL BREAKDOWN\n")
    sb.append("${report.psychologicalBreakdown}\n\n")

    sb.append("## ASTROLOGICAL SYNTHESIS\n")
    sb.append("${report.astrologicalSynthesis}\n\n")

    if (report.shadowWorkInsights.isNotEmpty()) {
        sb.append("## SHADOW WORK & SELF-INTEGRATION INSIGHTS\n")
        report.shadowWorkInsights.forEach { insight ->
            sb.append("- $insight\n")
        }
        sb.append("\n")
    }

    if (report.careerAndPurposeAdvice.isNotBlank()) {
        sb.append("## CAREER & PURPOSE STRATEGY\n")
        sb.append("${report.careerAndPurposeAdvice}\n\n")
    }

    if (report.relationshipPlaybook.isNotBlank()) {
        sb.append("## RELATIONSHIP & INTERPERSONAL PLAYBOOK\n")
        sb.append("${report.relationshipPlaybook}\n\n")
    }

    if (report.dailyActionPlan.isNotEmpty()) {
        sb.append("## 7-DAY ACTION PLAN & MICRO-HABITS\n")
        report.dailyActionPlan.forEach { habit ->
            val status = if (habit.isCompleted) "[COMPLETED]" else "[PENDING]"
            sb.append("Day ${habit.dayNumber} (${habit.category}) - $status: ${habit.title}\n")
            sb.append("  ${habit.description}\n")
        }
        sb.append("\n")
    }

    sb.append("---\n")
    sb.append("*Disclaimer: InsideMe reports are for self-discovery, educational and personal entertainment purposes.*")

    return sb.toString()
}

fun formatAllReportsToMarkdown(reports: List<DeepSynthesisReport>): String {
    val sb = StringBuilder()
    sb.append("# INSIDEME PREMIUM SYNTHESIS PORTFOLIO EXPORT\n")
    sb.append("Total Saved Reports: ${reports.size}\n")
    sb.append("Export Timestamp: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())}\n\n")
    sb.append("====================================================\n\n")

    reports.forEachIndexed { index, report ->
        sb.append("### REPORT ${index + 1} OF ${reports.size}\n")
        sb.append(formatReportToMarkdown(report))
        sb.append("\n\n====================================================\n\n")
    }

    return sb.toString()
}

fun formatReportToJson(report: DeepSynthesisReport): String {
    val obj = JSONObject()
    obj.put("id", report.id)
    obj.put("createdAtMillis", report.createdAtMillis)
    obj.put("createdAtFormatted", SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date(report.createdAtMillis)))
    obj.put("title", report.title)
    obj.put("archetypeSummary", report.archetypeSummary)

    val traitsArr = JSONArray()
    report.coreTraits.forEach { traitsArr.put(it) }
    obj.put("coreTraits", traitsArr)

    obj.put("psychologicalBreakdown", report.psychologicalBreakdown)
    obj.put("astrologicalSynthesis", report.astrologicalSynthesis)

    val shadowArr = JSONArray()
    report.shadowWorkInsights.forEach { shadowArr.put(it) }
    obj.put("shadowWorkInsights", shadowArr)

    obj.put("careerAndPurposeAdvice", report.careerAndPurposeAdvice)
    obj.put("relationshipPlaybook", report.relationshipPlaybook)

    val habitsArr = JSONArray()
    report.dailyActionPlan.forEach { h ->
        val hObj = JSONObject()
        hObj.put("dayNumber", h.dayNumber)
        hObj.put("title", h.title)
        hObj.put("description", h.description)
        hObj.put("category", h.category)
        hObj.put("isCompleted", h.isCompleted)
        habitsArr.put(hObj)
    }
    obj.put("dailyActionPlan", habitsArr)
    obj.put("isBookmarked", report.isBookmarked)
    obj.put("isGeneratedByAi", report.isGeneratedByAi)

    return obj.toString(2)
}

fun formatAllReportsToJson(reports: List<DeepSynthesisReport>): String {
    val rootObj = JSONObject()
    rootObj.put("exportTimestamp", System.currentTimeMillis())
    rootObj.put("exportDateFormatted", SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date()))
    rootObj.put("appName", "InsideMe - Mind & Astrology Explorer")
    rootObj.put("serviceTier", "Premium Service Export")
    rootObj.put("totalReportsCount", reports.size)

    val reportsArr = JSONArray()
    reports.forEach { r ->
        reportsArr.put(JSONObject(formatReportToJson(r)))
    }
    rootObj.put("savedSynthesisReports", reportsArr)

    return rootObj.toString(2)
}
