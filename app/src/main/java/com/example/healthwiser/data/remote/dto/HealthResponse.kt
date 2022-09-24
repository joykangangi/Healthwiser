package com.example.healthwiser.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HealthResponse(
    @Json(name = "diseases")
    val diseases: List<Disease>
)