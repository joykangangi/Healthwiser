package com.example.healthwiser.data.repository

import androidx.lifecycle.LiveData
import com.example.healthwiser.data.localdb.DiseaseDao
import com.example.healthwiser.data.remote.dto.Disease
import com.example.healthwiser.data.remote.RetrofitInstance

class HealthRepository(private val diseaseDao: DiseaseDao) {
    val allDiseases: LiveData<List<Disease>> = diseaseDao.getAllDiseases()

    suspend fun getAllDiseases(pageNumber: Int) =
        RetrofitInstance.api.generalDiseases(pageNumber)

    suspend fun searchDiseases(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchDiseases(searchQuery, pageNumber)
}