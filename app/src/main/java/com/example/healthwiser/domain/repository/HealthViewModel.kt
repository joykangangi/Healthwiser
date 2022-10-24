package com.example.healthwiser.domain.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.healthwiser.data.remote.dto.Disease
import com.example.healthwiser.data.remote.dto.HealthResponse
import com.example.healthwiser.data.repository.HealthRepository
import com.example.healthwiser.util.Constants.Companion.SEARCH_DISEASE_TIME_DELAY
import com.example.healthwiser.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import retrofit2.Response



class HealthViewModel(val healthRepository: HealthRepository) : ViewModel() {
    val allDiseases: MutableLiveData<Resource<HealthResponse>> = MutableLiveData()
    val allDiseasePage = 1

    val searchDiseases: MutableLiveData<Resource<HealthResponse>> = MutableLiveData()
    val searchDiseasePage = 1

    init {
        getAllDiseases()
    }
    
    fun getAllDiseases() = viewModelScope.launch {
        // Loading state in mutableLiveData
        allDiseases.postValue(Resource.Loading(refresh = true))
        val response = healthRepository.getAllDiseases(allDiseasePage)
        allDiseases.postValue(handleHomeDiseasesResponse(response))
    }

    fun searchDiseases(searchQuery: String) = viewModelScope.launch {
        searchDiseases.postValue(Resource.Loading(refresh = true))
        val response = healthRepository.searchDiseases(searchQuery, searchDiseasePage)
        searchDiseases.postValue(handleSearchDiseaseResponse(response))
    }

    private fun handleHomeDiseasesResponse(response: Response<HealthResponse>): Resource<HealthResponse> {
        if (response.isSuccessful) {
            response.body()?.let { healthResponse ->
                return Resource.Success(healthResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchDiseaseResponse(response: Response<HealthResponse>): Resource<HealthResponse> {
        if (response.isSuccessful) {
            response.body()?.let { searchResponse ->
                return Resource.Success(searchResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getDisease(diseaseIndex: Int):Disease? {
        return allDiseases.value?.data?.diseases?.get(diseaseIndex)
    }

}

class HealthViewModelProviderFactory(private val healthRepository: HealthRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HealthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HealthViewModel(healthRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}