package com.example.workhourstracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "leave_requests") // Assurez-vous que le nom de la table est correct
data class LeaveRequest(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int, // Référence à l'utilisateur
    val startDate: Date, // Date de début
    val endDate: Date, // Date de fin
    val reason: String, // Raison du congé
    val status: String // Statut : "En attente", "Approuvé", "Rejeté"
)