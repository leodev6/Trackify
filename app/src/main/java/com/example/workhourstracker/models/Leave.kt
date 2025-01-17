package com.example.workhourstracker.models

import java.util.Date

data class Leave(
    val id: Int,
    val userId: Int, // Référence à l'utilisateur
    val startDate: Date,
    val endDate: Date,
    val reason: String // Raison du congé
)