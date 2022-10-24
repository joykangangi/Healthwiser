package com.example.healthwiser.presentation.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthwiser.domain.repository.HealthViewModel
import com.example.healthwiser.util.Constants.Companion.SEARCH_DISEASE_TIME_DELAY
import com.example.healthwiser.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(
    navController: NavController,
    healthViewModel: HealthViewModel
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = { SearchViewBar(healthViewModel) }
        ) {
            Box(modifier = Modifier.padding(paddingValues = it)) {
                ResponseList(healthViewModel, navController)
            }
        }
    }
}

@Composable
fun SearchViewBar(healthViewModel: HealthViewModel) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showClearIcon by rememberSaveable {
        mutableStateOf(searchQuery.isNotEmpty())
    }

    var job: Job? = null

    TextField(
        value = searchQuery,
        onValueChange = { onQueryChanged ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_DISEASE_TIME_DELAY)
                if (onQueryChanged.isNotEmpty()) {
                    healthViewModel.searchDiseases(onQueryChanged)
                    showClearIcon = true
                }
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "search Icon",
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { searchQuery = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Cancel search"
                    )
                }
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = { Text(text = "Search Disease") },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )


}

@Composable
fun ResponseList(healthViewModel: HealthViewModel, navController: NavController) {

    val diseaseSearch by healthViewModel.searchDiseases.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (diseaseSearch) {
            is Resource.Success -> {
                LazyColumn {
                    diseaseSearch?.data?.diseases?.size?.let {
                        items(it) { index ->
                            val disease = diseaseSearch?.data?.diseases?.get(index)
                            if (disease != null) {
                                DiseaseItem(disease = disease) {
                                    navController.navigate("details/$index")
                                }
                            }
                            if (index < diseaseSearch!!.data?.diseases?.size!!) {
                                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                }
            }
            is Resource.Error -> {
                diseaseSearch?.message?.let { message ->
                    Text(text = message, color = MaterialTheme.colors.error)
                }
            }
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
            }
            null -> {
                Text(
                    "Loading.....",
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}