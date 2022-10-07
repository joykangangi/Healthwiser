package com.example.healthwiser.presentation.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthwiser.domain.repository.HealthViewModel

@Composable
fun DetailsScreen(
    diseaseIndex: String?,
    healthViewModel: HealthViewModel
) {
    val disease = diseaseIndex?.let { healthViewModel.getDisease(it.toInt()) }

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        
    }
}