package com.example.car_main.four_main_screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.car_main.AppViewModelProvider
import com.example.car_main.R
import com.example.car_main.TopFourButtons
import com.example.car_main.navigation.NavigationDestination
import java.text.DateFormat
import java.util.Date

object GraphDestination : NavigationDestination {
    override val route = "graphs"
    override val titleRes = R.string.graphs
    const val carIdArg = "carId"
    val routeWithArgs = "$route/{$carIdArg}"
}

/**
 * screen displays expenses in graphs
 */
@Composable
fun GraphsScreen(
    navigateBack: () -> Unit,
    viewModel: GraphsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
) {
    val carId = viewModel.carId
    BackHandler {
        navigateBack()
    }
    Scaffold(
        topBar = { TopFourButtons(navController = navController, carId) },

        ) { innerPadding ->
        GraphsBody(
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .fillMaxWidth(),
            viewModel = viewModel,
            carId
        )
    }
}

@Composable
fun GraphsBody(
    modifier: Modifier,
    viewModel: GraphsViewModel,
    carId: Int
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(1.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Car ID: $carId",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            ExpenseChart(modifier = Modifier.fillMaxWidth(), viewModel = viewModel)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Total Costs",
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
            TotalExpenseChart(modifier = Modifier
                .fillMaxWidth()
                , viewModel = viewModel)
        }

    }

}

@Composable
fun ExpenseChart(modifier: Modifier, viewModel: GraphsViewModel) {
    val steps = 10
    val expenses by viewModel.expensesUiState.collectAsState()
    if (expenses.isEmpty()) return
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)


    val pointsData = expenses.mapIndexed { index, expense ->
        co.yml.charts.common.model.Point(index.toFloat() * 10, expense.value.toFloat())
    }

    val xAxisData = AxisData.Builder()
        .axisStepSize(10.dp)
        .steps(expenses.size - 1)
        .labelData { i ->  expenses.getOrNull(i/10)?.date?.let {dateFormat.format(Date(it)) } ?: "" }
        .labelAndAxisLinePadding(15.dp)
        .build()



    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = expenses.maxOf { it.value } / steps
            "%.2f".format(i * yScale)
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Color.Blue,
                        lineType = LineType.Straight(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = Color.Blue
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outline)
    )
    LineChart(modifier = modifier.heightIn(max = 400.dp) , lineChartData = lineChartData)
}

@Composable
fun TotalExpenseChart(modifier: Modifier, viewModel: GraphsViewModel) {
    val steps = 10
    var sum: Double = 0.0
    val expenses by viewModel.expensesUiState.collectAsState()
    if (expenses.isEmpty()) return
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)

    val pointsData = expenses.mapIndexed { index, expense ->
        sum += expense.value
        co.yml.charts.common.model.Point(index.toFloat() * 10, sum.toFloat())
    }

    val xAxisData = AxisData.Builder()
        .axisStepSize(10.dp)
        .steps(expenses.size - 1)
        .labelData { i ->  expenses.getOrNull(i/10)?.date?.let {dateFormat.format(Date(it)) } ?: "" }
        .labelAndAxisLinePadding(15.dp)
        .build()



    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = sum / steps
            "%.2f".format(i * yScale)
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Color.Blue,
                        lineType = LineType.Straight(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = Color.Blue
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outline)
    )
    LineChart(modifier = modifier.heightIn(max = 400.dp) , lineChartData = lineChartData)
}




