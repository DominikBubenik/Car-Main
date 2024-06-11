package com.example.car_main.four_main_screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.AppViewModelProvider
import com.example.car_main.DeleteConfirmationDialog
import com.example.car_main.R
import com.example.car_main.TopFourButtons
import com.example.car_main.data.Expense
import com.example.car_main.navigation.NavigationDestination
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date

object StatsDestination : NavigationDestination {
    override val route = "stats"
    override val titleRes = R.string.stats
    const val carIdArg = "carId"
    val routeWithArgs = "$route/{$carIdArg}"
}

@Composable
fun StatsScreen(
    navigateBack: () -> Unit,
    viewModel: StatsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val carId = viewModel.carId

    BackHandler {
        navigateBack()
    }
    Scaffold(
        topBar = { TopFourButtons(navController = navController, carId) },

        ) { innerPadding ->
        StatsBody(
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            viewModel = viewModel,
            carId = carId
        )
    }
}

@Composable
fun StatsBody(
    modifier: Modifier = Modifier,
    viewModel: StatsViewModel,
    carId: Int
) {
    val expenses by viewModel.expensesUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val totalExpense = viewModel.totalExpense
    val totalExpenseFuel = viewModel.totalExpenseFuel
    val totalExpenseService = viewModel.totalExpenseService
    val totalExpenseParking = viewModel.totalExpenseParking
    val totalExpenseTolls = viewModel.totalExpenseTolls
    val totalExpenseOther = viewModel.totalExpenseOther

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Car ID: $carId ",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OtherRow(header = "Total Cost", value = totalExpense)
            OtherRow(header = "Fuel Cost", value = totalExpenseFuel)
            OtherRow(header = "Service Cost", value = totalExpenseService)
            OtherRow(header = "Parking Cost", value = totalExpenseParking)
            OtherRow(header = "Tolls Cost", value = totalExpenseTolls)
            OtherRow(header = "Other Cost", value = totalExpenseOther)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "All expenses",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 10.dp)
                        .align(Alignment.CenterVertically),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            expenses.forEach { expense ->
                ExpenseRow(expense, onDelete = {
                    coroutineScope.launch {
                        viewModel.deleteItem(expense)
                    }
                })
            }

        }
    }
}

@Composable
fun OtherRow(header: String, value: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${String.format("%.2f",value)}€",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End,
            modifier = Modifier.width(100.dp)
        )
    }
}

@Composable
fun ExpenseRow(expense: Expense, onDelete: () -> Unit) {
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    val formattedDate = dateFormat.format(Date(expense.date))
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp) // Reduce vertical padding
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 1.dp), // Adjust horizontal and vertical padding
        verticalAlignment = Alignment.CenterVertically // Align items vertically
    ) {
        Text(
            text = expense.kind,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically, // Align items vertically
            horizontalArrangement = Arrangement.spacedBy(4.dp) // Add spacing between items
        ) {
            IconButton(
                onClick = { deleteConfirmationRequired = true },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Delete Car",
                    tint = Color.Red
                )
            }

            Text(
                text = "${String.format("%.2f", expense.value)}€",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End,
                modifier = Modifier.width(100.dp)
            )
        }
    }

    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDelete()
            },
            onDeleteCancel = { deleteConfirmationRequired = false },
            modifier = Modifier.padding(),
            itemName = "expense"
        )

    }
}