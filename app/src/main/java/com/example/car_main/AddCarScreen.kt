package com.example.car_main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.car_main.home.MyTopAppBar
import com.example.car_main.navigation.NavigationDestination
import kotlinx.coroutines.launch



object AddCarDestination : NavigationDestination {
    override val route = "car_add_screen"
    override val titleRes = R.string.car_add
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen(
    navigateBack: () -> Unit,
    viewModel: AddCarViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = { MyTopAppBar(title = "Add Car", canNavigateBack = true, navigateBack= navigateBack) },

   ) {innerPadding ->
        AddCarBody(
            carUiState =viewModel.carUiState,
            onCarValueChange = viewModel::updateUiState,
            onSaveClick = {

                coroutineScope.launch {
                    viewModel.saveItem()
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
                .fillMaxWidth())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarBody(
    carUiState: CarUiState,
    onCarValueChange: (CarDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var carDetails = carUiState.carDetails

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
            onValueChange = { onCarValueChange(carDetails.copy(brand = it))  },
            label = { Text(text = "Brand") },
            modifier = Modifier
                .fillMaxWidth() // Fill the available width
                .height(72.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green)
        )
        OutlinedTextField(
            value = carDetails.model,
            onValueChange = { onCarValueChange(carDetails.copy(model = it))},
            label = { Text(text = "Model") },
            modifier = Modifier
                .fillMaxWidth() // Fill the available width
                .height(72.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green)
        )
        var yearText by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = yearText,
            onValueChange = { yearText = it
                onCarValueChange(carDetails.copy(year = yearText.text.toIntOrNull() ?: 100))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text(text = "Year") },
            modifier = Modifier
                .fillMaxWidth() // Fill the available width
                .height(72.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green)
        )
        //LazyColumn(modifier = Modifier) {
//            item {
//                Button(onClick = {
//                    launcher.launch("image/*")
//                }) {
//                    Text(text = "Choose a Photo")
//                }
//            }
//            item {
//                selectedImageUri?.let { uri ->
//                    AsyncImage(
//                        model = uri,
//                        contentDescription = null,
//                        modifier = Modifier.fillMaxWidth(),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//            }
        //            item {
//                Button(onClick = {
//                    launcher.launch(PickVisualMediaRequest(
//                        ActivityResultContracts.PickVisualMedia.ImageOnly
//                    ))
//                }) {
//                    Text(text = "Choose a Photo")
//                }
//            }
//            item {
//                AsyncImage(
//                    model = selectedImageUri,
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxWidth(),
//                    contentScale = ContentScale.Crop
//                    )
//            }
       // }


        Button(
            onClick = onSaveClick,
            enabled = carUiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save Car")
        }
    }

}



@Composable
fun ItemInputForm(
    //itemDetails: ItemDetails,
    modifier: Modifier = Modifier,
    //onValueChange: (ItemDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
//        OutlinedTextField(
//            value = itemDetails.name,
//            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
//            label = { Text(stringResource(R.string.item_name_req)) },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
//        OutlinedTextField(
//            value = itemDetails.price,
//            onValueChange = {  }, //onValueChange(itemDetails.copy(price = it))
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
//            label = {  },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
//
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
//        OutlinedTextField(
//            value = itemDetails.quantity,
//            onValueChange = { onValueChange(itemDetails.copy(quantity = it)) },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            label = { Text(stringResource(R.string.quantity_req)) },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
        if (enabled) {
            Text(
                text = "ideeem",//stringResource(R.string.required_fields),
                modifier = Modifier.padding(30.dp)
            )
        }
    }
}

