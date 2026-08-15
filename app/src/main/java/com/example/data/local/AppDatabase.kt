package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        TestResultEntity::class,
        AstrologyProfileEntity::class,
        SavedReportEntity::class,
        UserSubscriptionEntity::class,
        CustomNatalChartEntity::class
    ],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testResultDao(): TestResultDao
    abstract fun astrologyProfileDao(): AstrologyProfileDao
    abstract fun savedReportDao(): SavedReportDao
    abstract fun userSubscriptionDao(): UserSubscriptionDao
    abstract fun natalChartDao(): NatalChartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "psyche_astrology_db"
                )
                    // Use the no-arg form — Room's API does not accept `dropAllTables = true` here.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
