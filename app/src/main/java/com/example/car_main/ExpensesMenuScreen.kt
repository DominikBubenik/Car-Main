package com.example.car_main

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.home.MyTopAppBar
import com.example.car_main.navigation.NavigationDestination

object ExpensesMenuDestination : NavigationDestination {
    //override val route = "car_details_screen"
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
    val carUiState = viewModel.carUiState

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = uiState.value.carDetails.brand + " Details",
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        },

        ) { innerPadding ->

        CarDetailsBody(
            navController,
            carId = carId,
            carUiState = carUiState,
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
    carUiState: CarUiState,
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
                val kind = "Servise"
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


//CarDetailsBody(
////carDetails = viewModel.carUiState.carDetails,
//carUiState = uiState.value,
////onCarValueChange = viewModel::updateUiState,
//onCarValueChange = { carDetails ->
//    viewModel.updateUiState(carDetails)
////                coroutineScope.launch {
////                    viewModel.updateUiState(carDetails)
////                }
//},
//onSaveClick = {
//
//    coroutineScope.launch {
//        viewModel.updateUiState(viewModel.carUiState.carDetails)
//        viewModel.uiState
//        navigateBack()
//    }
//},
//modifier = Modifier
//.padding(
//start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
//top = innerPadding.calculateTopPadding(),
//end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
//)
//.verticalScroll(rememberScrollState())
//.fillMaxWidth()
//)


//
//@Composable
//fun CarDetailsBody(
//    //carDetails: CarDetails,
//    carUiState: CarDetailsUiState,
//    onCarValueChange: (CarDetails) -> Unit = {},
//    onSaveClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var carDetails = carUiState.carDetails
//    //var carDetails by remember { mutableStateOf(carUiState.carDetails) }
//    var brandP = carDetails.brand
//    Column(
//        modifier = modifier.padding(10.dp),
//        verticalArrangement = Arrangement.spacedBy(30.dp)
//    ) {
//        OutlinedTextField(
//            value = brandP,
//            onValueChange = { carDetails = carDetails.copy(brand = it)
//                onCarValueChange(carDetails) },
//            label = { Text(text = "Brand") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(72.dp),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = carDetails.model,
//            onValueChange = { onCarValueChange(carDetails.copy(model = it)) },
//            label = { Text(text = "Model") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(72.dp),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = carDetails.year.toString(),
//            onValueChange = {
//                val year = it.toIntOrNull() ?: 0
//                onCarValueChange(carDetails.copy(year = year))
//            },
//            label = { Text(text = "Year") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(72.dp),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = carDetails.licenceNum,
//            onValueChange = { onCarValueChange(carDetails.copy(licenceNum = it)) },
//            label = { Text(text = "License Number") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(72.dp),
//            singleLine = true
//        )
//        Button(
//            onClick = onSaveClick,
//            //enabled = carUiState.,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Save Car")
//        }
//    }
//}

