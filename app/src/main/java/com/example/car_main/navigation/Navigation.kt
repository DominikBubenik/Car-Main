package com.example.car_main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.car_main.add_screens.AddCarDestination
import com.example.car_main.add_screens.AddCarScreen
import com.example.car_main.add_screens.AddExpensesDestination
import com.example.car_main.add_screens.AddExpensesScreen
import com.example.car_main.add_screens.ExpensesMenuDestination
import com.example.car_main.add_screens.ExpensesMenuScreen
import com.example.car_main.four_main_screens.GraphDestination
import com.example.car_main.four_main_screens.GraphsScreen
import com.example.car_main.four_main_screens.StatsDestination
import com.example.car_main.four_main_screens.StatsScreen
import com.example.car_main.four_main_screens.TimeLineDestination
import com.example.car_main.four_main_screens.TimeLineScreen
import com.example.car_main.four_main_screens.HomeDestination
import com.example.car_main.four_main_screens.HomeScreen

/**
 * provides navigation in the whole app
 * src for template: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#0
 */
@Composable
fun Navigation(
    navController: NavHostController
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
         * navigation for Graphs screen
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

        /**
         * navigation for AddExpense screen
         */
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
    }
}