package com.example.car_main

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
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StatsViewModel(
    savedStateHandle: SavedStateHandle,
    private val carsRepository: CarsRepository,
) : ViewModel() {

    //private val carId: Int = checkNotNull(savedStateHandle[StatsDestination.carIdArg])
//    var carId: Int by mutableStateOf(0)
//        private set
    val carId: Int


    var totalExpense: Double by mutableStateOf(0.0)
        private set
    var totalExpenseFuel: Double by mutableStateOf(0.0)
        private set
    var totalExpenseService: Double by mutableStateOf(0.0)
        private set
    var totalExpenseParking: Double by mutableStateOf(0.0)
        private set
    var totalExpenseTolls: Double by mutableStateOf(0.0)
        private set
    var totalExpenseOther: Double by mutableStateOf(0.0)
        private set

    init {
        carId = checkNotNull(savedStateHandle[StatsDestination.carIdArg])
        viewModelScope.launch {
            totalExpense = carsRepository.getTotalExpensesForCar(carId).first()
            totalExpenseFuel = carsRepository.getTotalExpensesKind(carId, "Fuel").first()
            totalExpenseService = carsRepository.getTotalExpensesKind(carId, "Service").first()
            totalExpenseParking = carsRepository.getTotalExpensesKind(carId, "Parking").first()
            totalExpenseTolls = carsRepository.getTotalExpensesKind(carId, "Tolls").first()
            totalExpenseOther = carsRepository.getTotalExpensesKind(carId, "Other").first()
            val ee = 0;
        }
    }
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

    val expensesUiState: StateFlow<List<Expense>> =
        carsRepository.getAllExpensesForCarStream(carId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    var carUiState by mutableStateOf(CarUiState())
        private set

//    fun getCarId(): Int {
//        return carId
//    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
//data class HomeUiState(val itemList: List<Item> = listOf())