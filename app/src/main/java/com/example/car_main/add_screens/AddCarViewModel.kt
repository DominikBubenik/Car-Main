package com.example.car_main.add_screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.car_main.data.Car
import com.example.car_main.data.CarsRepository

/**
 * src for template: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#0
 */
class AddCarViewModel(private val carsRepository: CarsRepository) : ViewModel() {
    var carUiState by mutableStateOf(CarUiState())
        private set


    fun updateUiState(carDetails: CarDetails) {
        carUiState =
            CarUiState(carDetails = carDetails, isEntryValid = validateInput(carDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            carsRepository.insertCar(carUiState.carDetails.toItem())
        }
    }

    private fun validateInput(uiState: CarDetails = carUiState.carDetails): Boolean {
        return with(uiState) {
            brand.isNotBlank() && model.isNotBlank() && year > 1800
        }
    }
}

data class CarUiState(
    val carDetails: CarDetails = CarDetails(),
    val isEntryValid: Boolean = false
)

data class CarDetails(
    val id:Int = 0,
    val brand: String = "",
    val model: String = "",
    val year: Int = 0,
    val licenceNum: String = "",
    val fuelType: String = "",
    var isActive: Boolean = false
    , val imageUri: String? = ""
)

fun CarDetails.toItem(): Car = Car(
    id = id,
    brand = brand,
    model = model,
    year = year,
    licenceNum = licenceNum,
    fuelType = fuelType,
    isActive = isActive
    , imageUri = imageUri
)

fun Car.toCarDetails(): CarDetails = CarDetails(
    id = id,
    brand = brand,
    model = model,
    year = year,
    licenceNum = licenceNum,
    fuelType = fuelType,
    isActive = isActive,
    imageUri = imageUri
)