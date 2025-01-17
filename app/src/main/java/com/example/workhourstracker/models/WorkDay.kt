package com.example.workhourstracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "work_days")
data class WorkDay(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int, // Référence à l'utilisateur
    val date: Date, // Date du pointage
    val startTime: Date, // Heure d'entrée
    val endTime: Date? = null, // Heure de sortie
    val pauseStart: Date? = null, // Début de la pause
    val pauseEnd: Date? = null, // Fin de la pause
    val totalHoursWorked: Double = 0.0 // Total des heures travaillées
)