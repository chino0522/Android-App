package com.bcit.miniapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bcit.miniapp.MyApp
import com.bcit.miniapp.data.CatRepository

enum class Screen (val route: String) {
    Home("home"),
    Favourite("favourite")
}

data class NavItem (
    val icon : ImageVector,
    val route : String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(catRepository: CatRepository) {

    val catState = CatState(catRepository)

    val navController = rememberNavController()

    val favourites = remember { mutableStateListOf<FavoriteItem>() } // List to store favorites
    LaunchedEffect(
        key1 = catState,
        block = {
            catState.getCatFact()
            catState.getCatImage()
        }
    )

    Scaffold (
        bottomBar = {
            MyBottomNavBar(navController = navController)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Home.route,
            builder = {

                composable(Screen.Home.route) {
                    Home(catState = catState, favourites = favourites)
                }

                composable(Screen.Favourite.route) {
                    Favourite(favourites)
                }
            }
        )
    }
}