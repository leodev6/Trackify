package com.example.workhourstracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workhourstracker.models.LeaveRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaveRequestDao {

    @Insert
    suspend fun insert(leaveRequest: LeaveRequest)

    @Query("SELECT * FROM leave_requests WHERE userId = :userId")
    fun getLeaveRequestsByUser(userId: Int): Flow<List<LeaveRequest>>
}