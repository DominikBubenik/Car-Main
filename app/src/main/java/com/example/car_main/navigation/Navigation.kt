package com.example.car_main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.car_main.AddCarDestination
import com.example.car_main.AddCarScreen
import com.example.car_main.HomeDestination
import com.example.car_main.HomeScreen
import com.example.car_main.StatsDestination
import com.example.car_main.StatsScreen
import com.example.car_main.TimeLineDestination
import com.example.car_main.TimeLineScreen

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
            HomeScreen(
                navigateToAddCar = { navController.navigate(AddCarDestination.route) },
                navController = navController)
        }
        composable( route = StatsDestination.route) {
            StatsScreen(navController = navController) //
        }
        composable(route = AddCarDestination.route) {
            AddCarScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController)
        }
        composable(route = TimeLineDestination.route) {
            TimeLineScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController)
        }
    }

}
