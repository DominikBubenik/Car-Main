package com.example.car_main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.NavHostController
import com.example.car_main.home.MyTopAppBar
import com.example.car_main.home.TopFourButtons
import com.example.car_main.navigation.NavigationDestination

object TimeLineDestination : NavigationDestination {
    override val route = "timeLine_screen"
    override val titleRes = R.string.time_line
    const val carIdArg = "carId"
    val routeWithArgs = "${TimeLineDestination.route}/{$carIdArg}"
}
@Composable
fun TimeLineScreen(
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
        TimeLineBody( modifier = Modifier
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
fun TimeLineBody(modifier: Modifier = Modifier) {

}