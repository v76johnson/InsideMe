package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.AstrologyProfileEntity
import com.example.data.local.CustomNatalChartEntity
import com.example.data.local.SavedReportEntity
import com.example.data.local.TestResultEntity
import com.example.data.local.UserSubscriptionEntity
import com.example.data.model.AstrologyProfile
import com.example.data.model.Choice
import com.example.data.model.CustomNatalChart
import com.example.data.model.DailyHabitItem
import com.example.data.model.DeepSynthesisReport
import com.example.data.model.InDepthMatchReport
import com.example.data.model.MindChatMessage
import com.example.data.model.PsychologyTest
import com.example.data.model.SubscriptionTier
import com.example.data.model.UserSubscription
import com.example.data.model.ZodiacSign
import com.example.data.remote.GeminiReportGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

class PsycheRepository(private val database: AppDatabase) {

    val allTestResults: Flow<List<TestResultEntity>> = database.testResultDao().getAllTestResults()

    val natalCharts: Flow<List<CustomNatalChart>> = database.natalChartDao().getAllNatalCharts().map { list ->
        list.map { entity ->
            CustomNatalChart(
                id = entity.id,
                personName = entity.personName,
                birthDateMillis = entity.birthDateMillis,
                birthTime = entity.birthTime,
                birthCity = entity.birthCity,
                sunSign = try { ZodiacSign.valueOf(entity.sunSignName) } catch (e: Exception) { ZodiacSign.SCORPIO },
                moonSign = try { ZodiacSign.valueOf(entity.moonSignName) } catch (e: Exception) { ZodiacSign.PISCES },
                risingSign = try { ZodiacSign.valueOf(entity.risingSignName) } catch (e: Exception) { ZodiacSign.CANCER },
                notes = entity.notes,
                createdAtMillis = entity.createdAtMillis
            )
        }
    }

    suspend fun saveNatalChart(chart: CustomNatalChart) {
        val entity = CustomNatalChartEntity(
            id = chart.id,
            personName = chart.personName,
            birthDateMillis = chart.birthDateMillis,
            birthTime = chart.birthTime,
            birthCity = chart.birthCity,
            sunSignName = chart.sunSign.name,
            moonSignName = chart.moonSign.name,
            risingSignName = chart.risingSign.name,
            notes = chart.notes,
            createdAtMillis = chart.createdAtMillis
        )
        database.natalChartDao().insertNatalChart(entity)
    }

    suspend fun deleteNatalChart(chartId: String) {
        database.natalChartDao().deleteNatalChart(chartId)
    }

    suspend fun askAstrologyOracle(question: String, profile: AstrologyProfile?, isPremium: Boolean): String {
        return GeminiReportGenerator.askAstrologyOracle(question, profile, isPremium)
    }

    suspend fun askFreeMindCompanion(
        userMessage: String,
        history: List<MindChatMessage>,
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): String {
        return GeminiReportGenerator.askFreeMindCompanion(userMessage, history, testResults, astroProfile)
    }

    suspend fun generateInDepthMatchReport(
        p1Name: String, p1Dob: String, p1Time: String, p1City: String, p1Sun: ZodiacSign, p1Moon: ZodiacSign, p1Rising: ZodiacSign,
        p2Name: String, p2Dob: String, p2Time: String, p2City: String, p2Sun: ZodiacSign, p2Moon: ZodiacSign, p2Rising: ZodiacSign
    ): InDepthMatchReport {
        return GeminiReportGenerator.generateInDepthMatchReport(
            p1Name, p1Dob, p1Time, p1City, p1Sun, p1Moon, p1Rising,
            p2Name, p2Dob, p2Time, p2City, p2Sun, p2Moon, p2Rising
        )
    }

