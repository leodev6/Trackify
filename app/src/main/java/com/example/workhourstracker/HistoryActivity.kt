package com.example.workhourstracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workhourstracker.adapters.WorkDayAdapter
import com.example.workhourstracker.data.AppDatabase
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val workDayDao by lazy { database.workDayDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Initialise le RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe les changements dans la base de données
        lifecycleScope.launch {
            workDayDao.getWorkDaysByUser(1).collect { workDays ->
                val adapter = WorkDayAdapter(workDays)
                recyclerView.adapter = adapter
            }
        }
    }
}