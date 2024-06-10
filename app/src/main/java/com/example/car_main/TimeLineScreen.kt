package com.example.car_main

//import androidx.compose.foundation.lazy.LazyColumn
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.data.Expense
import com.example.car_main.home.TopFourButtons
import com.example.car_main.navigation.NavigationDestination
import java.text.DateFormat
import java.util.Date


object TimeLineDestination : NavigationDestination {
    override val route = "timeLine_screen"
    override val titleRes = R.string.time_line
    const val carIdArg = "carId"
    val routeWithArgs = "$route/{$carIdArg}"
}
@Composable
fun TimeLineScreen(
    navigateBack: () -> Unit,
    viewModel: TimeLineViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
)
{
    val carId = viewModel.carId
    BackHandler {
        navigateBack()
    }
    Scaffold (
        topBar = { TopFourButtons(navController = navController, carId) },

        ) {innerPadding ->
        TimeLineBody( modifier = Modifier
            .padding(
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                top = innerPadding.calculateTopPadding(),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
            )
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
            viewModel = viewModel,
            carId)
    }
}

@Composable
fun TimeLineBody(modifier: Modifier = Modifier,viewModel: TimeLineViewModel, carId: Int) {
    val expenses by viewModel.expensesUiState.collectAsState()

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
            Text(
                text = "Time Line---------------------------------------",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            expenses.reversed().forEach { expense ->
                TimeLineRow(expense)
            }
        }

    }
}

@Composable
fun TimeLineRow(expense: Expense) {
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    val formattedDate = dateFormat.format(Date(expense.date))
    var color = Color.Gray

    when(expense.kind) {
        "Fuel" -> color = Color.Red
        "Service" -> color = Color.Green
        "Parking" -> color = Color.Cyan
        "Tolls" -> color = Color(0xFFB4A235)
        "Other" -> color = Color.Black
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(1.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.parking_check_icon),
            contentDescription = "Item Image",
            tint = color,
            modifier = Modifier
                .size(25.dp)
        )
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
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 1.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(1.dp)
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .height(30.dp)
                .width(3.dp), // Adjust the height as needed
            color = color
        )
    }
}
