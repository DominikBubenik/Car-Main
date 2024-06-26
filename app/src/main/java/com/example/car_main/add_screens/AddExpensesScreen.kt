package com.example.car_main.add_screens

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.AppViewModelProvider
import com.example.car_main.MyTopAppBar
import com.example.car_main.R
import com.example.car_main.navigation.NavigationDestination
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


object AddExpensesDestination : NavigationDestination {
    override val route = "car_add_expenses"
    override val titleRes = R.string.car_add_epenses
    const val carIdArg = "carId"
    const val kindArg = "kind"
    val routeWithArgs = "$route/{$carIdArg}/{$kindArg}"
}

@Composable
fun AddExpensesScreen(
    navigateBack: () -> Unit,
    viewModel: AddExpenseViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Add Expense",
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        },

        ) { innerPadding ->
        AddExpenseBody(
            expenseUiState = viewModel.expenseUiState,
            onExpenseValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )

    }

}

@Composable
fun AddExpenseBody(
    expenseUiState: ExpenseUiState,
    onExpenseValueChange: (ExpenseDetails) -> Unit = {},
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val expenseDetails = expenseUiState.expenseDetails

    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        OutlinedTextField(
            value = expenseDetails.value.toString(),
            onValueChange = {
                onExpenseValueChange(expenseDetails.copy(value = it.toDouble()))
            },
            label = { Text(text = "Value") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Green,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        DateInputField(
            label = "Date",
            dateInMillis = expenseDetails.date,
            onDateChange = { newDate ->
                onExpenseValueChange(expenseDetails.copy(date = newDate))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        )

        Button(
            onClick = onSaveClick,
            enabled = expenseUiState.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Green, contentColor = Color.White)
        ) {
            Text(text = "Add Expense")
        }
    }
}

@Composable
fun DateInputField(
    label: String,
    dateInMillis: Long,
    onDateChange: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = dateInMillis

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = remember { mutableStateOf(dateFormat.format(Date(dateInMillis))) }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.set(selectedYear, selectedMonth, selectedDay)
            val selectedDateInMillis = selectedCalendar.timeInMillis
            formattedDate.value = dateFormat.format(Date(selectedDateInMillis))
            onDateChange(selectedDateInMillis)
        },
        year,
        month,
        day
    )

    OutlinedTextField(
        value = formattedDate.value,
        onValueChange = { /* Do nothing */ },
        label = { Text(text = label) },
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.Green,
        ),
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(Icons.Default.DateRange, contentDescription = "Select Date")
            }
        }
    )
}




