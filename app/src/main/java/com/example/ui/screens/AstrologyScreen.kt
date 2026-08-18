package com.example.ui.screens

import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import com.example.data.local.TestResultEntity
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AstrologyChatMessage
import com.example.data.model.AstrologyProfile
import com.example.data.model.CustomNatalChart
import com.example.data.model.InDepthMatchReport
import com.example.data.model.ZodiacSign
import com.example.data.repository.AstrologyEngine
import com.example.ui.components.ChartWheelView
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.ShadowRose
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.filled.TrendingUp

import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.FileProvider
import com.example.data.model.DeepSynthesisReport
import com.example.ui.components.ReportReaderView
import com.example.ui.components.TarotGeneratorView
import androidx.compose.material.icons.filled.Casino
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstrologyScreen(
    profile: AstrologyProfile?,
    natalCharts: List<CustomNatalChart>,
    oracleMessages: List<AstrologyChatMessage>,
    isOracleThinking: Boolean,
    isGeneratingMatchReport: Boolean,
    inDepthMatchReport: InDepthMatchReport?,
    savedReports: List<DeepSynthesisReport> = emptyList(),
    selectedReport: DeepSynthesisReport? = null,
    testResults: List<TestResultEntity> = emptyList(),
    isGeneratingReport: Boolean = false,
    gemsBalance: Int = 0,
    isPremium: Boolean = false,
    initialSubTab: Int = 0,
    onUpdateSigns: (ZodiacSign, ZodiacSign, ZodiacSign) -> Unit,
    onUpdateProfile: (dobMillis: Long, timeStr: String, cityStr: String) -> Unit = { _, _, _ -> },
    onSendOracleMessage: (String) -> Unit,
    onGenerateBirthdateMatch: (p1Name: String, p1Dob: Long, p1Time: String, p1City: String, p2Name: String, p2Dob: Long, p2Time: String, p2City: String) -> Unit,
    onSaveNatalChart: (name: String, dobMillis: Long, timeStr: String, cityStr: String, notes: String) -> Unit,
    onDeleteNatalChart: (String) -> Unit,
    onClearMatchReport: () -> Unit,
    onGenerateReportClicked: () -> Unit = {},
    onSelectReport: (DeepSynthesisReport?) -> Unit = {},
    onToggleBookmark: (String, Boolean) -> Unit = { _, _ -> },
    onToggleHabit: (DeepSynthesisReport, Int) -> Unit = { _, _ -> },
    onNavigateToReports: () -> Unit = {},
    onUpgradeClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val localContext = LocalContext.current

    if (selectedReport != null) {
        ReportReaderView(
            report = selectedReport,
            onBack = { onSelectReport(null) },
            onToggleBookmark = onToggleBookmark,
            onToggleHabit = onToggleHabit,
            testResults = testResults,
            astrologyProfile = profile,
            savedReports = savedReports,
            modifier = modifier
        )
        return
    }

    var activeTab by remember(initialSubTab) { mutableIntStateOf(initialSubTab) }

    val sunSign = profile?.sunSign ?: ZodiacSign.SCORPIO
    val moonSign = profile?.moonSign ?: ZodiacSign.PISCES
    val risingSign = profile?.risingSign ?: ZodiacSign.CANCER

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // State for Dialogs
    var showAddChartDialog by remember { mutableStateOf(false) }
    var showEditPersonalChartDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpace)
    ) {
        // Header & Astrology Home navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { activeTab = 0 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (activeTab == 0) CelestialGold else CosmicPurple,
                    contentColor = if (activeTab == 0) DeepSpace else Color.White
                ),
                border = BorderStroke(1.dp, if (activeTab == 0) CelestialGold else MysticViolet.copy(alpha = 0.6f)),
                shape = RoundedCornerShape(14.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                modifier = Modifier.testTag("astrology_home_top_btn")
            ) {
                Icon(
                    Icons.Default.AutoAwesome,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if (activeTab == 0) DeepSpace else CelestialGold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Astrology Home",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            if (activeTab != 0) {
                val currentTabTitle = when (activeTab) {
                    1 -> "🪐 Natal Charts"
                    2 -> "🃏 Tarot Generator"
                    3 -> "🔮 AI Oracle"
                    4 -> "💖 Synastry"
                    5 -> "✨ Mind & Cosmos"
                    else -> ""
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(CosmicPurple)
                        .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = currentTabTitle,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold
                    )
                }
            }
        }

        when (activeTab) {
            0 -> AstroHomepageTab(
                profile = profile,
                sunSign = sunSign,
                moonSign = moonSign,
                risingSign = risingSign,
                onUpdateSigns = onUpdateSigns,
                onOpenEditPersonalChartDialog = { showEditPersonalChartDialog = true },
                onNavigateToTab = { activeTab = it },
                onNavigateToReports = { activeTab = 5 }
            )

            1 -> NatalChartsTab(
                profile = profile,
                natalCharts = natalCharts,
                onUpdateSigns = onUpdateSigns,
                onOpenEditPersonalChartDialog = { showEditPersonalChartDialog = true },
                onOpenAddChartDialog = { showAddChartDialog = true },
                onDeleteChart = onDeleteNatalChart,
                onAskOracleAboutChart = { chart ->
                    activeTab = 3
                    onSendOracleMessage("Analyze natal chart for ${chart.personName}: Sun in ${chart.sunSign.displayName}, Moon in ${chart.moonSign.displayName}, Rising in ${chart.risingSign.displayName}. What are key strengths and life themes?")
                },
                onAskOracleAboutPersonalChart = {
                    activeTab = 3
                    onSendOracleMessage("Analyze my personal natal chart: Sun in ${sunSign.displayName}, Moon in ${moonSign.displayName}, Rising in ${risingSign.displayName}. What are my key strengths, life purpose, and current transit guidance?")
                }
            )

            2 -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentPadding = PaddingValues(top = 12.dp, bottom = 90.dp)
                ) {
                    item {
                        TarotGeneratorView(
                            sunSign = sunSign,
                            userName = profile?.userName?.ifBlank { "Seeker" } ?: "Seeker",
                            onOpenChat = { prompt ->
                                activeTab = 3
                                onSendOracleMessage(prompt)
                            }
                        )
                    }
                }
            }

            3 -> AiOracleTab(
                oracleMessages = oracleMessages,
                isOracleThinking = isOracleThinking,
                sunSign = sunSign,
                moonSign = moonSign,
                risingSign = risingSign,
                onSend = onSendOracleMessage
            )

            4 -> BirthdateSynastryTab(
                profile = profile,
                savedCharts = natalCharts,
                isGenerating = isGeneratingMatchReport,
                report = inDepthMatchReport,
                isPremium = isPremium,
                onUpgradeClicked = onUpgradeClicked,
                onGenerate = onGenerateBirthdateMatch,
                onClearReport = onClearMatchReport
            )

            5 -> MindAndCosmosReportTab(
                savedReports = savedReports,
                isGenerating = isGeneratingReport,
                gemsBalance = gemsBalance,
                isPremium = isPremium,
                onGenerateReportClicked = onGenerateReportClicked,
                onSelectReport = onSelectReport,
                onToggleBookmark = onToggleBookmark
            )
        }
    }

    if (showAddChartDialog) {
        AddNatalChartDialog(
            onDismiss = { showAddChartDialog = false },
            onSave = { name, dobMillis, timeStr, cityStr, notes ->
                onSaveNatalChart(name, dobMillis, timeStr, cityStr, notes)
                showAddChartDialog = false
            }
        )
    }

    if (showEditPersonalChartDialog) {
        EditPersonalNatalChartDialog(
            currentProfile = profile,
            onDismiss = { showEditPersonalChartDialog = false },
            onSave = { dobMillis, timeStr, cityStr ->
                onUpdateProfile(dobMillis, timeStr, cityStr)
                showEditPersonalChartDialog = false
            }
        )
    }
}

