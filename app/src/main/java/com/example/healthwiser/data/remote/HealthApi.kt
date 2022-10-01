package com.example.healthwiser.data.remote

import com.example.healthwiser.data.remote.dto.Disease
import com.example.healthwiser.data.remote.dto.HealthResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
how Retrofit talks to the web server using HTTP requests.
 */
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