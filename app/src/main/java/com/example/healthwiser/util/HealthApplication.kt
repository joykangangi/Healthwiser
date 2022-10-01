package com.example.healthwiser.util

import android.app.Application
import com.example.healthwiser.data.localdb.DiseaseDatabase
import com.example.healthwiser.data.repository.HealthRepository


/**
 * For only one instance of the database and of the repository in the app.
 * An easy way to achieve this is by creating them as members of the Application class.
 * Then they will just be retrieved from the Application whenever they're needed, rather than constructed every time.
 */

class HealthApplication: Application() {
    private val database by lazy { DiseaseDatabase(this) }
    val repository by lazy { HealthRepository(database.getDiseaseDao()) }
}