    val astrologyProfile: Flow<AstrologyProfile?> = database.astrologyProfileDao().getAstrologyProfile().map { entity ->
        if (entity != null) {
            val savedAdditions = if (entity.savedNameAdditionsJson.isNotBlank()) {
                entity.savedNameAdditionsJson.split(",").map { it.trim() }.filter { it.isNotEmpty() }
            } else {
                emptyList()
            }
            AstrologyProfile(
                birthDateMillis = entity.birthDateMillis,
                birthTime = entity.birthTime,
                birthCity = entity.birthCity,
                sunSign = try { ZodiacSign.valueOf(entity.sunSignName) } catch (e: Exception) { ZodiacSign.SCORPIO },
                moonSign = try { ZodiacSign.valueOf(entity.moonSignName) } catch (e: Exception) { ZodiacSign.PISCES },
                risingSign = try { ZodiacSign.valueOf(entity.risingSignName) } catch (e: Exception) { ZodiacSign.CANCER },
                userName = entity.userName,
                savedNameAdditions = savedAdditions,
                isProfileConfigured = entity.isProfileConfigured
            )
        } else {
            AstrologyProfile(
                birthDateMillis = 0L,
                birthTime = "12:00",
                birthCity = "",
                sunSign = ZodiacSign.SCORPIO,
                moonSign = ZodiacSign.PISCES,
                risingSign = ZodiacSign.CANCER,
                userName = "",
                savedNameAdditions = emptyList(),
                isProfileConfigured = false
            )
        }
    }

    val savedReports: Flow<List<DeepSynthesisReport>> = database.savedReportDao().getAllSavedReports().map { list ->
        list.map { entity ->
            val traits = jsonArrayToList(entity.coreTraitsJson)
            val shadow = jsonArrayToList(entity.shadowWorkJson)
            val plan = jsonArrayToHabits(entity.dailyActionPlanJson)

            DeepSynthesisReport(
                id = entity.id,
                createdAtMillis = entity.createdAtMillis,
                title = entity.title,
                archetypeSummary = entity.archetypeSummary,
                coreTraits = traits,
                psychologicalBreakdown = entity.psychologicalBreakdown,
                astrologicalSynthesis = entity.astrologicalSynthesis,
                shadowWorkInsights = shadow,
                careerAndPurposeAdvice = entity.careerAndPurposeAdvice,
                relationshipPlaybook = entity.relationshipPlaybook,
                dailyActionPlan = plan,
                isBookmarked = entity.isBookmarked,
                isGeneratedByAi = entity.isGeneratedByAi
            )
        }
    }

    val userSubscription: Flow<UserSubscription> = database.userSubscriptionDao().getUserSubscription().map { entity ->
        if (entity != null) {
            UserSubscription(
                isPremium = entity.isPremium,
                tier = SubscriptionTier.valueOf(entity.tierName),
                gemsBalance = entity.gemsBalance,
                adsWatchedCount = entity.adsWatchedCount,
                adFreeUntilMillis = entity.adFreeUntilMillis,
                hasClaimedReviewBonus = entity.hasClaimedReviewBonus
            )
        } else {
            UserSubscription()
        }
    }

    suspend fun saveTestResult(test: PsychologyTest, selectedChoices: List<Choice>) {
        val traitScores = mutableMapOf<String, Int>()
        selectedChoices.forEach { choice ->
            val current = traitScores.getOrDefault(choice.traitKey, 0)
            traitScores[choice.traitKey] = current + choice.weight
        }

        val dominant = traitScores.maxByOrNull { it.value }?.key ?: "Balanced Seeker"
        val scoresObj = JSONObject()
        traitScores.forEach { (k, v) -> scoresObj.put(k, v) }

        val answersArr = JSONArray()
        test.questions.forEachIndexed { idx, q ->
            val choice = selectedChoices.getOrNull(idx)
            val answerObj = JSONObject()
            answerObj.put("questionIndex", idx + 1)
            answerObj.put("questionId", q.id)
            answerObj.put("questionText", q.text)
            if (choice != null) {
                answerObj.put("selectedChoiceId", choice.id)
                answerObj.put("selectedChoiceText", choice.text)
                answerObj.put("traitKey", choice.traitKey)
                answerObj.put("weight", choice.weight)
            }
            answersArr.put(answerObj)
        }

        val summaryText = "Completed ${test.title} with dominant trait profile '$dominant'."

        val entity = TestResultEntity(
            testId = test.id,
            testTitle = test.title,
            completedAtMillis = System.currentTimeMillis(),
            dominantArchetype = dominant,
            scoresJson = scoresObj.toString(),
            summaryText = summaryText,
            answersJson = answersArr.toString()
        )
        database.testResultDao().insertTestResult(entity)
    }

    suspend fun clearAllTestResults() {
        database.testResultDao().clearAllTestResults()
    }

