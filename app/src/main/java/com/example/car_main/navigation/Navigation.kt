package com.example.car_main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.navArgument
import com.example.car_main.HomeDestination
import com.example.car_main.HomeScreen
import com.example.car_main.StatsDestination
import com.example.car_main.StatsScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = Modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(navController = navController)
        }
        composable( route = StatsDestination.route) {
            StatsScreen(navController = navController) //
        }
    }

}
