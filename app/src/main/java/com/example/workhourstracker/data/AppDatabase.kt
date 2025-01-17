package com.example.workhourstracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workhourstracker.models.LeaveRequest
import com.example.workhourstracker.models.WorkDay
import com.example.workhourstracker.utils.Converters

@Database(entities = [WorkDay::class, LeaveRequest::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun workDayDao(): WorkDayDao
    abstract fun leaveRequestDao(): LeaveRequestDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "work_hours_database"
                ).fallbackToDestructiveMigration() // Efface et recrée la base de données
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}