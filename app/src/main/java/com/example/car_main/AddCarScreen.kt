package com.example.car_main

import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.car_main.navigation.NavigationDestination
import java.util.Currency
import java.util.Locale

object AddCarDestination : NavigationDestination {
    override val route = "car_add_screen"
    override val titleRes = R.string.car_add
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen(
    navigateBack: () -> Unit,
    navController: NavHostController) {
    Scaffold (
        topBar = {MyTopAppBar(title = "Add Car", canNavigateBack = true, navigateBack= navigateBack)},

   ) {innerPadding ->
        AddCarBody( modifier = Modifier
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
    //itemUiState: ItemUiState,
    //onItemValueChange: (ItemDetails) -> Unit,
    //onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
//        ItemInputForm(
//            itemDetails = itemUiState.itemDetails,
//            onValueChange = onItemValueChange,
//            modifier = Modifier.fillMaxWidth()
//        )
        var brand by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(value = brand,
            onValueChange = { brand = it },
            label = { Text(text = "Brand") },
            modifier = Modifier
                .fillMaxWidth() // Fill the available width
                .height(72.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green)
        )
        var model by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(value = model,
            onValueChange = { model = it },
            label = { Text(text = "Model") },
            modifier = Modifier
                .fillMaxWidth() // Fill the available width
                .height(72.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green)
        )
        var year by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(value = year,
            onValueChange = { year = it },
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

