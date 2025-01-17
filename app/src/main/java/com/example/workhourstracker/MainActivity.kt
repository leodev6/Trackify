package com.example.workhourstracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workhourstracker.data.AppDatabase
import com.example.workhourstracker.models.WorkDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var currentWorkDay: WorkDay? = null
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val workDayDao by lazy { database.workDayDao() }

    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser les vues
        val btnClockIn = findViewById<Button>(R.id.btnClockIn)
        val btnClockOut = findViewById<Button>(R.id.btnClockOut)
        val btnStartPause = findViewById<Button>(R.id.btnStartPause)
        val btnEndPause = findViewById<Button>(R.id.btnEndPause)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val tvSummary = findViewById<TextView>(R.id.tvSummary)
        val tvClockInTime = findViewById<TextView>(R.id.tvClockInTime)
        val tvClockOutTime = findViewById<TextView>(R.id.tvClockOutTime)
        val tvPauseStartTime = findViewById<TextView>(R.id.tvPauseStartTime)
        val tvPauseEndTime = findViewById<TextView>(R.id.tvPauseEndTime)

        // Gestion du clic sur "Pointer l'entrée"
        btnClockIn.setOnClickListener {
            val startTime = Date()
            currentWorkDay = WorkDay(userId = 1, date = startTime, startTime = startTime)
            tvStatus.text = "Statut : Pointé (Entrée)"
            tvClockInTime.text = "Heure d'entrée : ${timeFormat.format(startTime)}"
            btnClockIn.isEnabled = false
            btnClockOut.isEnabled = true
            btnStartPause.isEnabled = true
        }

        // Gestion du clic sur "Pointer la sortie"
        btnClockOut.setOnClickListener {
            val endTime = Date()
            currentWorkDay = currentWorkDay?.copy(endTime = endTime)
            tvStatus.text = "Statut : Pointé (Sortie)"
            tvClockOutTime.text = "Heure de sortie : ${timeFormat.format(endTime)}"
            btnClockOut.isEnabled = false
            btnStartPause.isEnabled = false
            btnEndPause.isEnabled = false
            calculateAndDisplaySummary(tvSummary)

            // Enregistrer le WorkDay dans la base de données
            currentWorkDay?.let { workDay ->
                CoroutineScope(Dispatchers.IO).launch {
                    workDayDao.insert(workDay)
                }
            }
        }

        // Gestion du clic sur "Démarrer la pause"
        btnStartPause.setOnClickListener {
            val pauseStart = Date()
            currentWorkDay = currentWorkDay?.copy(pauseStart = pauseStart)
            tvStatus.text = "Statut : En pause"
            tvPauseStartTime.text = "Début de pause : ${timeFormat.format(pauseStart)}"
            btnStartPause.isEnabled = false
            btnEndPause.isEnabled = true
        }

        // Gestion du clic sur "Arrêter la pause"
        btnEndPause.setOnClickListener {
            val pauseEnd = Date()
            currentWorkDay = currentWorkDay?.copy(pauseEnd = pauseEnd)
            tvStatus.text = "Statut : Pointé (Entrée)"
            tvPauseEndTime.text = "Fin de pause : ${timeFormat.format(pauseEnd)}"
            btnEndPause.isEnabled = false
            btnStartPause.isEnabled = true
        }

        // Bouton pour voir l'historique
        val btnViewHistory = findViewById<Button>(R.id.btnViewHistory)
        btnViewHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    // Calcule et affiche le résumé des heures travaillées
    private fun calculateAndDisplaySummary(tvSummary: TextView) {
        val workDay = currentWorkDay ?: return

        val totalHoursWorked = if (workDay.endTime != null) {
            val duration = workDay.endTime.time - workDay.startTime.time
            (duration / 3600000.0) // Convertir en heures
        } else {
            0.0
        }

        val summary = """
            Heures travaillées : ${"%.2f".format(totalHoursWorked)}h
            Pause : ${if (workDay.pauseStart != null && workDay.pauseEnd != null) "Oui" else "Non"}
        """.trimIndent()

        tvSummary.text = summary
    }
}