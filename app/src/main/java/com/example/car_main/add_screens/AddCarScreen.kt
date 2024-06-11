package com.example.car_main.add_screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.car_main.AppViewModelProvider
import com.example.car_main.MyTopAppBar
import com.example.car_main.R
import com.example.car_main.navigation.NavigationDestination
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


object AddCarDestination : NavigationDestination {
    override val route = "car_add_screen"
    override val titleRes = R.string.car_add
}


@Composable
fun AddCarScreen(
    navigateBack: () -> Unit,
    viewModel: AddCarViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Add Car",
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        },

        ) { innerPadding ->
        AddCarBody(
            carUiState = viewModel.carUiState,
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
                .fillMaxWidth()
        )
    }
}

@Composable
fun AddCarBody(
    carUiState: CarUiState,
    onCarValueChange: (CarDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val carDetails = carUiState.carDetails
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        uri -> selectedImageUri = uri
    }


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
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green,
            )
        )

        OutlinedTextField(
            value = carDetails.model,
            onValueChange = { onCarValueChange(carDetails.copy(model = it)) },
            label = { Text(text = "Model") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green,
            )
        )
        var yearText by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = yearText,
            onValueChange = {
                yearText = it
                onCarValueChange(carDetails.copy(year = yearText.text.toIntOrNull() ?: 100))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text(text = "Year") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green,
            )
        )

        OutlinedTextField(
            value = carDetails.licenceNum,
            onValueChange = { onCarValueChange(carDetails.copy(licenceNum = it)) },
            label = { Text(text = "Licence Number") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green,
            )
        )

        OutlinedTextField(
            value = carDetails.fuelType,
            onValueChange = { onCarValueChange(carDetails.copy(fuelType = it)) },
            label = { Text(text = "Fuel Type") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green,
            )
        )

        Button(
            onClick = { launcher.launch("image/*") },
            colors = ButtonDefaults.buttonColors(Green, contentColor = Color.White)
        ) {
            Text(text = "Choose Image")
        }
        if (selectedImageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "Selected Image",
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
        }

        val finalUri = saveImageToInternalStorage(context,selectedImageUri)
        onCarValueChange(carDetails.copy(imageUri = finalUri.toString()))

        Button(
            onClick = onSaveClick ,
            enabled = carUiState.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Green, contentColor = Color.White)
        ) {
            Text(text = "Save Car")
        }
    }

}

/**
 * saving image to internal storage
 */
fun saveImageToInternalStorage(context: Context, imageUri: Uri?): Uri? {
    if (imageUri == null) return null
    val inputStream = context.contentResolver.openInputStream(imageUri)
    inputStream?.use { input ->
        val directory = File(context.filesDir, "car_images")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val fileName = "image_${System.currentTimeMillis()}.jpg" // Generate a unique file name
        val outputFile = File(directory, fileName)
        val outputStream = FileOutputStream(outputFile)
        outputStream.use { output ->
            input.copyTo(output)
        }
        // Return the URI of the saved image
        return outputFile.toUri()
    }
    return null
}


fun deleteFile(context: Context, filePath: String): Boolean {
    val fileToDelete = File(context.filesDir, filePath)

    if (fileToDelete.exists()) {
        return fileToDelete.delete()
    }

    return false
}



//@Composable
//fun ItemInputForm(
//    //itemDetails: ItemDetails,
//    modifier: Modifier = Modifier,
//    //onValueChange: (ItemDetails) -> Unit = {},
//    enabled: Boolean = true
//) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(30.dp)
//    ) {
//        if (enabled) {
//            Text(
//                text = "ideeem",//stringResource(R.string.required_fields),
//                modifier = Modifier.padding(30.dp)
//            )
//        }
//    }
//}

