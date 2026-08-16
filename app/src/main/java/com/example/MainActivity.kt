package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.viewmodel.PsycheViewModel
import com.example.ui.components.FreeAiChatDialog
import com.example.ui.components.ReviewDialog
import com.example.ui.screens.AssessmentsScreen
import com.example.ui.screens.AstrologyScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PremiumScreen
import com.example.ui.screens.TestTakingScreen
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.CosmicPurple
import com.example.ui.theme.DeepSpace
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.MysticViolet
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import com.example.ui.components.FirstInstallNameDialog
import com.example.ui.components.NameMeaningReportDialog
import com.example.ui.components.SettingsDialog

class MainActivity : ComponentActivity() {

    private val viewModel: PsycheViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(viewModel: PsycheViewModel) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var showSettingsDialog by remember { mutableStateOf(false) }
    var showFreeMindChatDialog by remember { mutableStateOf(false) }
    var hasDismissedFirstInstallOnboarding by remember { mutableStateOf(false) }

    val testResults by viewModel.testResults.collectAsStateWithLifecycle()
    val astroProfile by viewModel.astrologyProfile.collectAsStateWithLifecycle()
    val savedReports by viewModel.savedReports.collectAsStateWithLifecycle()
    val userSub by viewModel.userSubscription.collectAsStateWithLifecycle()
    val testState by viewModel.testState.collectAsStateWithLifecycle()
    val isGenerating by viewModel.isGeneratingReport.collectAsStateWithLifecycle()
    val selectedReport by viewModel.selectedReport.collectAsStateWithLifecycle()
    val showReviewModal by viewModel.showReviewModal.collectAsStateWithLifecycle()

    val natalCharts by viewModel.natalCharts.collectAsStateWithLifecycle()
    val oracleMessages by viewModel.oracleMessages.collectAsStateWithLifecycle()
    val isOracleThinking by viewModel.isOracleThinking.collectAsStateWithLifecycle()
    val isGeneratingMatch by viewModel.isGeneratingMatchReport.collectAsStateWithLifecycle()
    val inDepthMatchReport by viewModel.inDepthMatchReport.collectAsStateWithLifecycle()

    val mindChatMessages by viewModel.mindChatMessages.collectAsStateWithLifecycle()
    val isMindChatThinking by viewModel.isMindChatThinking.collectAsStateWithLifecycle()

