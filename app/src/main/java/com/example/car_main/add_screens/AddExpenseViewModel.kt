package com.example.car_main.add_screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.car_main.data.CarsRepository
import com.example.car_main.data.Expense

class AddExpenseViewModel(
    savedStateHandle: SavedStateHandle,
    private val carsRepository: CarsRepository
) : ViewModel() {
    private val carId: Int = checkNotNull(savedStateHandle[AddExpensesDestination.carIdArg])
    private val kind: String = checkNotNull(savedStateHandle[AddExpensesDestination.kindArg])


    var expenseUiState by mutableStateOf(ExpenseUiState())
        private set

    fun updateUiState(expenseDetails: ExpenseDetails) {
        expenseUiState =
            ExpenseUiState(expenseDetails = expenseDetails, isEntryValid = validateInput(expenseDetails))
    }


    suspend fun saveItem() {
        if (validateInput()) {
            val updatedExpenseDetails = expenseUiState.expenseDetails.copy(carId = carId, kind = kind)
            expenseUiState = expenseUiState.copy(expenseDetails = updatedExpenseDetails)
            carsRepository.insertExpense(expenseUiState.expenseDetails.toItem())
        }
    }

    private fun validateInput(uiState: ExpenseDetails = expenseUiState.expenseDetails): Boolean {
        return with(uiState) {
             value > 0 && carId == id
        }
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


data class ExpenseUiState(
    val expenseDetails: ExpenseDetails = ExpenseDetails(),
    val isEntryValid: Boolean = false
)
