package com.example.workhourstracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workhourstracker.data.AppDatabase
import com.example.workhourstracker.models.LeaveRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class LeaveRequestActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val leaveRequestDao by lazy { database.leaveRequestDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_request)

        val etReason = findViewById<EditText>(R.id.etReason)
        val btnSubmitLeave = findViewById<Button>(R.id.btnSubmitLeave)

        btnSubmitLeave.setOnClickListener {
            val reason = etReason.text.toString()
            val leaveRequest = LeaveRequest(
                userId = 1, // Exemple : utilisateur 1
                startDate = Date(),
                endDate = Date(),
                reason = reason,
                status = "En attente"
            )

            // Enregistrer la demande de congé dans la base de données
            CoroutineScope(Dispatchers.IO).launch {
                leaveRequestDao.insert(leaveRequest)
            }

            // Afficher un message de confirmation
            Toast.makeText(this, "Demande de congé soumise", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}