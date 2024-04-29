package com.example.car_main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.car_main.navigation.NavigationDestination
import com.example.car_main.ui.theme.CarMainTheme

object GraphDestination : NavigationDestination {
    override val route = "graphs"
    override val titleRes = R.string.graphs
}

@Composable
fun GraphsScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarMainTheme {
        GraphsScreen("Android")
    }
}