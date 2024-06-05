package com.example.car_main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.home.MyTopAppBar
import com.example.car_main.navigation.NavigationDestination
import kotlinx.coroutines.launch

object AddExpensesDestination : NavigationDestination {
    override val route = "car_add_expenses"
    override val titleRes = R.string.car_add_epenses
    const val carIdArg = "carId"
    val routeWithArgs = "${AddExpensesDestination.route}/{$carIdArg}"
}

@Composable
fun AddExpensesScreen(
    navigateBack: () -> Unit,
    viewModel: CarDetailsScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = { MyTopAppBar(title = "Add Car", canNavigateBack = true, navigateBack= navigateBack) },

        ) { innerPadding ->


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpense(
    carUiState: CarUiState,
    onCarValueChange: (CarDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var carDetails = carUiState.carDetails
//    var selectedImageUri by remember {
//        mutableStateOf<Uri?>(null)
//    }

//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        selectedImageUri = uri
//        uri?.let {
//            onCarValueChange(carDetails.copy(imageUri = it.toString()))
//        }
//    }

    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
//        ItemInputForm(
//            itemDetails = itemUiState.itemDetails,
//            onValueChange = onItemValueChange,
//            modifier = Modifier.fillMaxWidth()
//        )
        //var brand by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(

//            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
            value = carDetails.brand,
            onValueChange = { onCarValueChange(carDetails.copy(brand = it)) },
            label = { Text(text = "Brand") },
            modifier = Modifier
                .fillMaxWidth() // Fill the available width
                .height(72.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Green
            )
        )
    }
}
