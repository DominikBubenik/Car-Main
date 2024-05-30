package com.example.car_main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.car_main.data.Car
import com.example.car_main.data.CarsRepository

class AddCarViewModel(private val carsRepository: CarsRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var carUiState by mutableStateOf(CarUiState())
        private set

    /**
     * Updates the [carUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(carDetails: CarDetails) {
        carUiState =
            CarUiState(carDetails = carDetails, isEntryValid = validateInput(carDetails))
    }

    /**
     * Inserts an [Item] in the Room database
     */
    suspend fun saveItem() {
        if (validateInput()) {
            carsRepository.insertCar(carUiState.carDetails.toItem())
        }
    }
    suspend fun deleteItem() {
        carsRepository.deleteCar(carUiState.carDetails.toItem())
    }

    private fun validateInput(uiState: CarDetails = carUiState.carDetails): Boolean {
        return with(uiState) {
            brand.isNotBlank() && model.isNotBlank() && year > 1800 //TODO add more constrains
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
    year = year,
    licenceNum = licenceNum,
    fuelType = fuelType,
    isActive = isActive
    , imageUri = imageUri
    //price = price.toDoubleOrNull() ?: 0.0,
    //quantity = quantity.toIntOrNull() ?: 0
)


/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun Car.toItemUiState(isEntryValid: Boolean = false): CarUiState = CarUiState(
    carDetails = this.toCarDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Car.toCarDetails(): CarDetails = CarDetails(
    id = id,
    brand = brand,
    year = year,
    licenceNum = licenceNum,
    fuelType = fuelType,
    isActive = isActive
    , imageUri = imageUri
)