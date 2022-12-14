package com.example.healthwiser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthwiser.domain.repository.HealthViewModel
import com.example.healthwiser.domain.repository.HealthViewModelProviderFactory
import com.example.healthwiser.presentation.ui.theme.HealthWiserTheme
import com.example.healthwiser.presentation.ui.view.*
import com.example.healthwiser.util.HealthApplication

class MainActivity : ComponentActivity() {
    val viewModel: HealthViewModel by viewModels() {
        HealthViewModelProviderFactory((application as HealthApplication).repository)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthWiserTheme {
                MyApp(viewModel)
            }
        }
    }
}

@Composable
fun MyApp(viewModel: HealthViewModel) {
    val appNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Saved",
                        route = "saved",
                        icon = Icons.Default.List
                    ),
                    BottomNavItem(
                        name = "Search",
                        route = "search",
                        icon = Icons.Default.Search
                    )
                ),
                navController = appNavController,
                onItemClick = {
                    appNavController.navigate(it.route)
                }
            )
        }) {
        NavigationComponent(
            navHostController = appNavController,
            modifier = Modifier.padding(it),
            healthViewModel = viewModel
        )
    }

}


@Composable
fun NavigationComponent(
    navHostController: NavHostController,
    modifier: Modifier,
    healthViewModel: HealthViewModel

) {
    NavHost(
        navController = navHostController,
        startDestination = "home",
        modifier = modifier.animateContentSize()
    ) {
        composable("home") {
            HomeScreen(healthViewModel = healthViewModel, navController = navHostController)
        }
        composable("details/{diseaseIndex}"){ backStackEntry ->
        DetailsScreen(
            diseaseIndex = backStackEntry.arguments?.getString("diseaseIndex"),
            healthViewModel = healthViewModel
        )
        }
        composable("saved") {
            SavedScreen()
        }
        composable("search") {
            SearchScreen()
        }
    }
}
