package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import org.json.JSONArray
import org.json.JSONObject

@Composable
fun RawDataExportDialog(
    testResults: List<TestResultEntity>,
    astrologyProfile: AstrologyProfile?,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    val jsonFormatted = rememberRawJsonExport(testResults, astrologyProfile)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.85f),
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
                            Text(
                                text = "Download Raw Test Data",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFFA5D6A7).copy(alpha = 0.2f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "100% FREE",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFFA5D6A7),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            }
                        }
                        Text(
                            text = "Full export of assessment questions, selected answers & trait scores (JSON)",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Scrollable JSON container
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .border(1.dp, MysticViolet.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .background(CosmicPurple)
                        .padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = jsonFormatted,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = Color(0xFFA5D6A7),
                            lineHeight = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Raw Test Data JSON", jsonFormatted)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Copied raw JSON data to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy JSON", color = CelestialGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, jsonFormatted)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, "Share or Save Raw Test Data")
                            context.startActivity(shareIntent)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Share / Save", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

fun rememberRawJsonExport(
    testResults: List<TestResultEntity>,
    astrologyProfile: AstrologyProfile?
): String {
    val rootObj = JSONObject()
    rootObj.put("exportTimestamp", System.currentTimeMillis())
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss UTC", java.util.Locale.US)
    sdf.timeZone = java.util.TimeZone.getTimeZone("UTC")
    rootObj.put("exportDateFormatted", sdf.format(java.util.Date()))
    rootObj.put("appName", "InsideMe - Mind & Astrology Explorer")
    rootObj.put("exportTier", "Free Raw Data Export (Unrestricted)")

    val profileObj = JSONObject()
    if (astrologyProfile != null) {
        profileObj.put("sunSign", astrologyProfile.sunSign.displayName)
        profileObj.put("moonSign", astrologyProfile.moonSign.displayName)
        profileObj.put("risingSign", astrologyProfile.risingSign.displayName)
        profileObj.put("birthDateMillis", astrologyProfile.birthDateMillis)
        profileObj.put("birthTime", astrologyProfile.birthTime)
        profileObj.put("birthCity", astrologyProfile.birthCity)
    } else {
        profileObj.put("sunSign", "Scorpio")
        profileObj.put("moonSign", "Pisces")
        profileObj.put("risingSign", "Cancer")
    }
    rootObj.put("astrologyProfile", profileObj)

    val resultsArr = JSONArray()
    testResults.forEach { entity ->
        val itemObj = JSONObject()
        itemObj.put("testId", entity.testId)
        itemObj.put("testTitle", entity.testTitle)
        itemObj.put("completedAtMillis", entity.completedAtMillis)
        itemObj.put("completedAtFormatted", sdf.format(java.util.Date(entity.completedAtMillis)))
        itemObj.put("dominantArchetype", entity.dominantArchetype)
        itemObj.put("summaryText", entity.summaryText)
        
        try {
            itemObj.put("traitScores", JSONObject(entity.scoresJson))
        } catch (e: Exception) {
            itemObj.put("scoresRaw", entity.scoresJson)
        }

        if (entity.answersJson.isNotBlank()) {
            try {
                itemObj.put("questionAnswers", JSONArray(entity.answersJson))
            } catch (e: Exception) {
                itemObj.put("questionAnswersRaw", entity.answersJson)
            }
        } else {
            itemObj.put("questionAnswersNote", "Detailed answer log available for tests completed in current session.")
        }

        resultsArr.put(itemObj)
    }
    rootObj.put("completedAssessmentResults", resultsArr)

    return rootObj.toString(2)
}
