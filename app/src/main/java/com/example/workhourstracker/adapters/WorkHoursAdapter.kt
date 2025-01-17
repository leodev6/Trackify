package com.example.workhourstracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workhourstracker.R
import com.example.workhourstracker.models.User
import com.example.workhourstracker.models.WorkDay
import java.text.SimpleDateFormat
import java.util.Locale

class WorkHoursAdapter(private val workDays: List<WorkDay>, private val users: List<User>) :
    RecyclerView.Adapter<WorkHoursAdapter.WorkDayViewHolder>() {

    class WorkDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvHoursWorked: TextView = itemView.findViewById(R.id.tvHoursWorked)
        val tvUser: TextView = itemView.findViewById(R.id.tvUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkDayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_work_day, parent, false)
        return WorkDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkDayViewHolder, position: Int) {
        val workDay = workDays[position]
        val user = users.find { it.id == workDay.userId } // Trouve l'utilisateur correspondant
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // Calcule les heures travaillées
        val hoursWorked = if (workDay.endTime != null) {
            val duration = workDay.endTime.time - workDay.startTime.time
            (duration / 3600000.0) // Convertir en heures
        } else {
            0.0
        }

        // Affiche les données
        holder.tvDate.text = dateFormat.format(workDay.date)
        holder.tvHoursWorked.text = "Heures travaillées: ${"%.2f".format(hoursWorked)}h"
        holder.tvUser.text = "Utilisateur: ${user?.name ?: "Inconnu"}"
    }

    override fun getItemCount(): Int = workDays.size
}