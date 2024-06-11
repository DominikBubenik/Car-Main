package com.example.car_main.four_main_screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_main.data.CarsRepository
import com.example.car_main.data.Expense
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StatsViewModel(
    savedStateHandle: SavedStateHandle,
    private val carsRepository: CarsRepository,
) : ViewModel() {

    val carId: Int
    var totalExpense: Double by mutableDoubleStateOf(0.0)
        private set
    var totalExpenseFuel: Double by mutableDoubleStateOf(0.0)
        private set
    var totalExpenseService: Double by mutableDoubleStateOf(0.0)
        private set
    var totalExpenseParking: Double by mutableDoubleStateOf(0.0)
        private set
    var totalExpenseTolls: Double by mutableDoubleStateOf(0.0)
        private set
    var totalExpenseOther: Double by mutableDoubleStateOf(0.0)
        private set

    init {
        carId = checkNotNull(savedStateHandle[StatsDestination.carIdArg])
        viewModelScope.launch {
            initializeValues()
        }
    }

    val expensesUiState: StateFlow<List<Expense>> =
        carsRepository.getAllExpensesForCarStream(carId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    suspend fun deleteItem(expense: Expense) {
        carsRepository.deleteExpense(expense)
        initializeValues()
    }

    private suspend fun initializeValues() {
        totalExpense = carsRepository.getTotalExpensesForCar(carId).first()
        totalExpenseFuel = carsRepository.getTotalExpensesKind(carId, "Fuel").first()
        totalExpenseService = carsRepository.getTotalExpensesKind(carId, "Service").first()
        totalExpenseParking = carsRepository.getTotalExpensesKind(carId, "Parking").first()
        totalExpenseTolls = carsRepository.getTotalExpensesKind(carId, "Tolls").first()
        totalExpenseOther = carsRepository.getTotalExpensesKind(carId, "Other").first()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
