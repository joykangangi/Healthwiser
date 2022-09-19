package com.example.healthwiser.viewmodel

import androidx.lifecycle.LiveData
import com.example.healthwiser.db.DiseaseDao
import com.example.healthwiser.model.Disease
import com.example.healthwiser.network.RetrofitInstance

class HealthRepository(private val diseaseDao: DiseaseDao) {
    val allDiseases: LiveData<List<Disease>> = diseaseDao.getAllDiseases()

    suspend fun getAllDiseases(pageNumber: Int) =
        RetrofitInstance.api.generalDiseases(pageNumber)

    suspend fun searchDiseases(,searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchDiseases(searchQuery, pageNumber)
}