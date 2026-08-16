package com.example.ui.components

import android.app.DatePickerDialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.repository.AstrologyEngine
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun FirstInstallNameDialog(
    initialName: String = "",
    initialBirthDateMillis: Long = 0L,
    initialBirthTime: String = "12:00 PM",
    initialBirthCity: String = "",
    onSaveProfile: (name: String, birthDateMillis: Long, birthTime: String, birthCity: String) -> Unit = { _, _, _, _ -> },
    onSaveName: (String) -> Unit = {},
    onSkip: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var nameFieldValue by remember {
        mutableStateOf(TextFieldValue(text = initialName, selection = TextRange(initialName.length)))
    }
    var selectedDateMillis by remember {
        mutableLongStateOf(if (initialBirthDateMillis > 0L) initialBirthDateMillis else 0L)
    }
    var birthTimeStr by remember { mutableStateOf(initialBirthTime) }
    var birthCityStr by remember { mutableStateOf(initialBirthCity) }
    var showAdvancedOptions by remember { mutableStateOf(false) }

    val dateFormat = remember { SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()) }

    // DatePickerDialog launcher
    val calendar = Calendar.getInstance().apply {
        if (selectedDateMillis > 0L) {
            timeInMillis = selectedDateMillis
        } else {
            // Default picker position to ~22 years ago
            add(Calendar.YEAR, -22)
        }
    }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val cal = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    set(Calendar.HOUR_OF_DAY, 12)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                selectedDateMillis = cal.timeInMillis
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    // Calculated Signs preview when date is chosen
    val previewSigns = remember(selectedDateMillis, birthTimeStr, birthCityStr) {
        if (selectedDateMillis > 0L) {
            AstrologyEngine.calculateProfileFromDate(selectedDateMillis, birthTimeStr, birthCityStr)
        } else null
    }

    Dialog(
        onDismissRequest = onSkip,
        properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnClickOutside = false)
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(0.94f)
                .clip(RoundedCornerShape(24.dp))
                .border(1.5.dp, CelestialGold, RoundedCornerShape(24.dp))
                .testTag("first_install_onboarding_dialog"),
            color = DeepSpace
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Icon
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape)
                        .background(CosmicPurple)
                        .border(1.2.dp, CelestialGold.copy(alpha = 0.8f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = CelestialGold,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Title
                Text(
                    text = "Welcome to InsideMe",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "PSYCHOLOGICAL & ASTROLOGICAL SYNTHESIS",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = CelestialGold,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Enter your first name and birthdate to reveal your Big 3 signs (Sun, Moon, Rising), personalized psychological reports, and daily cosmic horoscopes.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Name Input Field
                OutlinedTextField(
                    value = nameFieldValue,
                    onValueChange = { nameFieldValue = it },
                    placeholder = { Text("e.g. Alex, Maya, Jordan", color = Color.White.copy(alpha = 0.45f)) },
                    label = { Text("First Name", color = CelestialGold) },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = CelestialGold)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("first_install_name_input"),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CelestialGold,
                        unfocusedBorderColor = CelestialGold.copy(alpha = 0.5f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = CosmicPurple.copy(alpha = 0.5f),
                        unfocusedContainerColor = CosmicPurple.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Birthdate Picker Field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Date of Birth",
                        style = MaterialTheme.typography.labelSmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = CosmicPurple.copy(alpha = 0.4f),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (selectedDateMillis > 0L) CelestialGold else MysticViolet.copy(alpha = 0.7f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { datePickerDialog.show() }
                            .testTag("first_install_birthdate_picker")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CalendarMonth,
                                    contentDescription = null,
                                    tint = CelestialGold,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    if (selectedDateMillis > 0L) {
                                        Text(
                                            text = dateFormat.format(Date(selectedDateMillis)),
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    } else {
                                        Text(
                                            text = "Tap to select birthdate...",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.White.copy(alpha = 0.55f)
                                        )
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(CelestialGold.copy(alpha = 0.2f))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (selectedDateMillis > 0L) "Change" else "Select",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = CelestialGold
                                )
                            }
                        }
                    }
                }

                // Live Signs Preview
                if (previewSigns != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MysticViolet.copy(alpha = 0.4f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, NebulaTeal.copy(alpha = 0.7f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "☀️ ${previewSigns.sunSign.displayName} (${previewSigns.sunSign.symbol})",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                            Text(
                                text = "🌙 ${previewSigns.moonSign.displayName} (${previewSigns.moonSign.symbol})",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "🌅 ${previewSigns.risingSign.displayName} (${previewSigns.risingSign.symbol})",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = NebulaTeal
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Advanced Options Toggle (Birth Time & City)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { showAdvancedOptions = !showAdvancedOptions }
                        .padding(vertical = 6.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Precise Placements (Time & City - Optional)",
                        style = MaterialTheme.typography.labelSmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = if (showAdvancedOptions) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = CelestialGold,
                        modifier = Modifier.size(18.dp)
                    )
                }

                AnimatedVisibility(visible = showAdvancedOptions) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = birthTimeStr,
                            onValueChange = { birthTimeStr = it },
                            label = { Text("Birth Time (e.g. 03:30 PM)", color = Color.White.copy(alpha = 0.7f)) },
                            leadingIcon = { Icon(Icons.Default.Schedule, contentDescription = null, tint = CelestialGold) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = CelestialGold,
                                unfocusedBorderColor = MysticViolet,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = birthCityStr,
                            onValueChange = { birthCityStr = it },
                            label = { Text("Birth City & Country", color = Color.White.copy(alpha = 0.7f)) },
                            leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = CelestialGold) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = CelestialGold,
                                unfocusedBorderColor = MysticViolet,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Privacy Assurance Badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = CosmicPurple.copy(alpha = 0.5f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, NebulaTeal.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(NebulaTeal.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Privacy Lock",
                                tint = NebulaTeal,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "100% Stored Locally: All personal details, assessment answers, and purchases remain strictly private on your device.",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 11.sp,
                            lineHeight = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action Buttons
                Button(
                    onClick = {
                        val finalName = nameFieldValue.text.trim()
                        val finalDate = if (selectedDateMillis > 0L) selectedDateMillis else System.currentTimeMillis()
                        onSaveProfile(
                            if (finalName.isNotBlank()) finalName else "Seeker",
                            finalDate,
                            birthTimeStr.ifBlank { "12:00 PM" },
                            birthCityStr
                        )
                        onSaveName(if (finalName.isNotBlank()) finalName else "Seeker")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("first_install_save_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CelestialGold,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Begin My Journey ✨",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = {
                        onSkip()
                    },
                    modifier = Modifier.testTag("first_install_skip_button")
                ) {
                    Text(
                        text = "Skip for Now (Continue as Guest)",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
