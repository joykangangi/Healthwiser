package com.example.healthwiser.model

data class Disease(
    val data_updated_at: String,
    val diagnosis: String,
    val facts: List<String>,
    val id: Int,
    val is_active: Boolean,
    val more: String,
    val name: String,
    val prevention: String,
    val symptoms: String,
    val transmission: String,
    val treatment: String
)