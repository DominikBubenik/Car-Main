package com.example.car_main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.home.MyTopAppBar
import com.example.car_main.navigation.NavigationDestination
import kotlinx.coroutines.launch

object CarDetailsScrDestination : NavigationDestination {
    override val route = "car_details_screen"
    override val titleRes = R.string.car_detail
    const val carIdArg = "carId"
    val routeWithArgs = "$route/{$carIdArg}"
}

@Composable
fun CarDetailsScreen(
    navigateBack: () -> Unit,
    viewModel: CarDetailsScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsState()
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
            carUiState = uiState.value,
            onCarValueChange = viewModel::updateUiState,
            onSaveClick = {

                coroutineScope.launch {
                    viewModel.uiState
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}


@Composable
fun CarDetailsBody(
    carUiState: CarDetailsUiState,
    onCarValueChange: (CarDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var carDetails = carUiState.carDetails

    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        OutlinedTextField(
            value = carDetails.brand,
            onValueChange = { onCarValueChange(carDetails.copy(brand = it)) },
            label = { Text(text = "Brand") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = carDetails.model,
            onValueChange = { onCarValueChange(carDetails.copy(model = it)) },
            label = { Text(text = "Model") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = carDetails.year.toString(),
            onValueChange = {
                val year = it.toIntOrNull() ?: 0
                onCarValueChange(carDetails.copy(year = year))
            },
            label = { Text(text = "Year") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = carDetails.licenceNum,
            onValueChange = { onCarValueChange(carDetails.copy(licenceNum = it)) },
            label = { Text(text = "License Number") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true
        )
        Button(
            onClick = onSaveClick,
            //enabled = carUiState.,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save Car")
        }
    }
}

