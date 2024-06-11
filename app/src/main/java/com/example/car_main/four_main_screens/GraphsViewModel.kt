package com.example.car_main.four_main_screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_main.data.CarsRepository
import com.example.car_main.data.Expense
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class GraphsViewModel (
    savedStateHandle: SavedStateHandle,
    private val carsRepository: CarsRepository,
) : ViewModel() {

    var carId: Int by mutableStateOf(0)
        private set
    init {
        carId = checkNotNull(savedStateHandle[GraphDestination.carIdArg])
    }

    val expensesUiState: StateFlow<List<Expense>> =
        carsRepository.getAllExpensesForCarStream(carId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}