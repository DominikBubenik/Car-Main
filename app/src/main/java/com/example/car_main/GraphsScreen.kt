package com.example.car_main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.car_main.home.MyTopAppBar
import com.example.car_main.home.TopFourButtons
import com.example.car_main.navigation.NavigationDestination
import com.example.car_main.ui.theme.CarMainTheme

object GraphDestination : NavigationDestination {
    override val route = "graphs"
    override val titleRes = R.string.graphs
    const val carIdArg = "carId"
    val routeWithArgs = "${GraphDestination.route}/{$carIdArg}"
}
@Composable
fun GraphsScreen(
    navigateBack: () -> Unit,
    viewModel: GraphsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController
)
{
    val carId = viewModel.getCarId()
    BackHandler {
        navigateBack()
    }
    Scaffold (
        topBar = { TopFourButtons(navController = navController, carId) },

        ) {innerPadding ->
        Text(
            text = "Car ID:", // $carId
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        GraphsBody( modifier = Modifier
            .padding(
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                top = innerPadding.calculateTopPadding(),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
            )
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(), carId)
    }
}

@Composable
fun GraphsBody(modifier: Modifier = Modifier, carId: Int) {
    Box(
        modifier = modifier
            .fillMaxSize()  // Ensure the Box takes up the entire screen
            .padding(16.dp),
        contentAlignment = Alignment.Center  // Center the content within the Box
    ) {
        Text(
            text = "Car ID: $carId",
            style = MaterialTheme.typography.bodyLarge,
        )
    }

}


