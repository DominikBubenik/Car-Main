package com.example.car_main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_main.data.CarsRepository
import com.example.car_main.data.Expense
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TimeLineViewModel (
    savedStateHandle: SavedStateHandle,
    carsRepository: CarsRepository,
) : ViewModel() {

    val carId: Int
    init {
        carId = checkNotNull(savedStateHandle[StatsDestination.carIdArg])
    }

    val expensesUiState: StateFlow<List<Expense>> =
        carsRepository.getAllExpensesForCarStream(carId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )
    /**
     * Holds the item details ui state. The data is retrieved from  and mapped to
     * the UI state.
     */
//    val uiState: StateFlow<ExpensesUiState> =
//        carsRepository.getCarStream(carId)
//            .filterNotNull()
//            .map {
//                ExpensesUiState(carDetails = it.toCarDetails())
//            }.stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = ExpensesUiState()
//            )
//    var carUiState by mutableStateOf(CarUiState())
//        private set

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}