    val nameMeaningReport by viewModel.nameMeaningReport.collectAsStateWithLifecycle()
    val isGeneratingNameReport by viewModel.isGeneratingNameReport.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (testState.activeTest == null) {
                TopAppBar(
                    title = {
                        Text(
                            text = when (selectedTab) {
                                0 -> "InsideMe AI"
                                1 -> "Assessments Vault"
                                2 -> "Astrology & Oracle"
                                3 -> "Premium & Gems"
                                else -> "InsideMe"
                            },
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = { showFreeMindChatDialog = true },
                            modifier = Modifier.testTag("open_free_ai_chat_top_bar")
                        ) {
                            Icon(Icons.Default.Psychology, contentDescription = "Free AI Chat", tint = NebulaTeal)
                        }
                        IconButton(
                            onClick = { showSettingsDialog = true },
                            modifier = Modifier.testTag("open_settings_button")
                        ) {
                            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = CelestialGold)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = CosmicPurple)
                )
            }
        },
        bottomBar = {
            if (testState.activeTest == null) {
                NavigationBar(
                    containerColor = CosmicPurple,
                    contentColor = Color.White,
                    modifier = Modifier.testTag("main_bottom_nav_bar")
                ) {
                    NavigationBarItem(
                        selected = (selectedTab == 0),
                        onClick = { selectedTab = 0 },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = {
                            Text(
                                text = "InsideMe",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                softWrap = false,
                                fontSize = 10.sp,
                                letterSpacing = (-0.2).sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CelestialGold,
                            selectedTextColor = CelestialGold,
                            indicatorColor = MysticViolet
                        ),
                        modifier = Modifier.testTag("nav_tab_insideme")
                    )

                    NavigationBarItem(
                        selected = (selectedTab == 1),
                        onClick = { selectedTab = 1 },
                        icon = { Icon(Icons.Default.Psychology, contentDescription = "Assessments") },
                        label = {
                            Text(
                                text = "Assessments",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                softWrap = false,
                                fontSize = 9.5.sp,
                                letterSpacing = (-0.4).sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CelestialGold,
                            selectedTextColor = CelestialGold,
                            indicatorColor = MysticViolet
                        ),
                        modifier = Modifier.testTag("nav_tab_assessments")
                    )

                    NavigationBarItem(
                        selected = (selectedTab == 2),
                        onClick = { selectedTab = 2 },
                        icon = { Icon(Icons.Default.Star, contentDescription = "Astrology") },
                        label = {
                            Text(
                                text = "Astrology",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                softWrap = false,
                                fontSize = 10.sp,
                                letterSpacing = (-0.2).sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CelestialGold,
                            selectedTextColor = CelestialGold,
                            indicatorColor = MysticViolet
                        ),
                        modifier = Modifier.testTag("nav_tab_astrology")
                    )

                    NavigationBarItem(
                        selected = (selectedTab == 3),
                        onClick = { selectedTab = 3 },
                        icon = { Icon(Icons.Default.WorkspacePremium, contentDescription = "Premium") },
                        label = {
                            Text(
                                text = "Premium",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                softWrap = false,
                                fontSize = 10.sp,
                                letterSpacing = (-0.2).sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CelestialGold,
                            selectedTextColor = CelestialGold,
                            indicatorColor = MysticViolet
                        ),
                        modifier = Modifier.testTag("nav_tab_premium")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (testState.activeTest != null) {
                TestTakingScreen(
                    testState = testState,
                    userSubscription = userSub,
                    onAnswerSelected = { viewModel.answerQuestion(it) },
                    onExitClicked = { viewModel.exitTest() },
                    onGenerateReportClicked = {
                        viewModel.exitTest()
                        selectedTab = 2
                        viewModel.generateSynthesisReport(
                            onSuccess = {},
                            onNeedAdOrGems = { selectedTab = 3 }
                        )
                    },
                    onPurchaseSingleReport = {
                        viewModel.exitTest()
                        selectedTab = 2
                        viewModel.purchaseSingleReportAndGenerate(onSuccess = {})
                    },
                    onSubscribeClicked = { tier ->
                        viewModel.exitTest()
                        selectedTab = 2
                        viewModel.subscribeAndGenerate(tier = tier, onSuccess = {})
                    },
                    onOpenReviewClicked = {
                        viewModel.openReviewModal()
                    }
                )
            } else {
                when (selectedTab) {
                    0 -> HomeScreen(
                        testResults = testResults,
                        astrologyProfile = astroProfile,
                        userSubscription = userSub,
                        onNavigateToAssessments = { selectedTab = 1 },
                        onNavigateToAstrology = { selectedTab = 2 },
                        onGenerateReportClicked = {
                            selectedTab = 2
                            viewModel.generateSynthesisReport(
                                onSuccess = {},
                                onNeedAdOrGems = { selectedTab = 3 }
                            )
                        },
                        onUpgradeClicked = { selectedTab = 3 },
                        onOpenFreeMindChat = { showFreeMindChatDialog = true },
                        onGenerateNameReport = { viewModel.generateNameMeaningReport(it) }
                    )

                    1 -> AssessmentsScreen(
                        testResults = testResults,
                        savedReports = savedReports,
                        selectedReport = selectedReport,
                        astrologyProfile = astroProfile,
                        isGenerating = isGenerating,
                        isPremium = userSub.isPremium,
                        gemsBalance = userSub.gemsBalance,
                        onStartTest = { viewModel.startTest(it) },
                        onGenerateMetaReportClicked = {
                            viewModel.generateMasterMetaReport(
                                onSuccess = {},
                                onNeedAdOrGems = { selectedTab = 3 }
                            )
                        },
                        onSelectReport = { viewModel.selectReport(it) },
                        onToggleBookmark = { id, current -> viewModel.toggleBookmark(id, current) },
                        onToggleHabit = { report, idx -> viewModel.toggleHabitCompletion(report, idx) },
                        onOpenFreeMindChat = { showFreeMindChatDialog = true }
                    )

                    2 -> AstrologyScreen(
                        profile = astroProfile,
                        natalCharts = natalCharts,
                        oracleMessages = oracleMessages,
                        isOracleThinking = isOracleThinking,
                        isGeneratingMatchReport = isGeneratingMatch,
                        inDepthMatchReport = inDepthMatchReport,
                        savedReports = savedReports,
                        selectedReport = selectedReport,
                        testResults = testResults,
                        isGeneratingReport = isGenerating,
                        gemsBalance = userSub.gemsBalance,
                        isPremium = userSub.isPremium,
                        onUpdateSigns = { sun, moon, rising ->
                            viewModel.updateAstrologySignsDirectly(sun, moon, rising)
                        },
                        onUpdateProfile = { dobMillis, timeStr, cityStr ->
                            viewModel.updateAstrologyProfile(dobMillis, timeStr, cityStr)
                        },
                        onSendOracleMessage = { question -> viewModel.sendOracleMessage(question) },
                        onGenerateBirthdateMatch = { p1Name, p1Dob, p1Time, p1City, p2Name, p2Dob, p2Time, p2City ->
                            viewModel.generateMatchReportFromBirthdates(p1Name, p1Dob, p1Time, p1City, p2Name, p2Dob, p2Time, p2City)
                        },
                        onSaveNatalChart = { name, dob, time, city, notes ->
                            viewModel.saveCustomNatalChart(name, dob, time, city, notes)
                        },
                        onDeleteNatalChart = { chartId -> viewModel.deleteNatalChart(chartId) },
                        onClearMatchReport = { viewModel.clearMatchReport() },
                        onGenerateReportClicked = {
                            viewModel.generateSynthesisReport(
                                onSuccess = {},
                                onNeedAdOrGems = { selectedTab = 3 }
                            )
                        },
                        onSelectReport = { viewModel.selectReport(it) },
                        onToggleBookmark = { id, current -> viewModel.toggleBookmark(id, current) },
                        onToggleHabit = { report, idx -> viewModel.toggleHabitCompletion(report, idx) },
                        onNavigateToReports = { selectedTab = 2 },
                        onUpgradeClicked = { selectedTab = 3 }
                    )

                    3 -> PremiumScreen(
                        userSubscription = userSub,
                        onSetSubscriptionTier = { tier -> viewModel.setSubscriptionTier(tier) },
                        onPurchaseSingleReport = { viewModel.purchaseSingleReportOnly() },
                        onOpenReview = { viewModel.openReviewModal() }
                    )
                }
            }

            // Review Modal overlay
            if (showReviewModal) {
                ReviewDialog(
                    hasClaimedBonus = userSub.hasClaimedReviewBonus,
                    onDismiss = { viewModel.dismissReviewModal() },
                    onSubmitReview = { rating, comment -> viewModel.submitAppReview(rating, comment) }
                )
            }

            // Free AI Mind & Wellbeing Companion Chat overlay
            if (showFreeMindChatDialog) {
                FreeAiChatDialog(
                    messages = mindChatMessages,
                    isThinking = isMindChatThinking,
                    testResults = testResults,
                    onSendMessage = { viewModel.sendMindChatMessage(it) },
                    onClearChat = { viewModel.clearMindChat() },
                    onDismiss = { showFreeMindChatDialog = false }
                )
            }

            // First Install Name Onboarding Modal overlay
            val isFirstInstallPrompt = astroProfile != null && (astroProfile?.userName.isNullOrBlank()) && !hasDismissedFirstInstallOnboarding
            if (isFirstInstallPrompt) {
                FirstInstallNameDialog(
                    initialName = astroProfile?.userName ?: "",
                    onSaveName = { name ->
                        viewModel.updateUserName(name)
                        hasDismissedFirstInstallOnboarding = true
                    },
                    onSkip = {
                        hasDismissedFirstInstallOnboarding = true
                    }
                )
            }

            // Settings Modal overlay
            if (showSettingsDialog) {
                SettingsDialog(
                    userSubscription = userSub,
                    testResults = testResults,
                    astrologyProfile = astroProfile,
                    savedReports = savedReports,
                    onDismiss = { showSettingsDialog = false },
                    onNavigateToUpgrade = { selectedTab = 3 },
                    onOpenReviewModal = { viewModel.openReviewModal() },
                    onResetData = { viewModel.clearAllTestResults() },
                    onUpdateUserName = { viewModel.updateUserName(it) },
                    onGenerateNameReport = { viewModel.generateNameMeaningReport(it) },
                    onRemoveSavedName = { viewModel.removeSavedNameAddition(it) },
                    onAddSavedName = { viewModel.addSavedNameAddition(it) },
                    onRedeemPromoCode = { viewModel.redeemPromoCode(it) }
                )
            }

            // Name Meaning & Intent Report overlay
            if (nameMeaningReport != null || isGeneratingNameReport) {
                NameMeaningReportDialog(
                    report = nameMeaningReport,
                    isGenerating = isGeneratingNameReport,
                    currentMainName = astroProfile?.userName ?: "",
                    savedNames = astroProfile?.savedNameAdditions ?: emptyList(),
                    onAnalyzeName = { viewModel.generateNameMeaningReport(it) },
                    onSetMainName = { viewModel.updateUserName(it) },
                    onSaveNameAddition = { viewModel.addSavedNameAddition(it) },
                    onDismiss = { viewModel.dismissNameMeaningReport() }
                )
            }
        }
    }
}
