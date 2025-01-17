package com.example.workhourstracker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.workhourstracker.models.WorkDay
import java.util.Date

class ProductivityReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productivity_report)

        // Exemple de données
        val workDays = listOf(
            WorkDay(1, 1, Date(), Date(), Date()),
            WorkDay(2, 1, Date(), Date(), Date())
        )

        // Calcule la productivité (exemple simple)ok
        val totalHours = workDays.sumOf { it.endTime?.time?.minus(it.startTime.time) ?: 0 }
        val tvReport = findViewById<TextView>(R.id.tvReport)
        tvReport.text = "Total des heures travaillées : ${totalHours / 3600000} heures"
    }
}