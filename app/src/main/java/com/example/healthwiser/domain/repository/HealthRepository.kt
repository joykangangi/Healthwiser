package com.example.healthwiser.domain.repository

import androidx.lifecycle.LiveData
import com.example.healthwiser.data.roomdb.DiseaseDao
import com.example.healthwiser.domain.model.Disease
import com.example.healthwiser.data.network.RetrofitInstance

class HealthRepository(private val diseaseDao: DiseaseDao) {
    val allDiseases: LiveData<List<Disease>> = diseaseDao.getAllDiseases()

    suspend fun getAllDiseases(pageNumber: Int) =
        RetrofitInstance.api.generalDiseases(pageNumber)

    suspend fun searchDiseases(,searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchDiseases(searchQuery, pageNumber)
}