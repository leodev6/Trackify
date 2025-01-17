# Trackify - Application de Gestion des Heures de Travail

## Description

**Trackify** est une application mobile avancée pour gérer les heures de travail et les congés. Elle permet aux utilisateurs de pointer leurs heures d'entrée, de pause, et de sortie, tout en offrant un suivi complet des heures travaillées. Elle intègre également une gestion des congés et des rapports de productivité.

## Fonctionnalités Principales

### Gestion des Heures de Travail
- **Pointage des heures** :
  - **Entrée** : Enregistrer l'heure d'arrivée au travail.
  - **Pause** : Pointer le début et la fin des pauses.
  - **Sortie** : Enregistrer l'heure de fin de journée.
- **Calcul Automatique** :
  - Total des heures travaillées dans la journée.
  - Indication claire si une pause a été effectuée.
- **Statut en Temps Réel** :
  - Statuts dynamiques : **Entré**, **En Pause**, ou **Sorti**.

### Historique des Pointages
- Affichage détaillé des données de chaque journée de travail :
  - Date
  - Heure d'entrée et de sortie
  - Heures de début et fin de pause
  - Total des heures travaillées
  - Nom de l'utilisateur
- Gestion des données via un **RecyclerView**.

### Gestion des Congés
- **Demandes de Congé** :
  - Soumission d'une demande via l'activité `LeaveRequestActivity`.
  - Suivi de l'état des demandes (en attente, approuvées, ou refusées).
- **Historique des Congés** :
  - Consultation des congés passés et en cours.

### Rapports de Productivité
- **Rapports Journaliers/Mensuels** :
  - Visualisation des heures travaillées.
  - Statistiques sur les pauses et la productivité globale.
- Génération des rapports via `ProductivityReportActivity`.

---

## Structure du Code

### Activités et Fichiers Principaux

1. **`MainActivity.kt`** :
   - Contient la logique principale de l'application, y compris le pointage des heures et l'affichage en temps réel des statuts.
  

```markdown
## Initialiser les vues

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
```
   
2. **Modèles de Données** :
   - `WorkDay.kt` : Représente une journée de travail avec les informations sur les heures travaillées et les pauses.
 ```markdown
    ## Model WorkDay
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

 ```
   - `User.kt` : Représente les données utilisateur (nom, identifiant, etc.).

     ```markdown
          ## Model User
          data class User(
          val id: Int,
          val name: String,
          val email: String
          )
  
  ```
  ```
  - `LeaveRequest.kt` : Représente une demande de congé avec les détails (motif, dates, statut).

  ```markdown
      ## Model LeaveRequest
      @Entity(tableName = "leave_requests") // Assurez-vous que le nom de la table est correct
      data class LeaveRequest(
          @PrimaryKey(autoGenerate = true) val id: Int = 0,
          val userId: Int, // Référence à l'utilisateur
          val startDate: Date, // Date de début
          val endDate: Date, // Date de fin
          val reason: String, // Raison du congé
          val status: String // Statut : "En attente", "Approuvé", "Rejeté"
      )

  ```

   - `Leave.kt` : Représente un congé approuvé.

  ```markdown
      ## Model Leave
      data class Leave(
      val id: Int,
      val userId: Int, // Référence à l'utilisateur
      val startDate: Date,
      val endDate: Date,
      val reason: String // Raison du congé
      )

  ```

3. **Interface Utilisateur** :
   - `item_work_day.xml` : Définit l'apparence de chaque élément dans la liste des jours de travail.
   - `activity_leave_request.xml` : Interface pour la soumission des demandes de congé.

4. **Adaptateurs** :
   - `WorkHoursAdapter.kt` : Gère l'affichage des journées de travail dans un `RecyclerView`.

5. **Activités Secondaires** :
   - `LeaveRequestActivity.kt` : Permet aux utilisateurs de soumettre et gérer leurs demandes de congé.
   - `ProductivityReportActivity.kt` : Affiche des rapports détaillés sur la productivité.

---

## Installation et Configuration

### Prérequis
- **Android Studio** (dernière version recommandée).
- Un appareil Android ou un émulateur avec une version minimale d'API 21 (Lollipop).
- Kotlin configuré comme langage principal.

### Étapes d'Installation
1. Clonez ce dépôt dans votre machine locale :
   ```markdown
   git clone https://github.com/votre-repo/trackify.git
