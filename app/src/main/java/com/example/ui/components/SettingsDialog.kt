package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RateReview
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    var showCareLocator by remember { mutableStateOf(false) }
    var showResetConfirm by remember { mutableStateOf(false) }

    var promoCodeFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var promoCodeMessage by remember { mutableStateOf<String?>(null) }
    var promoCodeSuccess by remember { mutableStateOf(false) }

    var dailyNotificationsEnabled by remember { mutableStateOf(true) }
    var cosmicThemeEnabled by remember { mutableStateOf(true) }

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
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "System, Data & Care Controls",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.6f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
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
                        icon = androidx.compose.material.icons.Icons.Default.Star,
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
                                androidx.compose.material3.OutlinedTextField(
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
                                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
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
                                border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold),
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
                                            border = androidx.compose.foundation.BorderStroke(1.dp, MysticViolet),
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
                                                    // View report button
                                                    TextButton(
                                                        onClick = { onGenerateNameReport(nameItem) },
                                                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                                                    ) {
                                                        Text("Report", color = CelestialGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                                    }

                                                    // Set main name button
                                                    if (!nameItem.equals(userNameFieldValue.text, ignoreCase = true)) {
                                                        TextButton(
                                                            onClick = {
                                                                onUpdateUserName(nameItem)
                                                                userNameFieldValue = TextFieldValue(text = nameItem, selection = TextRange(nameItem.length))
                                                            },
                                                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                                                        ) {
                                                            Text("Set Main", color = NebulaTeal, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                                        }
                                                    }

                                                    // Remove button
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

                    // SECTION 1: ACCOUNT & TIER STATUS
                    CollapsibleBlock(
                        title = "Subscription & Membership",
                        subtitle = if (userSubscription.isPremium) "Tier: ${userSubscription.tier.title}" else "Free Tier (Gems: ${userSubscription.gemsBalance})",
                        icon = Icons.Default.WorkspacePremium,
                        iconTint = CelestialGold,
                        initialExpanded = true
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Current Status: ${if (userSubscription.isPremium) userSubscription.tier.title else "Free User"}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Available Gems: ${userSubscription.gemsBalance}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = CelestialGold
                                    )
                                }

                                Button(
                                    onClick = {
                                        onDismiss()
                                        onNavigateToUpgrade()
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = DeepSpace),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.testTag("settings_upgrade_button")
                                ) {
                                    Text(if (userSubscription.isPremium) "Manage Tier" else "Upgrade Tier", fontWeight = FontWeight.Bold, fontSize = 12.sp)
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
                                androidx.compose.material3.OutlinedTextField(
                                    value = promoCodeFieldValue,
                                    onValueChange = {
                                        promoCodeFieldValue = it
                                        promoCodeMessage = null
                                    },
                                    placeholder = { Text("Enter code (e.g. betatest)", color = Color.White.copy(alpha = 0.45f)) },
                                    singleLine = true,
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("settings_promo_code_input"),
                                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
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
                                    modifier = Modifier.testTag("settings_promo_code_submit_button")
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
                                        checkedThumbColor = DeepSpace,
                                        checkedTrackColor = CelestialGold
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SECTION 3: CRISIS RESOURCES & 24/7 HELPLINES
                    val context = LocalContext.current
                    CollapsibleBlock(
                        title = "Crisis Resources & 24/7 Helplines",
                        subtitle = "Immediate Suicide Prevention, Hotlines & Localized Support",
                        badgeText = "24/7 Help",
                        icon = Icons.Default.Warning,
                        iconTint = Color(0xFFFF5252),
                        borderColor = Color(0xFFFF5252).copy(alpha = 0.6f),
                        initialExpanded = true,
                        testTag = "settings_crisis_resources_block"
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFFF5252).copy(alpha = 0.15f))
                                    .border(1.dp, Color(0xFFFF5252).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                    .padding(12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.Top) {
                                    Icon(
                                        imageVector = Icons.Default.Warning,
                                        contentDescription = null,
                                        tint = Color(0xFFFF5252),
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "If you or someone you know is in distress or experiencing a mental health emergency, please reach out immediately. Confidential support is available 24/7.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White.copy(alpha = 0.95f),
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp
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
                                        try {
                                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:988"))
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF5252),
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .testTag("call_988_button")
                                ) {
                                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Call 988", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }

                                Button(
                                    onClick = {
                                        try {
                                            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:741741")).apply {
                                                putExtra("sms_body", "HOME")
                                            }
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = NebulaTeal,
                                        contentColor = DeepSpace
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .testTag("text_741741_button")
                                ) {
                                    Text("Text 741741", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            HelplineInfoItem(
                                title = "988 Suicide & Crisis Lifeline (US & Canada)",
                                detail = "Call or Text 988 • Free, confidential, 24/7 support for mental health crises.",
                                badge = "Call/Text 988",
                                badgeColor = Color(0xFFFF5252)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            HelplineInfoItem(
                                title = "Crisis Text Line",
                                detail = "Text HOME to 741741 (US/Canada) or 85258 (UK) • 24/7 text counselor support.",
                                badge = "Text 741741",
                                badgeColor = NebulaTeal
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            HelplineInfoItem(
                                title = "The Trevor Project (LGBTQ+ Youth)",
                                detail = "Call 1-866-488-7386 or Text START to 678-678 • Specialized LGBTQ+ crisis care.",
                                badge = "1-866-488-7386",
                                badgeColor = CelestialGold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            HelplineInfoItem(
                                title = "Veterans Crisis Line",
                                detail = "Dial 988 (Press 1) or Text 838255 • Dedicated support for Veterans & families.",
                                badge = "988 Press 1",
                                badgeColor = MysticViolet
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            HelplineInfoItem(
                                title = "SAMHSA National Helpline",
                                detail = "Call 1-800-662-4357 • Free 24/7 treatment referral service for mental health.",
                                badge = "1-800-662-4357",
                                badgeColor = Color.White.copy(alpha = 0.8f)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            HelplineInfoItem(
                                title = "Global Crisis Directories",
                                detail = "International helplines: Visit befrienders.org or findahelpline.com for over 100 countries.",
                                badge = "International",
                                badgeColor = CelestialGold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SECTION 4: CARE & DATA TOOLS
                    CollapsibleBlock(
                        title = "Data Export & Care Resources",
                        subtitle = "Raw Json Export & Mental Health Professional Search",
                        icon = Icons.Default.MedicalServices,
                        iconTint = CelestialGold,
                        initialExpanded = true
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { showCareLocator = true },
                                    colors = ButtonDefaults.buttonColors(containerColor = NebulaTeal, contentColor = DeepSpace),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .testTag("settings_care_locator_button")
                                ) {
                                    Icon(Icons.Default.MedicalServices, contentDescription = null, modifier = Modifier.size(15.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Care AI", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }

                                OutlinedButton(
                                    onClick = { showExportDialog = true },
                                    shape = RoundedCornerShape(12.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold),
                                    modifier = Modifier
                                        .weight(1.1f)
                                        .height(44.dp)
                                        .testTag("settings_export_data_button")
                                ) {
                                    Icon(Icons.Default.Download, contentDescription = null, tint = CelestialGold, modifier = Modifier.size(15.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Raw Data (Free)", color = CelestialGold, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = { showReportExportDialog = true },
                                colors = ButtonDefaults.buttonColors(containerColor = CelestialGold, contentColor = Color.Black),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .testTag("settings_export_reports_cloud_button")
                            ) {
                                Icon(Icons.Default.CloudUpload, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Export Full Premium Reports (Drive / PDF / Cloud)", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedButton(
                                onClick = { showResetConfirm = true },
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red.copy(alpha = 0.6f)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp)
                                    .testTag("settings_reset_data_button")
                            ) {
                                Icon(Icons.Default.DeleteForever, contentDescription = null, tint = Color.Red, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Clear Local Assessment History", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SECTION 4: APP FEEDBACK & DISCLAIMER
                    CollapsibleBlock(
                        title = "App Info & Legal Disclaimer",
                        subtitle = "Version 2.4.0 & Mandatory Health Disclaimer",
                        icon = Icons.Default.Shield,
                        iconTint = MysticViolet,
                        initialExpanded = false
                    ) {
                        Column {
                            OutlinedButton(
                                onClick = {
                                    onDismiss()
                                    onOpenReviewModal()
                                },
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, MysticViolet),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
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

    if (showCareLocator) {
        ProfessionalLocatorDialog(
            onDismiss = { showCareLocator = false }
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
private fun HelplineInfoItem(
    title: String,
    detail: String,
    badge: String,
    badgeColor: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DeepSpace.copy(alpha = 0.8f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(badgeColor.copy(alpha = 0.2f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = badge,
                        style = MaterialTheme.typography.labelSmall,
                        color = badgeColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = detail,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

