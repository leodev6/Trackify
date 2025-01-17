package com.example.workhourstracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workhourstracker.models.WorkDay
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDayDao {

    @Insert
    suspend fun insert(workDay: WorkDay)

    @Query("SELECT * FROM work_days WHERE userId = :userId ORDER BY date DESC")
    fun getWorkDaysByUser(userId: Int): Flow<List<WorkDay>> // Utilise le Flow pour observer les changements
}