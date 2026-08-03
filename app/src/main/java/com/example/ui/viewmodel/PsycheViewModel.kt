package com.example.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.TestResultEntity
import com.example.data.model.AstrologyChatMessage
import com.example.data.model.AstrologyProfile
import com.example.data.model.Choice
import com.example.data.model.CustomNatalChart
import com.example.data.model.DeepSynthesisReport
import com.example.data.model.InDepthMatchReport
import com.example.data.model.MindChatMessage
import com.example.data.model.PsychologyTest
import com.example.data.model.SubscriptionTier
import com.example.data.model.UserSubscription
import com.example.data.model.ZodiacSign
import com.example.data.repository.AstrologyEngine
import com.example.data.repository.PsycheRepository
import com.example.data.repository.TestCatalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class TestState(
    val activeTest: PsychologyTest? = null,
    val currentQuestionIndex: Int = 0,
    val selectedChoices: MutableList<Choice> = mutableListOf(),
    val isCompleted: Boolean = false
)

class PsycheViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PsycheRepository(AppDatabase.getDatabase(application))

    val testResults: StateFlow<List<TestResultEntity>> = repository.allTestResults
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val astrologyProfile: StateFlow<AstrologyProfile?> = repository.astrologyProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val natalCharts: StateFlow<List<CustomNatalChart>> = repository.natalCharts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedReports: StateFlow<List<DeepSynthesisReport>> = repository.savedReports
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userSubscription: StateFlow<UserSubscription> = repository.userSubscription
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserSubscription())

    private val _oracleMessages = MutableStateFlow<List<AstrologyChatMessage>>(
        listOf(
            AstrologyChatMessage(
                sender = "oracle",
                text = "Greetings, Seeker. I am your Cosmic AI Oracle. Ask me anything about your natal chart, planetary transits, love compatibility, or life purpose."
            )
        )
    )
    val oracleMessages: StateFlow<List<AstrologyChatMessage>> = _oracleMessages.asStateFlow()

    private val _isOracleThinking = MutableStateFlow(false)
    val isOracleThinking: StateFlow<Boolean> = _isOracleThinking.asStateFlow()

    private val _mindChatMessages = MutableStateFlow<List<MindChatMessage>>(
        listOf(
            MindChatMessage(
                sender = "companion",
                text = "Welcome to your **Free AI Mind & Wellbeing Companion**! 💚\n\nI am here to listen, support your emotional wellbeing, answer questions about your assessment scores, and discuss ways to improve your mental health.\n\n*Feel free to ask how to interpret your scores, share how you are feeling, or discuss when to seek professional care.*"
            )
        )
    )
    val mindChatMessages: StateFlow<List<MindChatMessage>> = _mindChatMessages.asStateFlow()

    private val _isMindChatThinking = MutableStateFlow(false)
    val isMindChatThinking: StateFlow<Boolean> = _isMindChatThinking.asStateFlow()

    fun sendMindChatMessage(text: String) {
        if (text.isBlank()) return
        val userMsg = MindChatMessage(sender = "user", text = text)
        val currentHistory = _mindChatMessages.value.toMutableList().apply { add(userMsg) }
        _mindChatMessages.value = currentHistory
        _isMindChatThinking.value = true

        viewModelScope.launch {
            val answer = repository.askFreeMindCompanion(
                userMessage = text,
                history = currentHistory,
                testResults = testResults.value,
                astroProfile = astrologyProfile.value
            )
            val aiMsg = MindChatMessage(sender = "companion", text = answer)
            _mindChatMessages.value = _mindChatMessages.value.toMutableList().apply { add(aiMsg) }
            _isMindChatThinking.value = false
        }
    }

    fun clearMindChat() {
        _mindChatMessages.value = listOf(
            MindChatMessage(
                sender = "companion",
                text = "Chat reset! How can I support your feelings or help with assessment results today? 💚"
            )
        )
    }

    private val _isGeneratingMatchReport = MutableStateFlow(false)
    val isGeneratingMatchReport: StateFlow<Boolean> = _isGeneratingMatchReport.asStateFlow()

    private val _inDepthMatchReport = MutableStateFlow<InDepthMatchReport?>(null)
    val inDepthMatchReport: StateFlow<InDepthMatchReport?> = _inDepthMatchReport.asStateFlow()

    fun sendOracleMessage(question: String) {
        if (question.isBlank()) return
        val userMsg = AstrologyChatMessage(sender = "user", text = question)
        val current = _oracleMessages.value.toMutableList().apply { add(userMsg) }
        _oracleMessages.value = current
        _isOracleThinking.value = true

        viewModelScope.launch {
            val answer = repository.askAstrologyOracle(question, astrologyProfile.value)
            val aiMsg = AstrologyChatMessage(sender = "oracle", text = answer)
            _oracleMessages.value = _oracleMessages.value.toMutableList().apply { add(aiMsg) }
            _isOracleThinking.value = false
        }
    }

    fun generateMatchReportFromBirthdates(
        p1Name: String, p1DobMillis: Long, p1Time: String, p1City: String,
        p2Name: String, p2DobMillis: Long, p2Time: String, p2City: String
    ) {
        val p1Profile = AstrologyEngine.calculateProfileFromDate(p1DobMillis, p1Time, p1City)
        val p2Profile = AstrologyEngine.calculateProfileFromDate(p2DobMillis, p2Time, p2City)

        _isGeneratingMatchReport.value = true

        viewModelScope.launch {
            val report = repository.generateInDepthMatchReport(
                p1Name = p1Name.ifBlank { "Partner 1" },
                p1Dob = formatMillisToDateString(p1DobMillis),
                p1Time = p1Time,
                p1City = p1City,
                p1Sun = p1Profile.sunSign,
                p1Moon = p1Profile.moonSign,
                p1Rising = p1Profile.risingSign,
                p2Name = p2Name.ifBlank { "Partner 2" },
                p2Dob = formatMillisToDateString(p2DobMillis),
                p2Time = p2Time,
                p2City = p2City,
                p2Sun = p2Profile.sunSign,
                p2Moon = p2Profile.moonSign,
                p2Rising = p2Profile.risingSign
            )
            _inDepthMatchReport.value = report
            _isGeneratingMatchReport.value = false
        }
    }

    fun clearMatchReport() {
        _inDepthMatchReport.value = null
    }

    fun clearAllTestResults() {
        viewModelScope.launch {
            repository.clearAllTestResults()
        }
    }

    fun saveCustomNatalChart(
        name: String, dateMillis: Long, timeStr: String, cityStr: String, notes: String
    ) {
        val profile = AstrologyEngine.calculateProfileFromDate(dateMillis, timeStr, cityStr)
        val chart = CustomNatalChart(
            personName = name.ifBlank { "Natal Chart" },
            birthDateMillis = dateMillis,
            birthTime = timeStr,
            birthCity = cityStr,
            sunSign = profile.sunSign,
            moonSign = profile.moonSign,
            risingSign = profile.risingSign,
            notes = notes
        )
        viewModelScope.launch {
            repository.saveNatalChart(chart)
        }
    }

    fun deleteNatalChart(chartId: String) {
        viewModelScope.launch {
            repository.deleteNatalChart(chartId)
        }
    }

    private fun formatMillisToDateString(millis: Long): String {
        val sdf = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(millis))
    }

    private val _testState = MutableStateFlow(TestState())
    val testState: StateFlow<TestState> = _testState.asStateFlow()

    private val _isGeneratingReport = MutableStateFlow(false)
    val isGeneratingReport: StateFlow<Boolean> = _isGeneratingReport.asStateFlow()

    private val _selectedReport = MutableStateFlow<DeepSynthesisReport?>(null)
    val selectedReport: StateFlow<DeepSynthesisReport?> = _selectedReport.asStateFlow()

    private val _showReviewModal = MutableStateFlow(false)
    val showReviewModal: StateFlow<Boolean> = _showReviewModal.asStateFlow()

    fun startTest(test: PsychologyTest) {
        _testState.value = TestState(
            activeTest = test,
            currentQuestionIndex = 0,
            selectedChoices = mutableListOf(),
            isCompleted = false
        )
    }

    fun answerQuestion(choice: Choice) {
        val current = _testState.value
        val active = current.activeTest ?: return

        val newChoices = current.selectedChoices.toMutableList().apply { add(choice) }

        if (current.currentQuestionIndex + 1 < active.questions.size) {
            _testState.value = current.copy(
                currentQuestionIndex = current.currentQuestionIndex + 1,
                selectedChoices = newChoices
            )
        } else {
            // Completed
            _testState.value = current.copy(
                selectedChoices = newChoices,
                isCompleted = true
            )
            viewModelScope.launch {
                repository.saveTestResult(active, newChoices)
            }
        }
    }

    fun exitTest() {
        _testState.value = TestState()
    }

    fun updateAstrologyProfile(dateMillis: Long, timeStr: String, cityStr: String) {
        val newProfile = AstrologyEngine.calculateProfileFromDate(dateMillis, timeStr, cityStr)
        viewModelScope.launch {
            repository.saveAstrologyProfile(newProfile)
        }
    }

    fun updateAstrologySignsDirectly(sun: ZodiacSign, moon: ZodiacSign, rising: ZodiacSign) {
        val current = astrologyProfile.value ?: AstrologyProfile()
        val updated = current.copy(sunSign = sun, moonSign = moon, risingSign = rising)
        viewModelScope.launch {
            repository.saveAstrologyProfile(updated)
        }
    }

    fun generateSynthesisReport(onSuccess: (DeepSynthesisReport) -> Unit, onNeedAdOrGems: () -> Unit) {
        val sub = userSubscription.value
        viewModelScope.launch {
            if (!sub.isPremium && sub.gemsBalance < 10) {
                onNeedAdOrGems()
                return@launch
            }

            _isGeneratingReport.value = true
            val successConsume = repository.consumeGemForReport(sub)
            if (successConsume) {
                val report = repository.generateAndSaveReport(testResults.value, astrologyProfile.value)
                _selectedReport.value = report
                onSuccess(report)
            } else {
                onNeedAdOrGems()
            }
            _isGeneratingReport.value = false
        }
    }

    fun generateMasterMetaReport(onSuccess: (DeepSynthesisReport) -> Unit, onNeedAdOrGems: () -> Unit) {
        val sub = userSubscription.value
        viewModelScope.launch {
            if (!sub.isPremium && sub.gemsBalance < 10) {
                onNeedAdOrGems()
                return@launch
            }

            _isGeneratingReport.value = true
            val successConsume = repository.consumeGemForReport(sub)
            if (successConsume) {
                val report = repository.generateAndSaveMasterMetaReport(
                    savedReports.value,
                    testResults.value,
                    astrologyProfile.value
                )
                _selectedReport.value = report
                onSuccess(report)
            } else {
                onNeedAdOrGems()
            }
            _isGeneratingReport.value = false
        }
    }

    fun purchaseSingleReportAndGenerate(onSuccess: (DeepSynthesisReport) -> Unit) {
        val sub = userSubscription.value
        viewModelScope.launch {
            repository.grantSingleReportPurchase(sub)
            _isGeneratingReport.value = true
            val newSub = userSubscription.value
            val successConsume = repository.consumeGemForReport(newSub)
            if (successConsume) {
                val report = repository.generateAndSaveReport(testResults.value, astrologyProfile.value)
                _selectedReport.value = report
                onSuccess(report)
            }
            _isGeneratingReport.value = false
        }
    }

    fun subscribeAndGenerate(tier: SubscriptionTier, onSuccess: (DeepSynthesisReport) -> Unit) {
        viewModelScope.launch {
            val isPrem = (tier != SubscriptionTier.FREE)
            repository.updateSubscriptionTier(tier, isPrem)
            _isGeneratingReport.value = true
            val report = repository.generateAndSaveReport(testResults.value, astrologyProfile.value)
            _selectedReport.value = report
            onSuccess(report)
            _isGeneratingReport.value = false
        }
    }

    fun selectReport(report: DeepSynthesisReport?) {
        _selectedReport.value = report
    }

    fun toggleBookmark(reportId: String, currentBookmark: Boolean) {
        viewModelScope.launch {
            repository.toggleBookmarkReport(reportId, currentBookmark)
            _selectedReport.value?.let { current ->
                if (current.id == reportId) {
                    _selectedReport.value = current.copy(isBookmarked = !currentBookmark)
                }
            }
        }
    }

    fun toggleHabitCompletion(report: DeepSynthesisReport, habitIndex: Int) {
        val updatedHabits = report.dailyActionPlan.mapIndexed { idx, item ->
            if (idx == habitIndex) item.copy(isCompleted = !item.isCompleted) else item
        }
        val updatedReport = report.copy(dailyActionPlan = updatedHabits)
        _selectedReport.value = updatedReport

        viewModelScope.launch {
            repository.updateReportActionPlan(updatedReport)
        }
    }

    fun purchaseSingleReportOnly() {
        viewModelScope.launch {
            repository.grantSingleReportPurchase(userSubscription.value)
        }
    }

    fun openReviewModal() {
        _showReviewModal.value = true
    }

    fun dismissReviewModal() {
        _showReviewModal.value = false
    }

    fun submitAppReview(rating: Int, comment: String) {
        viewModelScope.launch {
            repository.grantReviewReward(userSubscription.value)
        }
    }

    private val _nameMeaningReport = MutableStateFlow<com.example.data.model.NameMeaningReport?>(null)
    val nameMeaningReport: StateFlow<com.example.data.model.NameMeaningReport?> = _nameMeaningReport.asStateFlow()

    private val _isGeneratingNameReport = MutableStateFlow(false)
    val isGeneratingNameReport: StateFlow<Boolean> = _isGeneratingNameReport.asStateFlow()

    fun updateUserName(name: String) {
        val current = astrologyProfile.value ?: AstrologyProfile()
        val trimmed = name.trim()
        val updatedList = current.savedNameAdditions.toMutableList()
        if (trimmed.isNotBlank() && !updatedList.contains(trimmed)) {
            updatedList.add(trimmed)
        }
        val updated = current.copy(userName = trimmed, savedNameAdditions = updatedList)
        viewModelScope.launch {
            repository.saveAstrologyProfile(updated)
        }
    }

    fun addSavedNameAddition(name: String) {
        val current = astrologyProfile.value ?: AstrologyProfile()
        val trimmed = name.trim()
        if (trimmed.isBlank()) return
        val updatedList = current.savedNameAdditions.toMutableList()
        if (!updatedList.contains(trimmed)) {
            updatedList.add(trimmed)
            val updated = current.copy(savedNameAdditions = updatedList)
            viewModelScope.launch {
                repository.saveAstrologyProfile(updated)
            }
        }
    }

    fun removeSavedNameAddition(name: String) {
        val current = astrologyProfile.value ?: AstrologyProfile()
        val updatedList = current.savedNameAdditions.filter { it != name.trim() }
        val updated = current.copy(savedNameAdditions = updatedList)
        viewModelScope.launch {
            repository.saveAstrologyProfile(updated)
        }
    }

    fun generateNameMeaningReport(targetName: String = "") {
        _isGeneratingNameReport.value = true
        viewModelScope.launch {
            val nameToUse = targetName.ifBlank { astrologyProfile.value?.userName ?: "Seeker" }
            val report = repository.generateNameMeaningReport(nameToUse, astrologyProfile.value)
            _nameMeaningReport.value = report
            _isGeneratingNameReport.value = false
        }
    }

    fun dismissNameMeaningReport() {
        _nameMeaningReport.value = null
    }

    fun setSubscriptionTier(tier: SubscriptionTier) {
        viewModelScope.launch {
            val isPrem = (tier != SubscriptionTier.FREE)
            repository.updateSubscriptionTier(tier, isPrem)
        }
    }

    fun redeemPromoCode(code: String): Boolean {
        val trimmed = code.trim()
        if (trimmed.equals("betatest", ignoreCase = true)) {
            viewModelScope.launch {
                repository.updateSubscriptionTier(SubscriptionTier.ANNUAL_PRO, isPremium = true)
            }
            return true
        }
        return false
    }
}
