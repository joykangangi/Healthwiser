package com.example.healthwiser.network

import com.example.healthwiser.model.Disease
import com.example.healthwiser.model.HealthResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HealthApi {
    @GET("/diseases.json")
    suspend fun generalDiseases(
        @Query("page")
        pageNumber: Int = 1
    ): Response<HealthResponse>

    @GET("/diseases.json")
    suspend fun searchDiseases(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1
    ): Response<Disease>
}