    suspend fun saveAstrologyProfile(profile: AstrologyProfile) {
        val entity = AstrologyProfileEntity(
            birthDateMillis = profile.birthDateMillis,
            birthTime = profile.birthTime,
            birthCity = profile.birthCity,
            sunSignName = profile.sunSign.name,
            moonSignName = profile.moonSign.name,
            risingSignName = profile.risingSign.name,
            userName = profile.userName,
            savedNameAdditionsJson = profile.savedNameAdditions.distinct().joinToString(","),
            isProfileConfigured = profile.isConfigured
        )
        database.astrologyProfileDao().saveAstrologyProfile(entity)
    }

    suspend fun generateNameMeaningReport(name: String, profile: AstrologyProfile?): com.example.data.model.NameMeaningReport {
        return GeminiReportGenerator.generateNameMeaningReport(name, profile)
    }

    suspend fun generateAndSaveReport(
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): DeepSynthesisReport {
        val report = GeminiReportGenerator.generateDeepReport(testResults, astroProfile)

        val entity = SavedReportEntity(
            id = report.id,
            createdAtMillis = report.createdAtMillis,
            title = report.title,
            archetypeSummary = report.archetypeSummary,
            coreTraitsJson = listToJsonArray(report.coreTraits),
            psychologicalBreakdown = report.psychologicalBreakdown,
            astrologicalSynthesis = report.astrologicalSynthesis,
            shadowWorkJson = listToJsonArray(report.shadowWorkInsights),
            careerAndPurposeAdvice = report.careerAndPurposeAdvice,
            relationshipPlaybook = report.relationshipPlaybook,
            dailyActionPlanJson = habitsToJsonArray(report.dailyActionPlan),
            isBookmarked = false,
            isGeneratedByAi = report.isGeneratedByAi
        )
        database.savedReportDao().saveReport(entity)
        return report
    }

    suspend fun generateAndSaveMasterMetaReport(
        savedReports: List<DeepSynthesisReport>,
        testResults: List<TestResultEntity>,
        astroProfile: AstrologyProfile?
    ): DeepSynthesisReport {
        val report = GeminiReportGenerator.generateMasterMetaAnalysisReport(savedReports, testResults, astroProfile)

        val entity = SavedReportEntity(
            id = report.id,
            createdAtMillis = report.createdAtMillis,
            title = report.title,
            archetypeSummary = report.archetypeSummary,
            coreTraitsJson = listToJsonArray(report.coreTraits),
            psychologicalBreakdown = report.psychologicalBreakdown,
            astrologicalSynthesis = report.astrologicalSynthesis,
            shadowWorkJson = listToJsonArray(report.shadowWorkInsights),
            careerAndPurposeAdvice = report.careerAndPurposeAdvice,
            relationshipPlaybook = report.relationshipPlaybook,
            dailyActionPlanJson = habitsToJsonArray(report.dailyActionPlan),
            isBookmarked = true,
            isGeneratedByAi = report.isGeneratedByAi
        )
        database.savedReportDao().saveReport(entity)
        return report
    }

    suspend fun toggleBookmarkReport(reportId: String, currentStatus: Boolean) {
        database.savedReportDao().updateBookmark(reportId, !currentStatus)
    }

    suspend fun updateReportActionPlan(report: DeepSynthesisReport) {
        val entity = SavedReportEntity(
            id = report.id,
            createdAtMillis = report.createdAtMillis,
            title = report.title,
            archetypeSummary = report.archetypeSummary,
            coreTraitsJson = listToJsonArray(report.coreTraits),
            psychologicalBreakdown = report.psychologicalBreakdown,
            astrologicalSynthesis = report.astrologicalSynthesis,
            shadowWorkJson = listToJsonArray(report.shadowWorkInsights),
            careerAndPurposeAdvice = report.careerAndPurposeAdvice,
            relationshipPlaybook = report.relationshipPlaybook,
            dailyActionPlanJson = habitsToJsonArray(report.dailyActionPlan),
            isBookmarked = report.isBookmarked,
            isGeneratedByAi = report.isGeneratedByAi
        )
        database.savedReportDao().saveReport(entity)
    }

    suspend fun deleteReport(reportId: String) {
        database.savedReportDao().deleteReport(reportId)
    }