@Composable
fun AiOracleTab(
    oracleMessages: List<AstrologyChatMessage>,
    isOracleThinking: Boolean,
    sunSign: ZodiacSign,
    moonSign: ZodiacSign,
    risingSign: ZodiacSign,
    onSend: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    val chatListState = rememberLazyListState()

    LaunchedEffect(oracleMessages.size, isOracleThinking) {
        if (oracleMessages.isNotEmpty()) {
            chatListState.animateScrollToItem(oracleMessages.size - 1)
        }
    }

    val quickPrompts = listOf(
        "✨ What does my Sun/Moon combination mean?",
        "💖 How do my placements affect love & compatibility?",
        "🚀 What is my true career purpose & financial direction?",
        "🪐 How can I align my energy with current transits?"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Quick Prompts Header Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(quickPrompts) { prompt ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(CosmicPurple)
                        .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                        .clickable { onSend(prompt.removePrefix("✨ ").removePrefix("💖 ").removePrefix("🚀 ").removePrefix("🪐 ")) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = prompt,
                        style = MaterialTheme.typography.labelMedium,
                        color = CelestialGold,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Messages List
        LazyColumn(
            state = chatListState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(oracleMessages) { msg ->
                val isUser = msg.sender == "user"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                ) {
                    if (!isUser) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(CelestialGold.copy(alpha = 0.2f))
                                .border(1.dp, CelestialGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Card(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (isUser) 16.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 16.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isUser) MysticViolet else CosmicPurple
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .border(
                                1.dp,
                                if (isUser) CelestialGold.copy(alpha = 0.4f) else MysticViolet.copy(alpha = 0.5f),
                                RoundedCornerShape(16.dp)
                            )
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            if (!isUser) {
                                Text(
                                    text = "Cosmic AI Oracle",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = CelestialGold,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                            Text(
                                text = msg.text,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }

            if (isOracleThinking) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = CelestialGold,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Consulting cosmic transits & star placements...",
                            style = MaterialTheme.typography.bodySmall,
                            color = CelestialGold
                        )
                    }
                }
            }
        }

        // Input Row
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            colors = CardDefaults.cardColors(containerColor = CosmicPurple),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Ask the Cosmic Oracle anything...", color = Color.White.copy(alpha = 0.5f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            onSend(inputText)
                            inputText = ""
                        }
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(CelestialGold)
                        .testTag("send_oracle_message_button")
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send", tint = DeepSpace)
                }
            }
        }
    }
}

