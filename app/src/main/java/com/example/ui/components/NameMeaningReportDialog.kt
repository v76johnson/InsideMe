package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.NameMeaningReport
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal

@Composable
fun NameMeaningReportDialog(
    report: NameMeaningReport?,
    isGenerating: Boolean,
    currentMainName: String = "",
    savedNames: List<String> = emptyList(),
    onAnalyzeName: (String) -> Unit,
    onSetMainName: (String) -> Unit = {},
    onSaveNameAddition: (String) -> Unit = {},
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val initialName = report?.name ?: ""
    var searchFieldValue by remember { mutableStateOf(TextFieldValue(text = initialName, selection = TextRange(initialName.length))) }
    var actionToastMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(report?.name) {
        val reportName = report?.name ?: ""
        if (reportName.isNotBlank() && reportName != searchFieldValue.text) {
            searchFieldValue = TextFieldValue(text = reportName, selection = TextRange(reportName.length))
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(0.92f)
                .heightIn(max = 680.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(1.5.dp, CelestialGold, RoundedCornerShape(24.dp)),
            color = DeepSpace
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Top Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(CelestialGold.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.HistoryEdu,
                                contentDescription = null,
                                tint = CelestialGold,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "NAME MEANING & INTENT REPORT",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = CelestialGold,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "Onomastics & Personality Blueprint",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("close_name_report_dialog")
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White.copy(alpha = 0.7f))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Quick selector for main name and saved name additions
                val allNameChips = (listOf(currentMainName).filter { it.isNotBlank() } + savedNames).distinct()
                if (allNameChips.isNotEmpty()) {
                    Text(
                        text = "QUICK LOOKUP SAVED NAMES:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        allNameChips.take(4).forEach { nameChip ->
                            val isMain = nameChip == currentMainName
                            Surface(
                                onClick = {
                                    searchFieldValue = TextFieldValue(text = nameChip, selection = TextRange(nameChip.length))
                                    onAnalyzeName(nameChip)
                                },
                                shape = RoundedCornerShape(12.dp),
                                color = if (isMain) CelestialGold.copy(alpha = 0.25f) else CosmicPurple,
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (isMain) CelestialGold else MysticViolet
                                ),
                                modifier = Modifier.padding(vertical = 2.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (isMain) {
                                        Icon(
                                            Icons.Default.Star,
                                            contentDescription = null,
                                            tint = CelestialGold,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                    }
                                    Text(
                                        text = nameChip,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isMain) CelestialGold else Color.White
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Input Box to Analyze Any Name
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = searchFieldValue,
                        onValueChange = { searchFieldValue = it },
                        placeholder = { Text("Enter any name to analyze...", color = Color.White.copy(alpha = 0.5f)) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("name_report_search_input"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CelestialGold,
                            unfocusedBorderColor = CelestialGold.copy(alpha = 0.4f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = CosmicPurple,
                            unfocusedContainerColor = CosmicPurple
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { if (searchFieldValue.text.isNotBlank()) onAnalyzeName(searchFieldValue.text) },
                        colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !isGenerating && searchFieldValue.text.isNotBlank(),
                        modifier = Modifier.testTag("analyze_name_search_button")
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Analyze", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Action Toast Banner if set main or saved
                AnimatedVisibility(visible = actionToastMessage != null) {
                    actionToastMessage?.let { msg ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            shape = RoundedCornerShape(8.dp),
                            color = NebulaTeal.copy(alpha = 0.2f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, NebulaTeal)
                        ) {
                            Text(
                                text = msg,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = NebulaTeal,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                // Body Content
                if (isGenerating) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = CelestialGold)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Locating etymologies & analyzing parental intention psychology...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.8f),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                } else if (report != null) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Title Banner for analyzed name
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, CelestialGold.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "ANALYSIS FOR: ${report.name.uppercase()}",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = CelestialGold,
                                        letterSpacing = 1.sp
                                    )
                                    if (report.name.trim().equals(currentMainName.trim(), ignoreCase = true)) {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = CelestialGold.copy(alpha = 0.2f),
                                            border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold)
                                        ) {
                                            Text(
                                                text = "⭐ Your Main Name",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = CelestialGold,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = report.parentalIntentCategory,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = NebulaTeal
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = report.numerologicalVibration,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White.copy(alpha = 0.9f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                // Action Options for analyzed name
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            onSetMainName(report.name)
                                            actionToastMessage = "Set '${report.name}' as your main preferred name! ⭐"
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier.weight(1f).testTag("set_as_main_name_button")
                                    ) {
                                        Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Set as Main", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }

                                    Button(
                                        onClick = {
                                            onSaveNameAddition(report.name)
                                            actionToastMessage = "Saved '${report.name}' to your name additions! 📌"
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier.weight(1f).testTag("save_name_addition_button")
                                    ) {
                                        Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Save Addition", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Section 1: Etymologies & Linguistic Origins
                        Text(
                            text = "🌍 LINGUISTIC ORIGINS & ETYMOLOGIES",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = CelestialGold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        report.etymologies.forEach { ety ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.7f)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .border(1.dp, MysticViolet, RoundedCornerShape(12.dp))
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = ety.languageOrCulture,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = NebulaTeal
                                        )
                                        Text(
                                            text = "• ${ety.literalMeaning}",
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Bold,
                                            color = CelestialGold
                                        )
                                    }
                                    if (ety.historicalContext.isNotBlank()) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = ety.historicalContext,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.White.copy(alpha = 0.85f),
                                            lineHeight = 18.sp
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Section 2: Parental Naming Intention & Subconscious Blueprint
                        Text(
                            text = "🧬 NAMING SOURCE & PARENTAL INTENTION",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = CelestialGold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.7f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MysticViolet, RoundedCornerShape(12.dp))
                        ) {
                            Text(
                                text = report.parentalIntentPsychology,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 19.sp,
                                modifier = Modifier.padding(14.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Section 3: Personality Effects & Cognitive Tendencies
                        Text(
                            text = "⚡ PERSONALITY & BEHAVIORAL EFFECTS",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = CelestialGold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.7f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MysticViolet, RoundedCornerShape(12.dp))
                        ) {
                            Text(
                                text = report.personalityEffects,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 19.sp,
                                modifier = Modifier.padding(14.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Section 4: Shadow Work & Personal Sovereignty
                        Text(
                            text = "🕊️ SHADOW WORK & HARMONIZATION",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = CelestialGold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.7f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MysticViolet, RoundedCornerShape(12.dp))
                        ) {
                            Text(
                                text = report.shadowIntegrationAdvice,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 19.sp,
                                modifier = Modifier.padding(14.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom Close Button
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("dismiss_name_report_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = CosmicPurple, contentColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Done / Close", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
