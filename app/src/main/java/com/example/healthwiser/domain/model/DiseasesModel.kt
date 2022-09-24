package com.example.healthwiser.domain.model

import androidx.room.PrimaryKey

/**
This is an EXPERIMENT to learn how to use mappers. Changing from entity class to this plain Kotlin class
This has no hustle when the room db / api changes., i.e you don't have to change it.
 */


data class DiseasesModel(
    val name: String,
    val dataUpdatedAt: String,
    val diagnosis: String,
    val facts: List<String>,
    val more: String,
    val treatment: String,
    val prevention: String,
    val symptoms: String,
    val transmission: String,
    val isActive: Boolean
)
