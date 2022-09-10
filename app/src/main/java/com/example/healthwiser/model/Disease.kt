package com.example.healthwiser.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "diseases")
@JsonClass(generateAdapter = true)
data class Disease(
    @PrimaryKey(autoGenerate = true) val iD: Long? = null,
    @Json(name = "data_updated_at")
    val dataUpdatedAt: String,
    @Json(name = "diagnosis")
    val diagnosis: String,
    @Json(name = "facts")
    val facts: List<String>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_active")
    val isActive: Boolean,
    @Json(name = "more")
    val more: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "prevention")
    val prevention: String,
    @Json(name = "symptoms")
    val symptoms: String,
    @Json(name = "transmission")
    val transmission: String,
    @Json(name = "treatment")
    val treatment: String
)