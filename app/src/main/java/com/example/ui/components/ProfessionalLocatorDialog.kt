package com.example.ui.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.BuildConfig
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class CareChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val isUser: Boolean,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Composable
fun ProfessionalLocatorDialog(
    onDismiss: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var inputText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    val initialAiGreeting = "Hello! I am your AI Care & Professional Locator Assistant. " +
            "To help locate qualified mental health professionals, therapists, or support services near you, " +
            "could you please tell me where you are located (City, State, or Zip Code) and what specific services or support you are seeking today (e.g., individual therapy, psychiatric evaluation, couples/family counseling, anxiety management, trauma care)?"

    val messages = remember {
        mutableStateListOf(
            CareChatMessage(isUser = false, text = initialAiGreeting)
        )
    }

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.88f),
            shape = RoundedCornerShape(24.dp),
            color = DeepSpace,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                // Header Bar
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
                                .background(NebulaTeal.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.MedicalServices, contentDescription = null, tint = NebulaTeal)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Locate a Professional",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                            Text(
                                text = "AI Mental Health Directory Assistant",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 11.sp
                            )
                        }
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Crisis Support & Mental Health Directory Resources Banner (from screenshots)
                Card(
                    colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.95f)),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CelestialGold.copy(alpha = 0.6f), RoundedCornerShape(14.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            Text("🆘", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Immediate 24/7 Support: If you or someone you know is in distress, call or text 988 to reach the Suicide & Crisis Lifeline, or text HOME to 741741.",
                                color = Color.White,
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 14.sp
                            )
                        }
                        Text(
                            text = "• SAMHSA National Helpline: Call 1-800-662-HELP (4357) or visit findtreatment.gov for free, confidential, 24/7 treatment referral.",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 10.sp,
                            lineHeight = 13.sp
                        )
                        Text(
                            text = "• Zocdoc / Open Path Collective: Search local mental health providers & affordable therapy collectives.",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 10.sp,
                            lineHeight = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Chat Messages List
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .border(1.dp, MysticViolet.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                        .background(CosmicPurple.copy(alpha = 0.6f))
                        .padding(10.dp)
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(messages, key = { it.id }) { msg ->
                            CareMessageBubble(msg)
                        }

                        if (isLoading) {
                            item {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = CelestialGold,
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Finding local professional resources...",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Mandatory Disclaimer Note
                Text(
                    text = "*This app is intended for entertainment and educational purposes and results should be discussed with a professional for diagnosis and treatment. In crisis, call 988.",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 9.sp,
                    lineHeight = 12.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Box & Send
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = { Text("E.g., Austin TX, looking for CBT anxiety therapist...", fontSize = 12.sp, color = Color.White.copy(alpha = 0.5f)) },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CelestialGold,
                            unfocusedBorderColor = MysticViolet,
                            focusedContainerColor = CosmicPurple,
                            unfocusedContainerColor = CosmicPurple,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            val userQuery = inputText.trim()
                            if (userQuery.isNotEmpty() && !isLoading) {
                                messages.add(CareChatMessage(isUser = true, text = userQuery))
                                inputText = ""
                                isLoading = true

                                coroutineScope.launch {
                                    val aiReply = fetchCareLocatorResponse(messages, userQuery)
                                    messages.add(CareChatMessage(isUser = false, text = aiReply))
                                    isLoading = false
                                }
                            }
                        },
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (inputText.isBlank() || isLoading) Color.Gray.copy(alpha = 0.3f) else CelestialGold)
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun CareMessageBubble(message: CareChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isUser) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(NebulaTeal.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.MedicalServices, contentDescription = null, tint = NebulaTeal, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Card(
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isUser) 16.dp else 4.dp,
                bottomEnd = if (message.isUser) 4.dp else 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser) MysticViolet else CosmicPurple
            ),
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .border(
                    1.dp,
                    if (message.isUser) CelestialGold.copy(alpha = 0.4f) else MysticViolet.copy(alpha = 0.5f),
                    RoundedCornerShape(16.dp)
                )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }

        if (message.isUser) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(CelestialGold.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(16.dp))
            }
        }
    }
}

