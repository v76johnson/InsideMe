package com.example.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.data.model.DeepSynthesisReport
import com.example.data.model.UserSubscription
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal

@Composable
fun SettingsDialog(
    userSubscription: UserSubscription,
    testResults: List<TestResultEntity>,
    astrologyProfile: AstrologyProfile?,
    savedReports: List<DeepSynthesisReport> = emptyList(),
    onDismiss: () -> Unit,
    onNavigateToUpgrade: () -> Unit = {},
    onCancelSubscription: () -> Unit = {},
    onOpenReviewModal: () -> Unit = {},
    onResetData: () -> Unit = {},
    onUpdateUserName: (String) -> Unit = {},
    onGenerateNameReport: (String) -> Unit = {},
    onRemoveSavedName: (String) -> Unit = {},
    onAddSavedName: (String) -> Unit = {},
    onRedeemPromoCode: (String) -> Boolean = { false }
) {
    var showExportDialog by remember { mutableStateOf(false) }
    var showReportExportDialog by remember { mutableStateOf(false) }
    var showResetConfirm by remember { mutableStateOf(false) }
    var showCancelSubscriptionConfirm by remember { mutableStateOf(false) }
    var showFeedbackDialog by remember { mutableStateOf(false) }
    var feedbackCategory by remember { mutableStateOf("General Feedback") }
    var feedbackMessageText by remember { mutableStateOf("") }

    var promoCodeFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var promoCodeMessage by remember { mutableStateOf<String?>(null) }
    var promoCodeSuccess by remember { mutableStateOf(false) }

    var dailyNotificationsEnabled by remember { mutableStateOf(true) }
    var cosmicThemeEnabled by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val initialName = astrologyProfile?.userName ?: ""
    var userNameFieldValue by remember { mutableStateOf(TextFieldValue(text = initialName, selection = TextRange(initialName.length))) }
    var isNameSaved by remember { mutableStateOf(false) }

    LaunchedEffect(astrologyProfile?.userName) {
        val currentProfileName = astrologyProfile?.userName ?: ""
        if (currentProfileName.isNotBlank() && currentProfileName != userNameFieldValue.text) {
            userNameFieldValue = TextFieldValue(text = currentProfileName, selection = TextRange(currentProfileName.length))
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepSpace.copy(alpha = 0.95f))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CosmicPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, CelestialGold, RoundedCornerShape(24.dp))
                    .testTag("settings_menu_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Header
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
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(CelestialGold.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Settings, contentDescription = null, tint = CelestialGold)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Settings & Preferences",
                                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "System, Account & Support Controls",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.6f)
                                )
                            }
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.testTag("close_settings_button")
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close Settings", tint = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // SECTION 0: USER PROFILE & NAME PERSONALIZATION
                    CollapsibleBlock(
                        title = "User Profile & Personalization",
                        subtitle = if (userNameFieldValue.text.isNotBlank()) "Addressed as: ${userNameFieldValue.text}" else "Set your name to personalize your reports",
                        icon = Icons.Default.Star,
                        iconTint = CelestialGold,
                        initialExpanded = true
                    ) {
                        Column {
                            Text(
                                text = "Personalize How You Are Addressed",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = userNameFieldValue,
                                    onValueChange = { newValue ->
                                        userNameFieldValue = newValue
                                        isNameSaved = false
                                    },
                                    placeholder = { Text("Enter your preferred name...", color = Color.White.copy(alpha = 0.5f)) },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("settings_user_name_input"),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = CelestialGold,
                                        unfocusedBorderColor = CelestialGold.copy(alpha = 0.4f),
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        onUpdateUserName(userNameFieldValue.text)
                                        isNameSaved = true
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.testTag("settings_save_name_button")
                                ) {
                                    Text(if (isNameSaved) "Saved ✓" else "Save", fontWeight = FontWeight.Bold)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedButton(
                                onClick = {
                                    if (userNameFieldValue.text.isNotBlank()) onUpdateUserName(userNameFieldValue.text)
                                    onGenerateNameReport(userNameFieldValue.text.ifBlank { "Seeker" })
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("settings_generate_name_report_button"),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = CelestialGold),
                                border = BorderStroke(1.dp, CelestialGold),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("📜 View Name Meaning & Intent Report", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            // SAVED NAME ADDITIONS / COMPANION NAMES
                            val savedAdditions = astrologyProfile?.savedNameAdditions ?: emptyList()
                            if (savedAdditions.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(14.dp))
                                Text(
                                    text = "SAVED NAME ADDITIONS & COMPANIONS",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = CelestialGold,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.height(6.dp))

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    savedAdditions.forEach { nameItem ->
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = CosmicPurple,
                                            border = BorderStroke(1.dp, MysticViolet),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.weight(1f)
                                                ) {
                                                    Icon(
                                                        Icons.Default.Star,
                                                        contentDescription = null,
                                                        tint = NebulaTeal,
                                                        modifier = Modifier.size(14.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                    Text(
                                                        text = nameItem,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }

                                                Row(
                                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    TextButton(
                                                        onClick = { onGenerateNameReport(nameItem) },
                                                        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                                                    ) {
                                                        Text("Report", color = CelestialGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                                    }

                                                    if (!nameItem.equals(userNameFieldValue.text, ignoreCase = true)) {
                                                        TextButton(
                                                            onClick = {
                                                                onUpdateUserName(nameItem)
                                                                userNameFieldValue = TextFieldValue(text = nameItem, selection = TextRange(nameItem.length))
                                                            },
                                                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                                                        ) {
                                                            Text("Set Main", color = NebulaTeal, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                                        }
                                                    }

                                                    IconButton(
                                                        onClick = { onRemoveSavedName(nameItem) },
                                                        modifier = Modifier.size(24.dp)
                                                    ) {
                                                        Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.White.copy(alpha = 0.6f), modifier = Modifier.size(14.dp))
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SECTION 1: SUBSCRIPTION & MEMBERSHIP MANAGEMENT
                    CollapsibleBlock(
                        title = "Subscription & Membership",
                        subtitle = if (userSubscription.isPremium) "Tier: ${userSubscription.tier.title} (${userSubscription.tier.priceDisplay})" else "Free Tier (Gems: ${userSubscription.gemsBalance})",
                        icon = Icons.Default.WorkspacePremium,
                        iconTint = CelestialGold,
                        initialExpanded = true
                    ) {
                        Column {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "Status: ${if (userSubscription.isPremium) userSubscription.tier.title else "Free Tier"}",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                        Text(
                                            text = if (userSubscription.isPremium)
                                                "${userSubscription.tier.priceDisplay} • Unlimited AI Reports"
                                            else
                                                "Gems: ${userSubscription.gemsBalance} • Standard Access",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = CelestialGold
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (userSubscription.isPremium) NebulaTeal else MysticViolet.copy(alpha = 0.5f))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = if (userSubscription.isPremium) "ACTIVE" else "FREE",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (userSubscription.isPremium) Color.Black else Color.White
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            onDismiss()
                                            onNavigateToUpgrade()
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                                        shape = RoundedCornerShape(12.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(44.dp)
                                            .testTag("settings_upgrade_button")
                                    ) {
                                        Icon(Icons.Default.WorkspacePremium, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            if (userSubscription.isPremium) "Switch Tier" else "Upgrade Tier",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                    }

                                    if (userSubscription.isPremium) {
                                        OutlinedButton(
                                            onClick = { showCancelSubscriptionConfirm = true },
                                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF6B6B)),
                                            border = BorderStroke(1.dp, Color(0xFFFF6B6B)),
                                            shape = RoundedCornerShape(12.dp),
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(44.dp)
                                                .testTag("settings_cancel_subscription_button")
                                        ) {
                                            Icon(Icons.Default.Cancel, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color(0xFFFF6B6B))
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("Cancel Plan", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "PROMO CODE / BETA ACCESS",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = promoCodeFieldValue,
                                    onValueChange = {
                                        promoCodeFieldValue = it
                                        promoCodeMessage = null
                                    },
                                    placeholder = { Text("Enter code (e.g. betatest)", color = Color.White.copy(alpha = 0.45f), fontSize = 12.sp) },
                                    singleLine = true,
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("settings_promo_code_input"),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = CelestialGold,
                                        unfocusedBorderColor = CelestialGold.copy(alpha = 0.5f),
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        focusedContainerColor = CosmicPurple.copy(alpha = 0.5f),
                                        unfocusedContainerColor = CosmicPurple.copy(alpha = 0.3f)
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        val code = promoCodeFieldValue.text.trim()
                                        if (code.isNotBlank()) {
                                            val success = onRedeemPromoCode(code)
                                            if (success) {
                                                promoCodeSuccess = true
                                                promoCodeMessage = "🎉 Beta test code accepted! All features & premium unlocked!"
                                                promoCodeFieldValue = TextFieldValue("")
                                            } else {
                                                promoCodeSuccess = false
                                                promoCodeMessage = "❌ Invalid promo code. Please try again."
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                    shape = RoundedCornerShape(12.dp),
                                    enabled = promoCodeFieldValue.text.isNotBlank(),
                                    modifier = Modifier
                                        .height(50.dp)
                                        .testTag("settings_promo_code_submit_button")
                                ) {
                                    Text("Redeem", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }

                            promoCodeMessage?.let { msg ->
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = msg,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (promoCodeSuccess) NebulaTeal else Color(0xFFFF6B6B),
                                    modifier = Modifier.testTag("settings_promo_code_message")
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SECTION 2: APP PREFERENCES & NOTIFICATIONS
                    CollapsibleBlock(
                        title = "App Preferences & Notifications",
                        subtitle = "Daily Affirmation Reminders & Theme Controls",
                        icon = Icons.Default.Notifications,
                        iconTint = NebulaTeal,
                        initialExpanded = false
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text("Daily Insights & Affirmations", color = Color.White, style = MaterialTheme.typography.bodyMedium)
                                }
                                Switch(
                                    checked = dailyNotificationsEnabled,
                                    onCheckedChange = { dailyNotificationsEnabled = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = DeepSpace,
                                        checkedTrackColor = NebulaTeal
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Palette, contentDescription = null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text("Deep Cosmic Palette", color = Color.White, style = MaterialTheme.typography.bodyMedium)
                                }
                                Switch(
                                    checked = cosmicThemeEnabled,
                                    onCheckedChange = { cosmicThemeEnabled = it },
                                    colors = SwitchDefaults.colors(
                                                                    )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SECTION 3: DATA EXPORT & LOCAL STORAGE
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.4f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MysticViolet.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Download, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Data Export & Storage Tools", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                                    Text("Export JSON Data, PDF Reports & Local Storage Management", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.6f))
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { showExportDialog = true },
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(1.dp, CelestialGold),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .testTag("settings_export_data_button")
                                ) {
                                    Icon(Icons.Default.Download, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(15.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Raw Data (.json)", color = CelestialGold, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }

                                Button(
                                    onClick = { showReportExportDialog = true },
                                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1.3f)
                                        .height(44.dp)
                                        .testTag("settings_export_reports_cloud_button")
                                ) {
                                    Icon(Icons.Default.CloudUpload, contentDescription = null, tint = Color.Black, modifier = Modifier.size(15.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Export Reports (PDF)", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedButton(
                                onClick = { showResetConfirm = true },
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFFF6B6B).copy(alpha = 0.6f)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp)
                                    .testTag("settings_reset_data_button")
                            ) {
                                Icon(Icons.Default.DeleteForever, contentDescription = null, tint = Color(0xFFFF6B6B), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Clear Local Assessment History", color = Color(0xFFFF6B6B), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SECTION 4: CONTACT, FEEDBACK & APP INFO
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.4f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MysticViolet.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Feedback, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Contact, Feedback & Support", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                                    Text("Send User Feedback, Email Support & App Information", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.6f))
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { showFeedbackDialog = true },
                                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .testTag("settings_send_feedback_btn")

                                ) {
                                    Icon(Icons.Default.Feedback, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Send Feedback", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }

                                Button(
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                                            data = Uri.parse("mailto:support@psycheapp.com")
                                            putExtra(Intent.EXTRA_SUBJECT, "InsideMe App Support & Inquiry")
                                        }
                                        try {
                                            context.startActivity(intent)
                                        } catch (_: Exception) {
                                            Toast.makeText(context, "Support email: support@psycheapp.com", Toast.LENGTH_LONG).show()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .testTag("settings_email_support_btn")
                                ) {
                                    Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Email Support", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedButton(
                                onClick = {
                                    onDismiss()
                                    onOpenReviewModal()
                                },
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, MysticViolet),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp)
                                    .testTag("settings_review_button")
                            ) {
                                Icon(Icons.Default.RateReview, contentDescription = null, tint = MysticViolet, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Rate & Review InsideMe App", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            Spacer(modifier = Modifier.height(12.dp))

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
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontSize = 11.sp,
                                        lineHeight = 15.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "InsideMe AI v2.4.0 • Built with Gemini 2.5 Flash",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.4f),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }

    if (showCancelSubscriptionConfirm) {
        AlertDialog(
            onDismissRequest = { showCancelSubscriptionConfirm = false },
            containerColor = CosmicPurple,
            titleContentColor = Color.White,
            textContentColor = Color.White.copy(alpha = 0.9f),
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = Color(0xFFFF6B6B))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cancel Subscription?", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Text("Are you sure you want to cancel your ${userSubscription.tier.title} subscription? You will revert to the Free Tier.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onCancelSubscription()
                        showCancelSubscriptionConfirm = false
                        Toast.makeText(context, "Subscription canceled. Reverted to Free Tier.", Toast.LENGTH_LONG).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B), contentColor = Color.White)
                ) {
                    Text("Yes, Cancel Plan", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showCancelSubscriptionConfirm = false },
                    colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White)
                ) {
                    Text("Keep Subscription")
                }
            }
        )
    }

    if (showFeedbackDialog) {
        AlertDialog(
            onDismissRequest = { showFeedbackDialog = false },
            containerColor = CosmicPurple,
            titleContentColor = Color.White,
            textContentColor = Color.White,
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Feedback, contentDescription = null, tint = CelestialGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Send Feedback", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column {
                    Text(
                        "Select category:",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf("General", "Bug Report", "Feature").forEach { cat ->
                            val isCatSelected = (feedbackCategory == cat)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isCatSelected) CelestialGold else MysticViolet.copy(alpha = 0.5f))
                                    .clickable { feedbackCategory = cat }
                                    .padding(horizontal = 8.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = cat,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCatSelected) DeepSpace else Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = feedbackMessageText,
                        onValueChange = { feedbackMessageText = it },
                        label = { Text("Your Message", color = Color.White.copy(alpha = 0.7f)) },
                        placeholder = { Text("Tell us what you love or how we can improve...", color = Color.White.copy(alpha = 0.4f)) },
                        minLines = 3,
                        maxLines = 5,
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
                        if (feedbackMessageText.isNotBlank()) {
                            Toast.makeText(context, "Thank you! Your feedback has been submitted.", Toast.LENGTH_LONG).show()
                            feedbackMessageText = ""
                            showFeedbackDialog = false
                        } else {
                            Toast.makeText(context, "Please enter a message before submitting.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace)
                ) {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Submit", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showFeedbackDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = Color.White)
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showExportDialog) {
        RawDataExportDialog(
            testResults = testResults,
            astrologyProfile = astrologyProfile,
            onDismiss = { showExportDialog = false }
        )
    }

    if (showReportExportDialog) {
        ReportExportDialog(
            reports = savedReports,
            initialSelectedReport = savedReports.firstOrNull(),
            onDismiss = { showReportExportDialog = false }
        )
    }

    if (showResetConfirm) {
        AlertDialog(
            onDismissRequest = { showResetConfirm = false },
            title = { Text("Clear Local Assessment History?", fontWeight = FontWeight.Bold, color = Color.White) },
            text = { Text("Are you sure you want to clear your saved test results? This operation cannot be undone.", color = Color.White.copy(alpha = 0.8f)) },
            confirmButton = {
                Button(
                    onClick = {
                        onResetData()
                        showResetConfirm = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)
                ) {
                    Text("Clear Data")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirm = false }) {
                    Text("Cancel", color = Color.White)
                }
            },
            containerColor = CosmicPurple
        )
    }
}

@Composable
fun CollapsibleBlock(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    badgeText: String? = null,
    borderColor: Color = MysticViolet.copy(alpha = 0.4f),
    initialExpanded: Boolean = false,
    testTag: String = "collapsible_block",
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(initialExpanded) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple.copy(alpha = 0.85f)),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .testTag(testTag)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
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
                            .background(iconTint.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            if (badgeText != null) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(iconTint)
                                        .padding(horizontal = 5.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = badgeText,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = DeepSpace,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 9.sp
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        if (expanded) androidx.compose.material.icons.Icons.Default.Close else androidx.compose.material.icons.Icons.Default.Settings,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(14.dp))
                content()
            }
        }
    }
}


