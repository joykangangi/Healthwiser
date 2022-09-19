package com.example.healthwiser.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthwiser.model.Disease
import com.example.healthwiser.model.HealthResponse
import com.example.healthwiser.network.HealthApi
import com.example.healthwiser.util.Resource
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf as mutableStateOf

class HealthViewModel:ViewModel(val healthRepository: HealthRepository) {
    val diseasesListResponse: List<Disease> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    val allDiseases: MutableLiveData<Resource<HealthResponse>> = MutableLiveData()
    val allDiseasePage = 1

    val searchDiseases: MutableLiveData<Resource<HealthResponse>> = MutableLiveData()
    val searchDiseasePage = 1

    init {
        getAllDiseass()
    }

    fun getAllDiseases() = viewModelScope.launch {
        allDiseases.postValue(Resource.Loading)
        val response = healthRepository.getAllDiseases(allDiseasePage)
        allDiseases.postValue()

        }
    }