suspend fun fetchCareLocatorResponse(history: List<CareChatMessage>, userQuery: String): String = withContext(Dispatchers.IO) {
    val apiKey = BuildConfig.GEMINI_API_KEY
    if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
        return@withContext generateFallbackCareResponse(userQuery)
    }

    val systemPrompt = "You are an empathetic, professional AI Care & Professional Locator Assistant for mental health. " +
            "Your role is to guide the user to locate verified mental health professionals, therapists, counselors, or specialized care near their location.\n\n" +
            "User's query: \"$userQuery\"\n\n" +
            "Provide a warm, highly actionable response (200-350 words) with:\n" +
            "1. Acknowledgement of their location and specific care request.\n" +
            "2. Direct, reputable provider directory suggestions (Psychology Today directory, SAMHSA Helpline 1-800-662-4357, Zocdoc, local community health centers).\n" +
            "3. 3 key questions to ask when contacting potential care providers (licensure, specialty experience, sliding scale rates).\n" +
            "4. Immediate crisis resources (988 Suicide & Crisis Lifeline, Crisis Text Line 741741).\n" +
            "Maintain a supportive, clear tone with bold headings."

    try {
        val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

        val rootObj = JSONObject().apply {
            put("contents", JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", JSONArray().apply {
                        put(JSONObject().apply { put("text", systemPrompt) })
                    })
                })
            })
        }
        val requestBody = RequestBody.create("application/json".toMediaType(), rootObj.toString())
        val request = Request.Builder()
            .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
            .post(requestBody)
            .build()

        val response = client.newCall(request).execute()
        val responseString = response.body?.string() ?: ""

        if (response.isSuccessful && responseString.isNotEmpty()) {
            val respJson = JSONObject(responseString)
            val candidates = respJson.optJSONArray("candidates")
            if (candidates != null && candidates.length() > 0) {
                val text = candidates.getJSONObject(0).optJSONObject("content")?.optJSONArray("parts")?.getJSONObject(0)?.optString("text", "") ?: ""
                if (text.isNotEmpty()) return@withContext text
            }
        }
        return@withContext generateFallbackCareResponse(userQuery)
    } catch (e: Exception) {
        e.printStackTrace()
        return@withContext generateFallbackCareResponse(userQuery)
    }
}

fun generateFallbackCareResponse(userQuery: String): String {
    return "🏥 **Professional Care & Resource Locator Guide**\n\n" +
            "Thank you for sharing your location and care needs regarding *\"$userQuery\"*. Connecting with a licensed mental health professional is an essential step toward personalized diagnosis and support.\n\n" +
            "### 🔍 Recommended Verified Directories & Search Tools\n" +
            "• **Psychology Today Therapist Directory:** Filter licensed counselors, psychologists, and psychiatrists by your zip code, insurance, and specific issue.\n" +
            "• **SAMHSA National Helpline:** Call **1-800-662-HELP (4357)** or visit findtreatment.gov for free, confidential, 24/7 treatment referral.\n" +
            "• **Zocdoc / Open Path Collective:** Search local in-person and telehealth therapists offering sliding-scale options ($30–$80/session).\n\n" +
            "### ❓ 3 Important Questions to Ask Prospective Therapists\n" +
            "1. *'What is your experience and clinical orientation treating my specific concerns?'*\n" +
            "2. *'Do you accept my health insurance, or do you offer superbills / sliding scale fees?'*\n" +
            "3. *'What does a typical treatment plan and intake session look like?'*\n\n" +
            "🆘 **Immediate 24/7 Support:** If you or someone you know is in distress, call or text **988** to reach the Suicide & Crisis Lifeline, or text **HOME to 741741**."
}