fun parseDateStringToMillis(input: String): Long? {
    val trimmed = input.trim()
    if (trimmed.isBlank()) return null
    val formats = listOf(
        SimpleDateFormat("MMM dd, yyyy", Locale.US),
        SimpleDateFormat("MM/dd/yyyy", Locale.US),
        SimpleDateFormat("yyyy-MM-dd", Locale.US),
        SimpleDateFormat("MM-dd-yyyy", Locale.US),
        SimpleDateFormat("dd/MM/yyyy", Locale.US),
        SimpleDateFormat("dd MMM yyyy", Locale.US),
        SimpleDateFormat("MMMM dd, yyyy", Locale.US)
    )
    for (sdf in formats) {
        try {
            sdf.isLenient = false
            val date = sdf.parse(trimmed)
            if (date != null) return date.time
        } catch (_: Exception) { }
    }
    return null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdateSynastryTab(
    profile: AstrologyProfile?,
    savedCharts: List<CustomNatalChart>,
    isGenerating: Boolean,
    report: InDepthMatchReport?,
    isPremium: Boolean = false,
    onUpgradeClicked: () -> Unit = {},
    onGenerate: (p1Name: String, p1Dob: Long, p1Time: String, p1City: String, p2Name: String, p2Dob: Long, p2Time: String, p2City: String) -> Unit,
    onClearReport: () -> Unit
) {
    val localContext = LocalContext.current
    if (!isPremium) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, CelestialGold, RoundedCornerShape(22.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(CelestialGold.copy(alpha = 0.2f))
                                .border(1.dp, CelestialGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "💖", fontSize = 28.sp)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Birthdate Synastry & Inter-Chart Compatibility",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(CelestialGold)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "PSYCHE+ PREMIUM FEATURE",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = DeepSpace
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Unlock birthdate synastry matching to analyze relationship chemistry, dual planetary alignments, elemental synergy, harmony vs friction points, and comprehensive AI relationship reports.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(
                                "✨ Multi-person birthdate & chart comparisons",
                                "✨ Dual Sun, Moon, & Rising planetary alignment scores",
                                "✨ Key relationship harmony & friction dynamics",
                                "✨ Deep AI-generated relationship synthesis reports"
                            ).forEach { perk ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = NebulaTeal, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = perk, style = MaterialTheme.typography.bodySmall, color = Color.White)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onUpgradeClicked,
                            colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("unlock_synastry_button")
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Unlock Synastry with Psyche+", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
        return
    }

    var p1Name by remember { mutableStateOf("Me (Personal Chart)") }
    var p1DobMillis by remember { mutableStateOf(profile?.birthDateMillis ?: 880000000000L) }
    var p1Time by remember { mutableStateOf(profile?.birthTime ?: "12:00 PM") }
    var p1City by remember { mutableStateOf(profile?.birthCity ?: "New York, USA") }

    var p2Name by remember { mutableStateOf(savedCharts.firstOrNull()?.personName ?: "Partner 2") }
    var p2DobMillis by remember { mutableStateOf(savedCharts.firstOrNull()?.birthDateMillis ?: 920000000000L) }
    var p2Time by remember { mutableStateOf(savedCharts.firstOrNull()?.birthTime ?: "02:15 PM") }
    var p2City by remember { mutableStateOf(savedCharts.firstOrNull()?.birthCity ?: "London, UK") }

    var showDatePickerForP1 by remember { mutableStateOf(false) }
    var showDatePickerForP2 by remember { mutableStateOf(false) }

    val sdf = remember { SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) }
    var p1DateText by remember { mutableStateOf(sdf.format(Date(p1DobMillis))) }
    var p2DateText by remember { mutableStateOf(sdf.format(Date(p2DobMillis))) }

    LaunchedEffect(p1DobMillis) {
        p1DateText = sdf.format(Date(p1DobMillis))
    }

    LaunchedEffect(p2DobMillis) {
        p2DateText = sdf.format(Date(p2DobMillis))
    }

    LaunchedEffect(profile) {
        if (profile != null && p1Name == "Me (Personal Chart)") {
            p1DobMillis = profile.birthDateMillis
            p1Time = profile.birthTime
            p1City = profile.birthCity
        }
    }

    if (report != null) {
        // Display full Synastry Report
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "In-Depth Synastry Report",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            try {
                                val pdfDocument = android.graphics.pdf.PdfDocument()
                                val pageInfo = android.graphics.pdf.PdfDocument.PageInfo.Builder(595, 842, 1).create()
                                val page = pdfDocument.startPage(pageInfo)
                                val canvas = page.canvas
                                val paint = android.graphics.Paint().apply {
                                    color = android.graphics.Color.BLACK
                                    textSize = 11f
                                    isAntiAlias = true
                                }
                                val titlePaint = android.graphics.Paint().apply {
                                    color = android.graphics.Color.BLACK
                                    textSize = 16f
                                    isFakeBoldText = true
                                    isAntiAlias = true
                                }

                                var y = 40f
                                canvas.drawText(report.title, 40f, y, titlePaint)
                                y += 25f
                                canvas.drawText("Compatibility Score: ${report.compatibilityScore}%", 40f, y, paint)
                                y += 20f
                                canvas.drawText("Between: ${report.person1Name} & ${report.person2Name}", 40f, y, paint)
                                y += 30f

                                for (line in report.actionableAdvice.split("\n")) {
                                    if (y > 800f) break
                                    canvas.drawText(line.take(95), 40f, y, paint)
                                    y += 16f
                                }

                                pdfDocument.finishPage(page)
                                val file = File(localContext.cacheDir, "Synastry_Report_${report.person1Name}_vs_${report.person2Name}.pdf")
                                val outputStream = FileOutputStream(file)
                                pdfDocument.writeTo(outputStream)
                                pdfDocument.close()
                                outputStream.close()

                                val uri = FileProvider.getUriForFile(localContext, "${localContext.packageName}.fileprovider", file)
                                val intent = Intent(Intent.ACTION_VIEW).setDataAndType(uri, "application/pdf").apply {
                                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                try {
                                    localContext.startActivity(intent)
                                } catch (_: Exception) {
                                    android.widget.Toast.makeText(localContext, "Synastry PDF saved to cache: ${file.name}", android.widget.Toast.LENGTH_LONG).show()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                android.widget.Toast.makeText(localContext, "Failed to generate Synastry PDF: ${e.localizedMessage}", android.widget.Toast.LENGTH_LONG).show()
                            }
                        }) {
                            Icon(Icons.Default.Download, contentDescription = "Download Synastry PDF", tint = CelestialGold)
                        }

                        IconButton(onClick = {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, "✨ Synastry Report (${report.person1Name} & ${report.person2Name}): ${report.title}\nCompatibility: ${report.compatibilityScore}%\n\n${report.actionableAdvice}")
                            }
                            localContext.startActivity(Intent.createChooser(shareIntent, "Share Synastry Report"))
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "Share Report", tint = CelestialGold)
                        }

                        TextButton(onClick = onClearReport) {
                            Text("New Calculation", color = NebulaTeal, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Score Banner
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CelestialGold, RoundedCornerShape(20.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(CelestialGold.copy(alpha = 0.2f))
                                .border(2.dp, CelestialGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${report.compatibilityScore}%",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = report.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 26.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "${report.person1Name} (${report.person1Sun.displayName}) & ${report.person2Name} (${report.person2Sun.displayName})",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // Elemental Chemistry Card
            item {
                AstroReportSectionCard(
                    title = "🌊 Elemental Chemistry",
                    content = report.elementalChemistry,
                    accentColor = NebulaTeal
                )
            }

            // Emotional Resonance Card
            item {
                AstroReportSectionCard(
                    title = "🌙 Emotional Resonance (Moon Connection)",
                    content = report.emotionalResonance,
                    accentColor = CelestialGold
                )
            }

            // Communication Card
            item {
                AstroReportSectionCard(
                    title = "🗣️ Communication & Intellectual Dynamics",
                    content = report.communicationDynamics,
                    accentColor = MysticViolet
                )
            }

            // Passion Card
            item {
                AstroReportSectionCard(
                    title = "🔥 Passion & Magnetism",
                    content = report.passionAndAttraction,
                    accentColor = ShadowRose
                )
            }

            // Harmony & Friction List Card
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MysticViolet.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("✨ Harmony Pillars", style = MaterialTheme.typography.titleMedium, color = NebulaTeal, fontWeight = FontWeight.Bold)
                        report.harmonyPoints.forEach { point ->
                            Text("• $point", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.9f), modifier = Modifier.padding(vertical = 2.dp))
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text("⚡ Growth Challenges", style = MaterialTheme.typography.titleMedium, color = ShadowRose, fontWeight = FontWeight.Bold)
                        report.frictionPoints.forEach { point ->
                            Text("• $point", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.9f), modifier = Modifier.padding(vertical = 2.dp))
                        }
                    }
                }
            }

            // Actionable Advice Card
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = DeepSpace),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CelestialGold.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("💡 Cosmic Relationship Advice", style = MaterialTheme.typography.titleSmall, color = CelestialGold, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(report.actionableAdvice, style = MaterialTheme.typography.bodyMedium, color = Color.White, lineHeight = 22.sp)
                    }
                }
            }
        }
    } else {
        // Form to select/enter both birthdates
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CelestialGold.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Favorite, contentDescription = null, tint = ShadowRose)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "In-Depth Birthdate Match Generator",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Text(
                            text = "Select from your saved natal charts or enter details to generate a comprehensive AI synastry report.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            // Person 1 Form Card
            item {
                PersonBirthCard(
                    title = "Person 1 Details",
                    profile = profile,
                    savedCharts = savedCharts,
                    name = p1Name,
                    onNameChange = { p1Name = it },
                    dateStr = p1DateText,
                    onDateChange = { typed ->
                        p1DateText = typed
                        val parsed = parseDateStringToMillis(typed)
                        if (parsed != null) {
                            p1DobMillis = parsed
                        }
                    },
                    onOpenDatePicker = { showDatePickerForP1 = true },
                    timeStr = p1Time,
                    onTimeChange = { p1Time = it },
                    cityStr = p1City,
                    onCityChange = { p1City = it },
                    onSelectPersonalChart = {
                        p1Name = "Me (Personal Chart)"
                        p1DobMillis = profile?.birthDateMillis ?: System.currentTimeMillis()
                        p1Time = profile?.birthTime ?: "12:00 PM"
                        p1City = profile?.birthCity ?: "New York, USA"
                    },
                    onSelectChart = { chart ->
                        p1Name = chart.personName
                        p1DobMillis = chart.birthDateMillis
                        p1Time = chart.birthTime
                        p1City = chart.birthCity
                    },
                    accentColor = CelestialGold
                )
            }

            // Person 2 Form Card
            item {
                PersonBirthCard(
                    title = "Person 2 Details",
                    profile = profile,
                    savedCharts = savedCharts,
                    name = p2Name,
                    onNameChange = { p2Name = it },
                    dateStr = p2DateText,
                    onDateChange = { typed ->
                        p2DateText = typed
                        val parsed = parseDateStringToMillis(typed)
                        if (parsed != null) {
                            p2DobMillis = parsed
                        }
                    },
                    onOpenDatePicker = { showDatePickerForP2 = true },
                    timeStr = p2Time,
                    onTimeChange = { p2Time = it },
                    cityStr = p2City,
                    onCityChange = { p2City = it },
                    onSelectPersonalChart = {
                        p2Name = "Me (Personal Chart)"
                        p2DobMillis = profile?.birthDateMillis ?: System.currentTimeMillis()
                        p2Time = profile?.birthTime ?: "12:00 PM"
                        p2City = profile?.birthCity ?: "New York, USA"
                    },
                    onSelectChart = { chart ->
                        p2Name = chart.personName
                        p2DobMillis = chart.birthDateMillis
                        p2Time = chart.birthTime
                        p2City = chart.birthCity
                    },
                    accentColor = NebulaTeal
                )
            }

            // Action Button
            item {
                Button(
                    onClick = {
                        onGenerate(p1Name, p1DobMillis, p1Time, p1City, p2Name, p2DobMillis, p2Time, p2City)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("generate_birthdate_match_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                    enabled = !isGenerating
                ) {
                    if (isGenerating) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = DeepSpace, strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Generating In-Depth Synastry...", fontWeight = FontWeight.Bold)
                    } else {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Generate AI Match Report", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }
    }

    if (showDatePickerForP1) {
        BirthDatePickerDialog(
            initialMillis = p1DobMillis,
            onDismiss = { showDatePickerForP1 = false },
            onDateSelected = { selected ->
                p1DobMillis = selected
                p1DateText = sdf.format(Date(selected))
                showDatePickerForP1 = false
            }
        )
    }

    if (showDatePickerForP2) {
        BirthDatePickerDialog(
            initialMillis = p2DobMillis,
            onDismiss = { showDatePickerForP2 = false },
            onDateSelected = { selected ->
                p2DobMillis = selected
                p2DateText = sdf.format(Date(selected))
                showDatePickerForP2 = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonBirthCard(
    title: String,
    profile: AstrologyProfile?,
    savedCharts: List<CustomNatalChart>,
    name: String,
    onNameChange: (String) -> Unit,
    dateStr: String,
    onDateChange: (String) -> Unit,
    onOpenDatePicker: () -> Unit,
    timeStr: String,
    onTimeChange: (String) -> Unit,
    cityStr: String,
    onCityChange: (String) -> Unit,
    onSelectPersonalChart: () -> Unit,
    onSelectChart: (CustomNatalChart) -> Unit,
    accentColor: Color
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    var nameFieldValue by remember { mutableStateOf(TextFieldValue(text = name, selection = TextRange(name.length))) }

    LaunchedEffect(name) {
        if (name != nameFieldValue.text) {
            nameFieldValue = TextFieldValue(text = name, selection = TextRange(name.length))
        }
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = accentColor)

                ExposedDropdownMenuBox(
                    expanded = dropdownExpanded,
                    onExpandedChange = { dropdownExpanded = !dropdownExpanded }
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(accentColor.copy(alpha = 0.2f))
                            .clickable { dropdownExpanded = true }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .menuAnchor()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = accentColor, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Select Chart ▾", style = MaterialTheme.typography.labelSmall, color = accentColor, fontWeight = FontWeight.Bold)
                        }
                    }

                    ExposedDropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false },
                        modifier = Modifier.background(CosmicPurple)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text("👤 My Personal Natal Chart", color = CelestialGold, fontWeight = FontWeight.Bold)
                                    profile?.let {
                                        Text("${it.sunSign.symbol} ${it.sunSign.displayName} Sun • ${it.birthCity}", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
                                    }
                                }
                            },
                            onClick = {
                                onSelectPersonalChart()
                                dropdownExpanded = false
                            }
                        )

                        savedCharts.forEach { chart ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text("🪐 ${chart.personName}", color = Color.White, fontWeight = FontWeight.Bold)
                                        Text("${chart.sunSign.symbol} ${chart.sunSign.displayName} Sun • ${chart.birthCity}", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
                                    }
                                },
                                onClick = {
                                    onSelectChart(chart)
                                    dropdownExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = nameFieldValue,
                onValueChange = { newValue ->
                    nameFieldValue = newValue
                    onNameChange(newValue.text)
                },
                label = { Text("Name", color = Color.White.copy(alpha = 0.7f)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = accentColor,
                    unfocusedBorderColor = MysticViolet,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = dateStr,
                    onValueChange = onDateChange,
                    label = { Text("Date of Birth", color = Color.White.copy(alpha = 0.7f)) },
                    trailingIcon = {
                        IconButton(onClick = onOpenDatePicker) {
                            Icon(Icons.Default.DateRange, contentDescription = "Pick date from calendar", tint = accentColor)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentColor,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = timeStr,
                    onValueChange = onTimeChange,
                    label = { Text("Birth Time", color = Color.White.copy(alpha = 0.7f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentColor,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = cityStr,
                onValueChange = onCityChange,
                label = { Text("Birth City & Country", color = Color.White.copy(alpha = 0.7f)) },
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = accentColor) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = accentColor,
                    unfocusedBorderColor = MysticViolet,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun AstroReportSectionCard(title: String, content: String, accentColor: Color) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, accentColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, color = accentColor, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(content, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.9f), lineHeight = 22.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignSelectorDropdown(
    label: String,
    selectedSign: ZodiacSign,
    onSignSelected: (ZodiacSign) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = "${selectedSign.symbol} ${selectedSign.displayName} (${selectedSign.element.displayName})",
            onValueChange = {},
            readOnly = true,
            label = { Text(label, color = CelestialGold) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CelestialGold,
                unfocusedBorderColor = MysticViolet.copy(alpha = 0.5f),
                focusedLabelColor = CelestialGold,
                unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(CosmicPurple)
        ) {
            ZodiacSign.entries.forEach { sign ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "${sign.symbol} ${sign.displayName} • ${sign.element.displayName}",
                            color = Color.White
                        )
                    },
                    onClick = {
                        onSignSelected(sign)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPersonalNatalChartDialog(
    currentProfile: AstrologyProfile?,
    onDismiss: () -> Unit,
    onSave: (dobMillis: Long, timeStr: String, cityStr: String) -> Unit
) {
    var dobMillis by remember { mutableStateOf(currentProfile?.birthDateMillis ?: 880000000000L) }
    var timeStr by remember { mutableStateOf(currentProfile?.birthTime ?: "12:00 PM") }
    var cityStr by remember { mutableStateOf(currentProfile?.birthCity ?: "New York, USA") }

    var showDatePicker by remember { mutableStateOf(false) }
    val sdf = remember { SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) }
    var dateText by remember { mutableStateOf(sdf.format(Date(dobMillis))) }

    LaunchedEffect(dobMillis) {
        dateText = sdf.format(Date(dobMillis))
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CosmicPurple,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = CelestialGold)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Edit Personal Birth Details", color = CelestialGold, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Updating your birth date, time, and city recalculates your Big 3 (Sun, Moon, Rising) and personal Natal Essence Summary.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )

                OutlinedTextField(
                    value = dateText,
                    onValueChange = { typed ->
                        dateText = typed
                        val parsed = parseDateStringToMillis(typed)
                        if (parsed != null) {
                            dobMillis = parsed
                        }
                    },
                    label = { Text("Date of Birth (e.g. Nov 19, 1997 or 11/19/1997)", color = Color.White.copy(alpha = 0.7f)) },
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Open Calendar", tint = CelestialGold)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = timeStr,
                    onValueChange = { timeStr = it },
                    label = { Text("Time of Birth (e.g. 08:30 AM)", color = Color.White.copy(alpha = 0.7f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = cityStr,
                    onValueChange = { cityStr = it },
                    label = { Text("Birth City & Country", color = Color.White.copy(alpha = 0.7f)) },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = CelestialGold) },
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
                    val finalMillis = parseDateStringToMillis(dateText) ?: dobMillis
                    onSave(finalMillis, timeStr, cityStr)
                },
                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace)
            ) {
                Text("Update My Personal Chart", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.White.copy(alpha = 0.7f))
            }
        }
    )

    if (showDatePicker) {
        BirthDatePickerDialog(
            initialMillis = dobMillis,
            onDismiss = { showDatePicker = false },
            onDateSelected = { selected ->
                dobMillis = selected
                dateText = sdf.format(Date(selected))
                showDatePicker = false
            }
        )
    }
}

@Composable
fun NatalChartsTab(
    profile: AstrologyProfile?,
    natalCharts: List<CustomNatalChart>,
    onUpdateSigns: (ZodiacSign, ZodiacSign, ZodiacSign) -> Unit,
    onOpenEditPersonalChartDialog: () -> Unit,
    onOpenAddChartDialog: () -> Unit,
    onDeleteChart: (String) -> Unit,
    onAskOracleAboutChart: (CustomNatalChart) -> Unit,
    onAskOracleAboutPersonalChart: () -> Unit
) {
    val sunSign = profile?.sunSign ?: ZodiacSign.SCORPIO
    val moonSign = profile?.moonSign ?: ZodiacSign.PISCES
    val risingSign = profile?.risingSign ?: ZodiacSign.CANCER

    val sdf = remember { SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) }
    val personalDobStr = remember(profile?.birthDateMillis) {
        profile?.birthDateMillis?.let { sdf.format(Date(it)) } ?: "11/19/1997"
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // SECTION 1: MY PERSONAL NATAL CHART (Primary)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, CelestialGold, RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("MY PERSONAL NATAL CHART", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CelestialGold)
                            }
                            Text("Born $personalDobStr at ${profile?.birthTime ?: "12:00 PM"} in ${profile?.birthCity ?: "New York, USA"}", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.7f))
                        }

                        Button(
                            onClick = onOpenEditPersonalChartDialog,
                            colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Edit Chart", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ChartWheelView(
                        sunSign = sunSign,
                        moonSign = moonSign,
                        risingSign = risingSign
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Big 3: ${sunSign.displayName} Sun • ${moonSign.displayName} Moon • ${risingSign.displayName} Rising",
                        style = MaterialTheme.typography.bodySmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Personal Natal Essence Summary
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = DeepSpace),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("✨ Natal Essence Summary", style = MaterialTheme.typography.labelSmall, color = NebulaTeal, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Your Sun in ${sunSign.displayName} (${sunSign.symbol}) anchors core identity. Combined with Moon in ${moonSign.displayName} (${moonSign.symbol}) guiding intuition, and Ascendant in ${risingSign.displayName} (${risingSign.symbol}) projecting your outer presence, your Big 3 harmonizes ${sunSign.element.displayName}, ${moonSign.element.displayName}, and ${risingSign.element.displayName} cosmic energies.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onAskOracleAboutPersonalChart,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = DeepSpace),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Ask AI Oracle About My Personal Chart", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // SECTION 2: SAVED ADDITIONAL CHARTS VAULT (Friends & Family)
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Saved Natal Charts Vault (${natalCharts.size})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Saved charts for friends, partners, and family",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onOpenAddChartDialog,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("add_natal_chart_button")
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("+ Add New Natal Chart", fontWeight = FontWeight.Bold)
                }
            }
        }

        if (natalCharts.isEmpty()) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MysticViolet.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("No saved additional natal charts yet", style = MaterialTheme.typography.titleSmall, color = Color.White)
                        Text(
                            "Create natal charts for partners, friends, or family to store their placements and run synastry match comparisons.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        } else {
            items(natalCharts) { chart ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CelestialGold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = chart.personName,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "${sdf.format(Date(chart.birthDateMillis))} • ${chart.birthTime} • ${chart.birthCity}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.6f)
                                )
                            }

                            IconButton(onClick = { onDeleteChart(chart.id) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ShadowRose)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DeepSpace)
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("☀️ ${chart.sunSign.displayName}", style = MaterialTheme.typography.bodySmall, color = CelestialGold, fontWeight = FontWeight.Bold)
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DeepSpace)
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("🌙 ${chart.moonSign.displayName}", style = MaterialTheme.typography.bodySmall, color = NebulaTeal, fontWeight = FontWeight.Bold)
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DeepSpace)
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("🌅 ${chart.risingSign.displayName}", style = MaterialTheme.typography.bodySmall, color = ShadowRose, fontWeight = FontWeight.Bold)
                            }
                        }

                        if (chart.notes.isNotBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Notes: ${chart.notes}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = { onUpdateSigns(chart.sunSign, chart.moonSign, chart.risingSign) }) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Set Active Trinity", color = CelestialGold, fontWeight = FontWeight.Bold)
                            }

                            TextButton(onClick = { onAskOracleAboutChart(chart) }) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = NebulaTeal, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Ask AI Oracle", color = NebulaTeal, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNatalChartDialog(
    onDismiss: () -> Unit,
    onSave: (name: String, dobMillis: Long, timeStr: String, cityStr: String, notes: String) -> Unit
) {
    var nameFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var dobMillis by remember { mutableStateOf(880000000000L) }
    var timeStr by remember { mutableStateOf("12:00 PM") }
    var cityStr by remember { mutableStateOf("Los Angeles, USA") }
    var notes by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    val sdf = remember { SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) }
    var dateText by remember { mutableStateOf(sdf.format(Date(dobMillis))) }

    LaunchedEffect(dobMillis) {
        dateText = sdf.format(Date(dobMillis))
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CosmicPurple,
        title = {
            Text("Create New Natal Chart", color = CelestialGold, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = nameFieldValue,
                    onValueChange = { nameFieldValue = it },
                    label = { Text("Person's Name", color = Color.White.copy(alpha = 0.7f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = dateText,
                    onValueChange = { typed ->
                        dateText = typed
                        val parsed = parseDateStringToMillis(typed)
                        if (parsed != null) {
                            dobMillis = parsed
                        }
                    },
                    label = { Text("Date of Birth (e.g. Nov 19, 1997 or 11/19/1997)", color = Color.White.copy(alpha = 0.7f)) },
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Open Calendar", tint = CelestialGold)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = timeStr,
                    onValueChange = { timeStr = it },
                    label = { Text("Birth Time", color = Color.White.copy(alpha = 0.7f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = cityStr,
                    onValueChange = { cityStr = it },
                    label = { Text("Birth City & Country", color = Color.White.copy(alpha = 0.7f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = MysticViolet,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Optional Notes", color = Color.White.copy(alpha = 0.7f)) },
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
                    if (nameFieldValue.text.isNotBlank()) {
                        val finalMillis = parseDateStringToMillis(dateText) ?: dobMillis
                        onSave(nameFieldValue.text, finalMillis, timeStr, cityStr, notes)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace)
            ) {
                Text("Save Natal Chart", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.White.copy(alpha = 0.7f))
            }
        }
    )

    if (showDatePicker) {
        BirthDatePickerDialog(
            initialMillis = dobMillis,
            onDismiss = { showDatePicker = false },
            onDateSelected = { selected ->
                dobMillis = selected
                dateText = sdf.format(Date(selected))
                showDatePicker = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDatePickerDialog(
    initialMillis: Long,
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { onDateSelected(it) }
            }) {
                Text("OK", color = CelestialGold, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.White.copy(alpha = 0.7f))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstroHomepageTab(
    profile: AstrologyProfile?,
    sunSign: ZodiacSign,
    moonSign: ZodiacSign,
    risingSign: ZodiacSign,
    onUpdateSigns: (ZodiacSign, ZodiacSign, ZodiacSign) -> Unit,
    onOpenEditPersonalChartDialog: () -> Unit,
    onNavigateToTab: (Int) -> Unit,
    onNavigateToReports: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var showEditBigThree by remember { mutableStateOf(false) }
    val sdf = remember { SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) }
    val dobFormatted = remember(profile?.birthDateMillis) {
        profile?.birthDateMillis?.takeIf { it > 0L }?.let { sdf.format(Date(it)) } ?: "11/19/1997"
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Personal Cosmic Identity & Transit Summary Card
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, CelestialGold.copy(alpha = 0.6f), RoundedCornerShape(24.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(CelestialGold.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(24.dp))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "MY PERSONAL NATAL CHART",
                        style = MaterialTheme.typography.labelSmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (profile?.isConfigured == true) (profile.birthCity.ifBlank { "Personal Profile" }) else "Personal Natal Identity",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = if (profile?.isConfigured == true && profile.birthDateMillis > 0L) "$dobFormatted at ${profile.birthTime.ifBlank { "12:00 PM" }}" else "Birth details configured in Settings",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onOpenEditPersonalChartDialog,
                        colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Edit Personal Chart & Big Three", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Big Three Badges
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BigThreeBadge(
                            title = "Sun ☀️",
                            signName = sunSign.displayName,
                            symbol = sunSign.symbol,
                            element = sunSign.element.displayName,
                            modifier = Modifier.fillMaxWidth()
                        )
                        BigThreeBadge(
                            title = "Moon 🌙",
                            signName = moonSign.displayName,
                            symbol = moonSign.symbol,
                            element = moonSign.element.displayName,
                            modifier = Modifier.fillMaxWidth()
                        )
                        BigThreeBadge(
                            title = "Rising 🌅",
                            signName = risingSign.displayName,
                            symbol = risingSign.symbol,
                            element = risingSign.element.displayName,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Live Synthesis Summary
                    val synthesisText = remember(sunSign, moonSign, risingSign, profile) {
                        "Your Sun in ${sunSign.displayName} (${sunSign.symbol}) drives core vitality and soul purpose. Combined with Moon in ${moonSign.displayName} (${moonSign.symbol}) shaping emotional intuition, and Ascendant in ${risingSign.displayName} (${risingSign.symbol}) projecting your outer aura in ${profile?.birthCity ?: "your birth location"}, your Big 3 harmonizes ${sunSign.element.displayName}, ${moonSign.element.displayName}, and ${risingSign.element.displayName} cosmic energies."
                    }

                    Text(
                        text = synthesisText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f),
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Element Balance Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(DeepSpace)
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val elements = listOf(sunSign.element, moonSign.element, risingSign.element)
                        val fireCount = elements.count { it.displayName == "Fire" }
                        val earthCount = elements.count { it.displayName == "Earth" }
                        val airCount = elements.count { it.displayName == "Air" }
                        val waterCount = elements.count { it.displayName == "Water" }

                        ElementCountChip(label = "🔥 Fire", count = fireCount)
                        ElementCountChip(label = "🌍 Earth", count = earthCount)
                        ElementCountChip(label = "💨 Air", count = airCount)
                        ElementCountChip(label = "🌊 Water", count = waterCount)
                    }
                }
            }
        }

        // 2. Zodiac Identity & Traits Blueprint Box (Moved from Home Screen)
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .testTag("zodiac_and_traits_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
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
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Keywords Row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
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

                    val trait1 = "High Reflective Intelligence & Depth"
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

        // Quick Sign Customization Dropdown Editor
        if (showEditBigThree) {
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MysticViolet, RoundedCornerShape(20.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Tune Your Big Three Signs",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = CelestialGold
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        SignSelectorDropdown("Sun Sign (Identity & Ego)", sunSign) { newSun ->
                            onUpdateSigns(newSun, moonSign, risingSign)
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        SignSelectorDropdown("Moon Sign (Emotions & Intuition)", moonSign) { newMoon ->
                            onUpdateSigns(sunSign, newMoon, risingSign)
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        SignSelectorDropdown("Rising Sign / Ascendant (Outer Mask)", risingSign) { newRising ->
                            onUpdateSigns(sunSign, moonSign, newRising)
                        }
                    }
                }
            }
        }

        // Astrology Services Options Hub
        item {
            Text(
                text = "Astrology Services & Portal",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Option 1: Natal Chart & Big Three Vault
        item {
            AstroServiceOptionCard(
                icon = Icons.Default.Nightlight,
                title = "🪐 Natal Charts & Placements",
                subtitle = "View your interactive birth chart wheel, house positions, and manage natal charts for family & friends.",
                tagText = "Chart Vault",
                buttonText = "View Natal Charts",
                onClick = { onNavigateToTab(1) },
                testTag = "goto_natal_charts_service_button"
            )
        }

        // Option 2: Tarot Card Reading Generator (Moved ABOVE Oracle AI Box)
        item {
            AstroServiceOptionCard(
                icon = Icons.Default.Casino,
                title = "🃏 Tarot Card Reading Generator",
                subtitle = "Draw 1-card daily guidance, 3-card past/present/future spreads, and psychological reflections with archetypal wisdom.",
                tagText = "78-Card Deck",
                buttonText = "Open Tarot Generator",
                onClick = { onNavigateToTab(2) },
                testTag = "goto_tarot_service_button"
            )
        }

        // Option 3: AI Cosmic Oracle
        item {
            AstroServiceOptionCard(
                icon = Icons.Default.AutoAwesome,
                title = "🔮 AI Cosmic Oracle",
                subtitle = "Ask Gemini AI personalized astrological questions, real-time transit insights, and daily predictions.",
                tagText = "AI Powered",
                buttonText = "Consult AI Oracle",
                onClick = { onNavigateToTab(3) },
                testTag = "goto_oracle_service_button"
            )
        }

        // Option 4: Birthdate Synastry & Compatibility (Premium)
        item {
            AstroServiceOptionCard(
                icon = Icons.Default.Favorite,
                title = "💖 Birthdate Synastry Match",
                subtitle = "Calculate precise planetary compatibility and love chemistry scores between two birthdates, times & cities.",
                tagText = "Psyche+ Premium",
                buttonText = "Check Synastry Match",
                onClick = { onNavigateToTab(4) },
                testTag = "goto_synastry_service_button"
            )
        }

        // Option 5: Unified Mind & Cosmos AI Report
        item {
            AstroServiceOptionCard(
                icon = Icons.Default.Psychology,
                title = "✨ Mind & Cosmos Synthesis",
                subtitle = "Synthesize psychological test scores (MBTI, Enneagram) with natal astrology charts into a 7-section deep report.",
                tagText = "Deep AI Report",
                buttonText = "Generate Deep AI Report",
                onClick = { onNavigateToReports() },
                testTag = "goto_synthesis_report_service_button"
            )
        }
    }
}

@Composable
fun BigThreeBadge(
    title: String,
    signName: String,
    symbol: String,
    element: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(DeepSpace)
            .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$symbol $signName",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = CelestialGold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = element,
                style = MaterialTheme.typography.labelSmall,
                color = NebulaTeal
            )
        }
    }
}

@Composable
fun ElementCountChip(label: String, count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.8f)
        )
        Text(
            text = "$count",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = CelestialGold
        )
    }
}

@Composable
fun AstroServiceOptionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    tagText: String,
    buttonText: String,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MysticViolet, RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(CelestialGold.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(22.dp))
            }

            if (tagText.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CelestialGold.copy(alpha = 0.2f))
                        .border(1.dp, CelestialGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = tagText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.8f),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 44.dp)
                    .testTag(testTag)
            ) {
                Text(
                    text = buttonText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun MindAndCosmosReportTab(
    savedReports: List<DeepSynthesisReport>,
    isGenerating: Boolean,
    gemsBalance: Int,
    isPremium: Boolean,
    onGenerateReportClicked: () -> Unit,
    onSelectReport: (DeepSynthesisReport?) -> Unit,
    onToggleBookmark: (String, Boolean) -> Unit
) {
    val reports = savedReports.filter { !it.archetypeSummary.contains("Meta-Analysis") && !it.title.contains("Master") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Mind & Cosmos AI Report",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Deep insights combining test scores & natal charts.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(CelestialGold.copy(alpha = 0.2f))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (isPremium) "✨ Psyche+ Unlimited" else if (gemsBalance >= 10) "1 Report Credit" else "$1.00 / Report",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = CelestialGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Generate Report CTA Card
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MysticViolet, RoundedCornerShape(24.dp))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(CelestialGold.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(28.dp))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Generate Unified Mind & Cosmos Report",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Uses Gemini AI to craft a personalized 7-section breakdown and actionable 7-day self-improvement plan combining test results & astrological natal chart.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.75f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Pricing: $1.00 per Report or Unlimited with Psyche+ Subscription",
                        style = MaterialTheme.typography.labelMedium,
                        color = CelestialGold,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (isGenerating) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = CelestialGold)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Synthesizing Report with Gemini...", color = CelestialGold, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        val canGenerate = isPremium || gemsBalance >= 10
                        Button(
                            onClick = onGenerateReportClicked,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (canGenerate) CelestialGold else MysticViolet,
                                contentColor = if (canGenerate) DeepSpace else Color.White
                            ),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("generate_new_synthesis_report_button")
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when {
                                    isPremium -> "Synthesize Report (Included with Psyche+)"
                                    gemsBalance >= 10 -> "Synthesize Report (1 Credit Ready)"
                                    else -> "Order AI Synthesis Report ($1.00 / Psyche+ Sub)"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Saved Mind & Cosmos Reports (${reports.size})",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        if (reports.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Psychology, contentDescription = null, tint = Color.White.copy(alpha = 0.3f), modifier = Modifier.size(56.dp))
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("No Mind & Cosmos reports generated yet.", color = Color.White.copy(alpha = 0.6f))
                        Text("Tap 'Synthesize Report' above to generate one!", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.4f))
                    }
                }
            }
        } else {
            items(reports) { report ->
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .border(1.dp, MysticViolet.copy(alpha = 0.35f), RoundedCornerShape(18.dp))
                        .clickable { onSelectReport(report) }
                        .testTag("saved_report_card_${report.id}")
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

