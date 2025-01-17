package com.example.workhourstracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workhourstracker.R
import com.example.workhourstracker.models.WorkDay
import java.text.SimpleDateFormat
import java.util.Locale

class WorkDayAdapter(private val workDays: List<WorkDay>) :
    RecyclerView.Adapter<WorkDayAdapter.WorkDayViewHolder>() {

    class WorkDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvStartTime: TextView = itemView.findViewById(R.id.tvStartTime)
        val tvEndTime: TextView = itemView.findViewById(R.id.tvEndTime)
        val tvPause: TextView = itemView.findViewById(R.id.tvPause)
        val tvTotalHours: TextView = itemView.findViewById(R.id.tvTotalHours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkDayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_work_day, parent, false)
        return WorkDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkDayViewHolder, position: Int) {
        val workDay = workDays[position]
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        holder.tvDate.text = "Date : ${dateFormat.format(workDay.date)}"
        holder.tvStartTime.text = "Heure d'entrée : ${timeFormat.format(workDay.startTime)}"
        holder.tvEndTime.text = "Heure de sortie : ${workDay.endTime?.let { timeFormat.format(it) } ?: "Non pointé"}"
        holder.tvPause.text = "Pause : ${if (workDay.pauseStart != null && workDay.pauseEnd != null) "Oui" else "Non"}"
        holder.tvTotalHours.text = "Total des heures : ${"%.2f".format(workDay.totalHoursWorked)}h"
    }

    override fun getItemCount(): Int = workDays.size
}