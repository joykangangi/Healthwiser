package com.example.healthwiser.presentation.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthwiser.R
import com.example.healthwiser.domain.repository.HealthViewModel
import org.w3c.dom.Text

@Composable
fun DetailsScreen(
    diseaseIndex: String?,
    healthViewModel: HealthViewModel
) {
    val disease = diseaseIndex?.let { healthViewModel.getDisease(it.toInt()) }
    val titles = listOf(disease?.symptoms,disease?.transmission,disease?.diagnosis,disease?.treatment,disease?.prevention)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)
        .verticalScroll(rememberScrollState())) {
         Image(
             painter = painterResource(id = R.drawable.medical_equipment),
             contentDescription = stringResource(id = R.string.medical_equipment),
             contentScale = ContentScale.Crop,
             modifier = Modifier
                 .padding(bottom = 16.dp)
                 .fillMaxWidth()
                 .fillMaxHeight(0.5f)
         )
        disease?.name?.let { Text(it, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h4) }
        Text(text = disease?.facts!!.joinToString(" "),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LazyColumn{
            items(titles.size){ index->
                if (titles[index] == null)
                    Text(text = "Read More Here ${disease.more}")
                Text(text = titles[index] )
            }
        }



    }
}



