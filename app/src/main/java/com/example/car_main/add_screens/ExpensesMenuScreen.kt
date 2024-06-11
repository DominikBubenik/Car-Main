package com.example.car_main.add_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.AppViewModelProvider
import com.example.car_main.MyTopAppBar
import com.example.car_main.R
import com.example.car_main.navigation.NavigationDestination

object ExpensesMenuDestination : NavigationDestination {
    override val route = "expenses_menu_screen"
    override val titleRes = R.string.expenses_menu
    const val carIdArg = "carId"
    val routeWithArgs = "$route/{$carIdArg}"
}

@Composable
fun ExpensesMenuScreen(
    navigateBack: () -> Unit,
    viewModel: ExpensesMenuViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsState()
    val carId = uiState.value.carDetails.id

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "${uiState.value.carDetails.brand} Add Expenses",
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        },

        ) { innerPadding ->

        CarDetailsBody(
            navController,
            carId = carId,
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}
@Composable
fun CarDetailsBody(
    navController: NavHostController,
    carId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = {
                val kind = "Fuel"
                navController.navigate("${AddExpensesDestination.route}/$carId/$kind")},
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Fuel Fill-Up", color = Color.White)
        }

        Button(
            onClick = {
                val kind = "Service"
                navController.navigate("${AddExpensesDestination.route}/$carId/$kind")
            },
            colors = ButtonDefaults.buttonColors(Color.Green),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Service", color = Color.White)
        }

        Button(
            onClick = {
                val kind = "Parking"
                navController.navigate("${AddExpensesDestination.route}/$carId/$kind")
            },
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Parking", color = Color.White)
        }

        Button(
            onClick = {
                val kind = "Tolls"
                navController.navigate("${AddExpensesDestination.route}/$carId/$kind")},
            colors = ButtonDefaults.buttonColors(Color(0xFFB4A235)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tolls", color = Color.White)
        }

        Button(
            onClick = {
                val kind = "Other"
                navController.navigate("${AddExpensesDestination.route}/$carId/$kind")
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Other Expenses", color = Color.White)
        }
    }
}

