package com.example.healthwiser.data.remote.dto


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * if an entity is from the api, then it should be in dto. But if from local, then Data layer
 */

@Entity(tableName = "diseases")
@JsonClass(generateAdapter = true)
data class Disease(
    @Json(name = "data_updated_at")
    val dataUpdatedAt: String,
    @Json(name = "diagnosis")
    val diagnosis: String?,
    @Json(name = "facts")
    val facts: List<String>,
    @Json(name = "id") @PrimaryKey(autoGenerate = true)
    val id: Int?= null,
    @Json(name = "is_active")
    val isActive: Boolean,
    @Json(name = "more")
    val more: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "prevention")
    val prevention: String?,
    @Json(name = "symptoms")
    val symptoms: String?,
    @Json(name = "transmission")
    val transmission: String?,
    @Json(name = "treatment")
    val treatment: String?
)