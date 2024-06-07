package com.example.car_main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
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
    navController: NavHostController
)
{
    BackHandler {
        navigateBack()
    }
    Scaffold (
        topBar = { TopFourButtons(navController = navController) },

        ) {innerPadding ->
        GraphsBody( modifier = Modifier
            .padding(
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                top = innerPadding.calculateTopPadding(),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
            )
            .verticalScroll(rememberScrollState())
            .fillMaxWidth())
    }
}

@Composable
fun GraphsBody(modifier: Modifier = Modifier) {

}


