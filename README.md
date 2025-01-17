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


## Installation et Configuration (suite)

2. Lancez l'application sur un émulateur ou un appareil physique :
   - Assurez-vous qu'un émulateur Android est configuré dans **Android Studio** ou qu'un appareil est connecté en mode développeur avec le débogage USB activé.
   - Cliquez sur le bouton **Run** (icône ▶️) dans Android Studio.
   - Alternativement, utilisez la ligne de commande suivante pour construire et exécuter l'application :
     ```bash
     ./gradlew installDebug
     ```

---

## Utilisation de l'Application

1. **Démarrage** :
   - Ouvrez l'application sur votre appareil ou émulateur.
   - Créez un compte utilisateur ou connectez-vous si l'application intègre un système de gestion des utilisateurs.

2. **Pointage des Heures** :
   - **Entrée** : Appuyez sur le bouton "Entrée" pour enregistrer votre heure d'arrivée.
   - **Pause** : Appuyez sur le bouton "Pause" pour débuter ou terminer une pause.
   - **Sortie** : Appuyez sur le bouton "Sortie" pour enregistrer votre heure de départ.

3. **Visualisation des Données** :
   - Suivez vos statuts en temps réel : **Entré**, **En Pause**, ou **Sorti**.
   - Consultez le total des heures travaillées et vérifiez si une pause a été effectuée.
   - Accédez à l'historique des journées de travail via le menu dédié.

4. **Demandes de Congé** :
   - Accédez à l'activité de gestion des congés via le bouton ou menu correspondant.
   - Soumettez une demande de congé en précisant le motif et les dates.
   - Suivez l'état des demandes (en attente, approuvées, ou refusées).

5. **Rapports de Productivité** :
   - Consultez les rapports journaliers ou mensuels pour visualiser vos performances.
   - Analysez les statistiques sur vos heures travaillées, pauses, et jours de congé.

---

## Développement et Structure du Projet

1. **Activités Principales** :
   - `MainActivity.kt` : Pointage des heures et affichage des statuts en temps réel.
   - `LeaveRequestActivity.kt` : Gestion des demandes de congé.
   - `ProductivityReportActivity.kt` : Visualisation des rapports de productivité.

2. **Modèles de Données** :
   - `WorkDay.kt` : Gère les informations sur une journée de travail (heures d'entrée, sortie, pauses, etc.).
   - `User.kt` : Stocke les données utilisateur (nom, identifiant, etc.).
   - `Leave.kt` et `LeaveRequest.kt` : Gestion des données relatives aux congés.

3. **Interfaces Utilisateur (UI)** :
   - `item_work_day.xml` : Définit l'apparence d'une journée de travail dans la liste.
   - `activity_leave_request.xml` : Interface pour les demandes de congé.

4. **Adaptateurs** :
   - `WorkHoursAdapter.kt` : Gère l'affichage des éléments dans le `RecyclerView` pour les journées de travail.

---

## Contributions

Les contributions sont les bienvenues ! Si vous souhaitez ajouter une fonctionnalité ou signaler un problème :
1. Forkez le dépôt.
2.Branche pour vos modifications :
   ```bash
   git checkout -b feature/ma-nouvelle-fonctionnalite

   
