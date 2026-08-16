package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_results")
data class TestResultEntity(
    @PrimaryKey val testId: String,
    val testTitle: String,
    val completedAtMillis: Long,
    val dominantArchetype: String,
    val scoresJson: String, // Key-value JSON or map representation
    val summaryText: String,
    val answersJson: String = "" // Serialized question and answer choices JSON
)

@Entity(tableName = "astrology_profile")
data class AstrologyProfileEntity(
    @PrimaryKey val id: Int = 1, // Single profile row
    val birthDateMillis: Long,
    val birthTime: String,
    val birthCity: String,
    val sunSignName: String,
    val moonSignName: String,
    val risingSignName: String,
    val userName: String = "",
    val savedNameAdditionsJson: String = "",
    val isProfileConfigured: Boolean = false
)

@Entity(tableName = "saved_reports")
data class SavedReportEntity(
    @PrimaryKey val id: String,
    val createdAtMillis: Long,
    val title: String,
    val archetypeSummary: String,
    val coreTraitsJson: String,
    val psychologicalBreakdown: String,
    val astrologicalSynthesis: String,
    val shadowWorkJson: String,
    val careerAndPurposeAdvice: String,
    val relationshipPlaybook: String,
    val dailyActionPlanJson: String,
    val isBookmarked: Boolean,
    val isGeneratedByAi: Boolean
)

@Entity(tableName = "user_subscription")
data class UserSubscriptionEntity(
    @PrimaryKey val id: Int = 1,
    val isPremium: Boolean,
    val tierName: String,
    val gemsBalance: Int,
    val adsWatchedCount: Int,
    val adFreeUntilMillis: Long,
    val hasClaimedReviewBonus: Boolean = false
)

@Entity(tableName = "natal_charts")
data class CustomNatalChartEntity(
    @PrimaryKey val id: String,
    val personName: String,
    val birthDateMillis: Long,
    val birthTime: String,
    val birthCity: String,
    val sunSignName: String,
    val moonSignName: String,
    val risingSignName: String,
    val notes: String = "",
    val createdAtMillis: Long = System.currentTimeMillis()
)
