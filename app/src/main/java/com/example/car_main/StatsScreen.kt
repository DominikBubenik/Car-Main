package com.example.car_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.car_main.navigation.NavigationDestination
import com.example.car_main.ui.theme.BarColour
import com.example.car_main.ui.theme.CarMainTheme
object StatsDestination : NavigationDestination {
    override val route = "stats"
    override val titleRes = R.string.stats
}

@Composable
fun StatsScreen(navController: NavHostController) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(BarColour)
            .padding(top = 40.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(
            onClick = {},
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Menu") }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {

            },
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Graphs", textAlign = TextAlign.Center, maxLines = 1, overflow = TextOverflow.Ellipsis) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
            },
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Time Line", textAlign = TextAlign.Center) }
        )
        Spacer(modifier = Modifier.width(8.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    CarMainTheme {
        //StatsScreen(navController: NavController)
    }
}