    suspend fun grantVoluntaryAdReward(currentSub: UserSubscription) {
        val newAdsCount = currentSub.adsWatchedCount + 1
        val newGems = currentSub.gemsBalance + 2 // +2 gems per 30s ad (5 ads = 10 gems = 1 report)
        val extraAdFree = 12 * 60 * 60 * 1000L
        val now = System.currentTimeMillis()
        val newAdFreeUntil = if (currentSub.adFreeUntilMillis > now) currentSub.adFreeUntilMillis + extraAdFree else now + extraAdFree

        val entity = UserSubscriptionEntity(
            isPremium = currentSub.isPremium,
            tierName = currentSub.tier.name,
            gemsBalance = newGems,
            adsWatchedCount = newAdsCount,
            adFreeUntilMillis = newAdFreeUntil,
            hasClaimedReviewBonus = currentSub.hasClaimedReviewBonus
        )
        database.userSubscriptionDao().saveSubscription(entity)
    }

    suspend fun grantReviewReward(currentSub: UserSubscription) {
        if (currentSub.hasClaimedReviewBonus) return
        val newGems = currentSub.gemsBalance + 10 // 10 gems bonus = 1 free AI report
        val entity = UserSubscriptionEntity(
            isPremium = currentSub.isPremium,
            tierName = currentSub.tier.name,
            gemsBalance = newGems,
            adsWatchedCount = currentSub.adsWatchedCount,
            adFreeUntilMillis = currentSub.adFreeUntilMillis,
            hasClaimedReviewBonus = true
        )
        database.userSubscriptionDao().saveSubscription(entity)
    }

    suspend fun grantSingleReportPurchase(currentSub: UserSubscription) {
        val newGems = currentSub.gemsBalance + 10 // 10 gems = 1 full report unlock
        val entity = UserSubscriptionEntity(
            isPremium = currentSub.isPremium,
            tierName = currentSub.tier.name,
            gemsBalance = newGems,
            adsWatchedCount = currentSub.adsWatchedCount,
            adFreeUntilMillis = currentSub.adFreeUntilMillis,
            hasClaimedReviewBonus = currentSub.hasClaimedReviewBonus
        )
        database.userSubscriptionDao().saveSubscription(entity)
    }

    suspend fun consumeGemForReport(currentSub: UserSubscription): Boolean {
        if (currentSub.isPremium) return true
        if (currentSub.gemsBalance >= 10) {
            val entity = UserSubscriptionEntity(
                isPremium = currentSub.isPremium,
                tierName = currentSub.tier.name,
                gemsBalance = currentSub.gemsBalance - 10, // 10 gems per AI synthesis report
                adsWatchedCount = currentSub.adsWatchedCount,
                adFreeUntilMillis = currentSub.adFreeUntilMillis,
                hasClaimedReviewBonus = currentSub.hasClaimedReviewBonus
            )
            database.userSubscriptionDao().saveSubscription(entity)
            return true
        }
        return false
    }

    suspend fun updateSubscriptionTier(tier: SubscriptionTier, isPremium: Boolean) {
        val entity = UserSubscriptionEntity(
            isPremium = isPremium,
            tierName = tier.name,
            gemsBalance = if (isPremium) 99 else 3,
            adsWatchedCount = 0,
            adFreeUntilMillis = if (isPremium) System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000) else 0L,
            hasClaimedReviewBonus = true
        )
        database.userSubscriptionDao().saveSubscription(entity)
    }

    // JSON Helper Utilities
    private fun listToJsonArray(list: List<String>): String {
        val arr = JSONArray()
        list.forEach { arr.put(it) }
        return arr.toString()
    }

    private fun jsonArrayToList(json: String): List<String> {
        return try {
            val arr = JSONArray(json)
            val res = mutableListOf<String>()
            for (i in 0 until arr.length()) {
                res.add(arr.getString(i))
            }
            res
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun habitsToJsonArray(habits: List<DailyHabitItem>): String {
        val arr = JSONArray()
        habits.forEach { habit ->
            val obj = JSONObject().apply {
                put("dayNumber", habit.dayNumber)
                put("title", habit.title)
                put("description", habit.description)
                put("category", habit.category)
                put("isCompleted", habit.isCompleted)
            }
            arr.put(obj)
        }
        return arr.toString()
    }

    private fun jsonArrayToHabits(json: String): List<DailyHabitItem> {
        return try {
            val arr = JSONArray(json)
            val list = mutableListOf<DailyHabitItem>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                list.add(
                    DailyHabitItem(
                        dayNumber = obj.optInt("dayNumber", i + 1),
                        title = obj.optString("title", "Daily Habit"),
                        description = obj.optString("description", ""),
                        category = obj.optString("category", "Mindset"),
                        isCompleted = obj.optBoolean("isCompleted", false)
                    )
                )
            }
            list
        } catch (e: Exception) {
            emptyList()
        }
    }
}
