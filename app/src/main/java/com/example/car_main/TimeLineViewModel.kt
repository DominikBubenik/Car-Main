package com.example.car_main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_main.data.CarsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TimeLineViewModel (
    savedStateHandle: SavedStateHandle,
    private val carsRepository: CarsRepository,
) : ViewModel() {

    private val carId: Int = checkNotNull(savedStateHandle[TimeLineDestination.carIdArg])

    /**
     * Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ExpensesUiState> =
        carsRepository.getCarStream(carId)
            .filterNotNull()
            .map {
                ExpensesUiState(carDetails = it.toCarDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ExpensesUiState()
            )
    var carUiState by mutableStateOf(CarUiState())
        private set

    private fun validateInput(uiState: CarDetails = carUiState.carDetails): Boolean {
        return with(uiState) {
            brand.isNotBlank() && model.isNotBlank() && year > 1800 //TODO add more constrains
        }
    }

    fun updateUiState(carDetails: CarDetails) {
        carUiState =
            CarUiState(carDetails = carDetails, isEntryValid = validateInput(carDetails))
    }
//    suspend fun updateUiState(carDetails: CarDetails) {
//        if (validateInput(carUiState.carDetails)) {
//            carsRepository.updateCar(carUiState.carDetails.toItem())
//        }
//    }
    /**
     * Reduces the item quantity by one and update the [ItemsRepository]'s data source.
     */
//    fun reduceQuantityByOne() {
//        viewModelScope.launch {
//            val currentCar = uiState.value.carDetails.toItem()
//            carsRepository.updateCar(currentCar.copy())
//        }
//    }

    /**
     * Deletes the item from the [ItemsRepository]'s data source.
     */
    suspend fun deleteItem() {
        carsRepository.deleteCar(uiState.value.carDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}