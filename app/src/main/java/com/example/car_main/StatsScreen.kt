package com.example.car_main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.car_main.home.TopFourButtons
import com.example.car_main.navigation.NavigationDestination
import com.example.car_main.ui.theme.BarColour
import com.example.car_main.ui.theme.CarMainTheme
object StatsDestination : NavigationDestination {
    override val route = "stats"
    override val titleRes = R.string.stats
    const val carIdArg = "carId"
    val routeWithArgs = "${StatsDestination.route}/{$carIdArg}"
}

@Composable
fun StatsScreen(
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
            StatsBody( modifier = Modifier
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
    fun StatsBody(modifier: Modifier = Modifier) {

    }