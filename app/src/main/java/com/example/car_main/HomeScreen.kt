package com.example.car_main

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.car_main.navigation.NavigationDestination
import com.example.car_main.ui.theme.BarColour

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

//enum class CarMainScreens(@StringRes val title: Int) {
//    Home(title = R.string.app_name),
//    StatsScreen(title = R.string.stats)
//}

@Composable
fun HomeScreen(
   navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
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
                //context.startActivity(Intent(context, Graphs::class.java))
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
                      navController.navigate(GraphDestination.route)
                //context.startActivity(Intent(context, TimeLine::class.java))
            },
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Time Line", textAlign = TextAlign.Center) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                navController.navigate(StatsDestination.route)
            },
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Stats", textAlign = TextAlign.Center) }
        )
    }



}


@Composable
fun FourButtonsInVerticalLine(
    context: Context
) {
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    // Get the name of the current screen
//    val currentScreen = CarMainScreens.valueOf(
//        backStackEntry?.destination?.route ?: CarMainScreens.Home.name
//    )
//
//    NavHost(navController = NavHostController(context),
//        startDestination = HomeDestination.route,
//        modifier = Modifier
//    ) {
//        composable(route = HomeDestination.route) {
//            HomeScreen(navController = navController)
//        }
//        composable( route = StatsDestination.route) {
//            StatsScreen(navController = navController)
//        }
//    }

}
//@Preview(widthDp = 285, heightDp = 40)
//@Composable
//private fun HomeScreenPreview() {
//    HomeScreen(navigateToItemEntry: () -> Unit,
//    navigateToItemUpdate: (Int) -> Unit)
//    //FourButtonsInVerticalLine()
//    //PrimaryButton(Modifier)
//}