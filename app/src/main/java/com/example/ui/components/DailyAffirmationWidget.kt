package com.example.ui.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyProfile
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.ShadowRose
import com.example.ui.theme.Spacing

import androidx.compose.material.icons.filled.ContentCopy as CopyIcon
import androidx.compose.material.icons.filled.Favorite as FavoriteIcon
import androidx.compose.material.icons.filled.FavoriteBorder as FavoriteBorderIcon
import androidx.compose.material.icons.filled.Refresh as RefreshIcon
import androidx.compose.material.icons.filled.Lightbulb as LightbulbIcon
import androidx.compose.material.icons.filled.Psychology as PsychologyIcon
import androidx.compose.material.icons.filled.SelfImprovement as SelfImprovementIcon
import androidx.compose.runtime.getValue as rv
import androidx.compose.runtime.mutableStateOf as mso
import androidx.compose.runtime.remember as r
import androidx.compose.runtime.setValue as sv
import androidx.compose.ui.res.stringResource
import com.example.ui.components.DailyAffirmationWidget

@Composable
fun DailyAffirmationWidget(
    testResults: List<TestResultEntity>,
    astrologyProfile: AstrologyProfile?,
    onTakeAssessmentClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    val affirmations = remember(testResults, astrologyProfile) {
        AffirmationEngine.getPersonalizedAffirmations(testResults, astrologyProfile)
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentItem = affirmations.getOrElse(currentIndex % affirmations.size) { affirmations.first() }

    var isBookmarked by remember { mutableStateOf(false) }
    var showReflectionPrompt by remember { mutableStateOf(true) }

    val personalizedCount = remember(affirmations) { affirmations.count { it.isPersonalized } }

    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = CosmicPurple),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(CelestialGold, NebulaTeal, MysticViolet)
                ),
                shape = RoundedCornerShape(22.dp)
            )
            .testTag("daily_affirmation_widget")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.pagePadding)
        ) {
            // Header Row: Widget Title & Personalization Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(CelestialGold.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription = "Affirmation Icon",
                            tint = CelestialGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

{