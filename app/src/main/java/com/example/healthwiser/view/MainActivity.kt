package com.example.healthwiser.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthwiser.ui.theme.HealthWiserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthWiserTheme {
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
                    NavigationComponent(navController = appNavController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("saved") {
            SavedScreen()
        }
        composable("search") {
            SearchScreen()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HealthWiserTheme {

    }
}