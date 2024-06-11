package com.example.car_main.add_screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_main.data.CarsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExpensesMenuViewModel (
    savedStateHandle: SavedStateHandle,
    carsRepository: CarsRepository,
) : ViewModel() {
    private val carId: Int = checkNotNull(savedStateHandle[ExpensesMenuDestination.carIdArg])

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

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ExpensesUiState(
    val carDetails: CarDetails = CarDetails()
)

