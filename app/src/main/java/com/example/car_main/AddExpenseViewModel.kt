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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AddExpenseViewModel(savedStateHandle: SavedStateHandle,
                          private val carsRepository: CarsRepository
) : ViewModel() {
    private val carId: Int = checkNotNull(savedStateHandle[AddExpensesDestination.carIdArg])
    private val kind: String = checkNotNull(savedStateHandle[AddExpensesDestination.kindArg])


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
    /**
     * Holds current item ui state
     */
    var expenseUiState by mutableStateOf(ExpenseUiState())
        private set

    var isValueFieldClicked by mutableStateOf(false)
        private set

    fun onValueFieldClicked() {
        isValueFieldClicked = true
    }

    fun clearValueFieldClicked() {
        isValueFieldClicked = false
    }

    /**
     * Updates the [carUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(expenseDetails: ExpenseDetails) {
        expenseUiState =
            ExpenseUiState(expenseDetails = expenseDetails, isEntryValid = validateInput(expenseDetails))
    }

    fun getKind(): String {
        return kind
    }




    /**
     * Inserts an [Item] in the Room database
     */
    suspend fun saveItem() {
        if (validateInput()) {
            val updatedExpenseDetails = expenseUiState.expenseDetails.copy(carId = carId, kind = kind)
            expenseUiState = expenseUiState.copy(expenseDetails = updatedExpenseDetails)
            carsRepository.insertExpense(expenseUiState.expenseDetails.toItem())
        }
    }
//    suspend fun deleteItem() {
//        carsRepository.deleteCar(carUiState.carDetails.toItem())
//    }

    private fun validateInput(uiState: ExpenseDetails = expenseUiState.expenseDetails): Boolean {
        return with(uiState) {
             value > 0 && carId == id //TODO add more constrains
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class ExpenseDetails(
    val id: Int = 0,
    val value: Double = 0.0,
    val kind: String = "",
    val date: Long = System.currentTimeMillis(),
    val carId: Int = 0
)

fun ExpenseDetails.toItem(): Expense = Expense(
    id = id,
    value = value,
    kind = kind,
    date = date,
    carId = carId
)

fun Expense.toExpenseDetails(): ExpenseDetails = ExpenseDetails(
    id = id,
    value = value,
    kind = kind,
    date = date,
    carId = carId
)

data class ExpenseUiState(
    val expenseDetails: ExpenseDetails = ExpenseDetails(),
    val isEntryValid: Boolean = false
)

fun Expense.toExpenseUiState(isEntryValid: Boolean = false): ExpenseUiState = ExpenseUiState(
    expenseDetails = this.toExpenseDetails(),
    isEntryValid = isEntryValid
)