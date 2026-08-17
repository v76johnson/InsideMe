package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AstrologyProfile
import com.example.data.model.DailyHoroscope
import com.example.data.model.ZodiacSign
import com.example.data.repository.AstrologyEngine
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DailyHoroscopeWidget(
    astrologyProfile: AstrologyProfile?,
    onNavigateToAstrology: () -> Unit,
    modifier: Modifier = Modifier
) {
    val defaultSign = astrologyProfile?.sunSign ?: ZodiacSign.SCORPIO
    var selectedSign by remember(astrologyProfile?.sunSign) { mutableStateOf(defaultSign) }
    var isExpanded by remember { mutableStateOf(false) }
    var signMenuExpanded by remember { mutableStateOf(false) }

    val horoscope: DailyHoroscope = remember(selectedSign) {
        AstrologyEngine.generateDailyHoroscope(selectedSign)
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, CelestialGold.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
            .testTag("home_daily_horoscope_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header Row: Label & Sign Switcher
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(CelestialGold.copy(alpha = 0.2f))
                            .border(1.dp, CelestialGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedSign.symbol,
                            fontSize = 18.sp,
                            color = CelestialGold,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "DAILY HOROSCOPE 🔮",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = CelestialGold
                        )
                        Text(
                            text = horoscope.dateString,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 11.sp
                        )
                    }
                }

                // Sign Selector Dropdown
                Box {
                    Surface(
                        color = MysticViolet.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CelestialGold.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .clickable { signMenuExpanded = true }
                            .testTag("daily_horoscope_sign_selector")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${selectedSign.displayName} ${selectedSign.symbol}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                Icons.Default.ExpandMore,
                                contentDescription = "Change Sign",
                                tint = CelestialGold,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = signMenuExpanded,
                        onDismissRequest = { signMenuExpanded = false },
                        modifier = Modifier.background(CosmicPurple)
                    ) {
                        ZodiacSign.values().forEach { sign ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(sign.symbol, color = CelestialGold, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = sign.displayName,
                                            color = if (sign == selectedSign) CelestialGold else Color.White,
                                            fontWeight = if (sign == selectedSign) FontWeight.Bold else FontWeight.Normal
                                        )
                                        if (sign == astrologyProfile?.sunSign) {
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("(You)", color = NebulaTeal, fontSize = 10.sp)
                                        }
                                    }
                                },
                                onClick = {
                                    selectedSign = sign
                                    signMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Headline
            Text(
                text = horoscope.headline,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Overview Text
            Text(
                text = horoscope.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Quick Stats Badges
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Focus Theme
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MysticViolet.copy(alpha = 0.45f))
                        .border(1.dp, MysticViolet, RoundedCornerShape(8.dp))
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "✨ ${horoscope.focusTheme}",
                        style = MaterialTheme.typography.labelSmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    )
                }

                // Energy Rating
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(NebulaTeal.copy(alpha = 0.2f))
                        .border(1.dp, NebulaTeal.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "⚡ Cosmic Energy: ${horoscope.energyRating}%",
                        style = MaterialTheme.typography.labelSmall,
                        color = NebulaTeal,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    )
                }

                // Compatible Sign Today
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CelestialGold.copy(alpha = 0.15f))
                        .border(1.dp, CelestialGold.copy(alpha = 0.35f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "💫 Synergy: ${horoscope.compatibleSignToday.displayName} ${horoscope.compatibleSignToday.symbol}",
                        style = MaterialTheme.typography.labelSmall,
                        color = CelestialGold,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    )
                }
            }

            // Expandable Deeper Breakdown (Love, Career, Cosmic Advice, Lucky Number/Color)
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 14.dp)) {
                    HorizontalDivider(color = MysticViolet.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(12.dp))

                    // Love Vibe
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEF476F).copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Color(0xFFEF476F),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Love & Resonance",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFEF476F)
                            )
                            Text(
                                text = horoscope.loveVibe,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f),
                                lineHeight = 17.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Career Vibe
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(NebulaTeal.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Work,
                                contentDescription = null,
                                tint = NebulaTeal,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Career & Ambition",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = NebulaTeal
                            )
                            Text(
                                text = horoscope.careerVibe,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f),
                                lineHeight = 17.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Cosmic Advice
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(CelestialGold.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = CelestialGold,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Cosmic Guidance",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = CelestialGold
                            )
                            Text(
                                text = horoscope.cosmicAdvice,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f),
                                lineHeight = 17.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Lucky details row
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MysticViolet.copy(alpha = 0.35f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("LUCKY NUMBER", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = CelestialGold)
                                Text("${horoscope.luckyNumber}", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("LUCKY COLOR", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = NebulaTeal)
                                Text(horoscope.luckyColor, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("ELEMENT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = CelestialGold)
                                Text(selectedSign.element.displayName, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Row: Toggle Details & Navigate to Astrology
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.clickable { isExpanded = !isExpanded }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = if (isExpanded) "Hide Details" else "Love & Career Insights",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = CelestialGold
                        )
                        Icon(
                            if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = CelestialGold,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Button(
                    onClick = onNavigateToAstrology,
                    colors = ButtonDefaults.buttonColors(containerColor = MysticViolet, contentColor = CelestialGold),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("daily_horoscope_astrology_hub_btn")
                ) {
                    Text("Full Charts →", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
