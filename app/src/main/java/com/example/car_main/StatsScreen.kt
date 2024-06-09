package com.example.car_main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.car_main.data.Expense
import com.example.car_main.home.TopFourButtons
import com.example.car_main.navigation.NavigationDestination
import com.example.car_main.ui.theme.BarColour
import com.example.car_main.ui.theme.CarMainTheme
import java.text.DateFormat
import java.util.Date

object StatsDestination : NavigationDestination {
    override val route = "stats"
    override val titleRes = R.string.stats
    const val carIdArg = "carId"
    val routeWithArgs = "${StatsDestination.route}/{$carIdArg}"
}

@Composable
fun StatsScreen(
    navigateBack: () -> Unit,
    viewModel: StatsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val carId = viewModel.getCarId()


    BackHandler {
        navigateBack()
    }
    Scaffold(
        topBar = { TopFourButtons(navController = navController, viewModel.getCarId()) },

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
    //var totalExpense by remember { mutableStateOf(0.0) }
    val totalExpense = viewModel.totalExpense
    val totalExpenseFuel = viewModel.totalExpenseFuel
    val totalExpenseService = viewModel.totalExpenseService
    val totalExpenseParking = viewModel.totalExpenseParking
    val totalExpenseTolls = viewModel.totalExpenseTolls
    val totalExpenseOther = viewModel.totalExpenseOther

//    LaunchedEffect(expenses) {
//        //totalExpense = expenses.sumByDouble { it.value }
//        //totalExpenseFuel = expenses.filter { it.kind == "Fuel" }.sumByDouble { it.value }
//    }
    Box(
        modifier = modifier
            .fillMaxSize()  // Ensure the Box takes up the entire screen
            .padding(16.dp),
        contentAlignment = Alignment.Center  // Center the content within the Box
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.verticalScroll(rememberScrollState())
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
            Text(
                text = "All expenses---------------------------------------",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            expenses.forEach { expense ->
                ExpenseRow(expense)
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
            text = value.toString(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End,
            modifier = Modifier.width(100.dp)
        )
    }
}

@Composable
fun ExpenseRow(expense: Expense) {
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    val formattedDate = dateFormat.format(Date(expense.date))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
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
        Text(
            text = expense.value.toString(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End,
            modifier = Modifier.width(100.dp)
        )
    }
}