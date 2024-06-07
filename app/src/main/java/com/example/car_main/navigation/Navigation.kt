package com.example.car_main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.car_main.AddCarDestination
import com.example.car_main.AddCarScreen
import com.example.car_main.AddExpensesDestination
import com.example.car_main.AddExpensesScreen
import com.example.car_main.ExpensesMenuDestination
import com.example.car_main.ExpensesMenuScreen
import com.example.car_main.GraphDestination
import com.example.car_main.GraphsScreen
import com.example.car_main.home.HomeDestination
import com.example.car_main.home.HomeScreen
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
        /**
         * navigation for Home screen
         */
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddCar = { navController.navigate(AddCarDestination.route) },
                navigateToExpenesMenu = {
                    navController.navigate("${ExpensesMenuDestination.route}/${it}")
                },
                navController = navController)
        }


        /**
         * navigation for AddCar screen
         */
        composable(route = AddCarDestination.route) {
            AddCarScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController)
        }


        /**
         * navigation for Stats screen
          */
        composable(
            route = StatsDestination.routeWithArgs,
            arguments = listOf(navArgument(StatsDestination.carIdArg) {
                type = NavType.IntType
            })
        ) {
            StatsScreen(
                navigateBack = { navController.navigate(HomeDestination.route) },
                navController = navController) //
        }

        /**
         * navigation for TimeLine screen
         */
        composable(
            route = TimeLineDestination.routeWithArgs,
            arguments = listOf(navArgument(TimeLineDestination.carIdArg) {
                type = NavType.IntType
            })
        ){
                TimeLineScreen(
                    navigateBack = { navController.navigate(HomeDestination.route) },
                    navController = navController)
        }

        /**
         * navigation for Graph screen
         */
        composable(
            route = GraphDestination.routeWithArgs,
            arguments = listOf(navArgument(GraphDestination.carIdArg) {
                type = NavType.IntType
            })
        ) {
            GraphsScreen(
                navigateBack = { navController.navigate(HomeDestination.route) },
                navController = navController)
        }

        /**
         * navigation for ExpensesMenu screen
         */
        composable(
            route = ExpensesMenuDestination.routeWithArgs,
            arguments = listOf(navArgument(ExpensesMenuDestination.carIdArg) {
                type = NavType.IntType
            })
        ) {
            ExpensesMenuScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController)
        }
        composable(
            route = AddExpensesDestination.routeWithArgs,
            arguments = listOf(
                navArgument(AddExpensesDestination.carIdArg) { type = NavType.IntType },
                navArgument(AddExpensesDestination.kindArg) { type = NavType.StringType }
            )
        ) {
            AddExpensesScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController)
        }
        composable(
            route = AddExpensesDestination.routeWithArgs,
            arguments = listOf(navArgument(AddExpensesDestination.carIdArg) {
                type = NavType.IntType
            })
        ) {
            AddExpensesScreen(
                navigateBack = { navController.popBackStack() },
                navController = navController)
        }

    }

}
