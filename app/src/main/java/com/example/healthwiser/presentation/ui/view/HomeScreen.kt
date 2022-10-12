package com.example.healthwiser.presentation.ui.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthwiser.domain.repository.HealthViewModel
import com.example.healthwiser.util.Resource

//Todo -> Needs proper error handling, i,e when not connected to the net

//observe the LiveData as a state in Compose
@Composable
fun HomeScreen(
    navController: NavController,
    healthViewModel: HealthViewModel
) {

    //state
    val diseases by healthViewModel.allDiseases.observeAsState()

    //API one time call
   /* LaunchedEffect(key1 = true) {
        healthViewModel.getAllDiseases()
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(3.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (diseases) {
            is Resource.Success -> {
                LazyColumn {
                    diseases?.data?.diseases?.size?.let {
                        items(it) { index ->
                            val disease = diseases?.data?.diseases?.get(index)
                            if (disease != null) {
                                DiseaseItem(disease = disease) {
                                    navController.navigate("details/$index")
                                }
                            }
                            if (index < diseases!!.data?.diseases?.size!!) {
                                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                }
            }
            is Resource.Error -> {
                Text(
                    "Error! Can't connect to the internet.\n" +
                            "Check your internet connection.",
                    color = MaterialTheme.colors.error
                )
            }
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
            }
            null -> {
                Text(
                    "Error! Can't connect to the internet.\n" +
                            "Check your internet connection.",
                    color = MaterialTheme.colors.error
                )
            }
        }
    }

}
