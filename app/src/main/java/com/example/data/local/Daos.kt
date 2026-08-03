package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TestResultDao {
    @Query("SELECT * FROM test_results ORDER BY completedAtMillis DESC")
    fun getAllTestResults(): Flow<List<TestResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestResult(result: TestResultEntity)

    @Query("DELETE FROM test_results WHERE testId = :testId")
    suspend fun deleteTestResult(testId: String)

    @Query("DELETE FROM test_results")
    suspend fun clearAllTestResults()
}

@Dao
interface AstrologyProfileDao {
    @Query("SELECT * FROM astrology_profile WHERE id = 1 LIMIT 1")
    fun getAstrologyProfile(): Flow<AstrologyProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAstrologyProfile(profile: AstrologyProfileEntity)
}

@Dao
interface SavedReportDao {
    @Query("SELECT * FROM saved_reports ORDER BY createdAtMillis DESC")
    fun getAllSavedReports(): Flow<List<SavedReportEntity>>

    @Query("SELECT * FROM saved_reports WHERE id = :reportId LIMIT 1")
    suspend fun getReportById(reportId: String): SavedReportEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReport(report: SavedReportEntity)

    @Query("UPDATE saved_reports SET isBookmarked = :isBookmarked WHERE id = :reportId")
    suspend fun updateBookmark(reportId: String, isBookmarked: Boolean)

    @Query("UPDATE saved_reports SET dailyActionPlanJson = :planJson WHERE id = :reportId")
    suspend fun updateActionPlan(reportId: String, planJson: String)

    @Query("DELETE FROM saved_reports WHERE id = :reportId")
    suspend fun deleteReport(reportId: String)
}

@Dao
interface UserSubscriptionDao {
    @Query("SELECT * FROM user_subscription WHERE id = 1 LIMIT 1")
    fun getUserSubscription(): Flow<UserSubscriptionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSubscription(subscription: UserSubscriptionEntity)
}

@Dao
interface NatalChartDao {
    @Query("SELECT * FROM natal_charts ORDER BY createdAtMillis DESC")
    fun getAllNatalCharts(): Flow<List<CustomNatalChartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNatalChart(chart: CustomNatalChartEntity)

    @Query("DELETE FROM natal_charts WHERE id = :chartId")
    suspend fun deleteNatalChart(chartId: String)
}
