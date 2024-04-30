package com.example.car_main

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.node.CanFocusChecker.end
//import androidx.compose.ui.node.CanFocusChecker.end
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
    navigateToAddCar: () -> Unit,
   navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    Scaffold (
        topBar = {
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
                        navController.navigate(TimeLineDestination.route)
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
        },

        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                onClick = { },
//                modifier = Modifier
//                    .padding(
//                        end = WindowInsets.safeDrawing.asPaddingValues()
//                            .calculateEndPadding(LocalLayoutDirection.current)
//                    ),
//                containerColor = Color.Gray,
//                icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
//                text = { Text(text = "Extended FAB") },
//            )
            LargeFloatingActionButton(
                onClick = navigateToAddCar,//navigateToItemEntry,
                modifier = Modifier
                    .padding(
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(LocalLayoutDirection.current)
                    ),
                containerColor = Color.Gray
            ) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Image (
                        painter = painterResource(id = R.drawable.car_icon),
                        contentDescription = "fab",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(text = "ADD")
                }
            }
        },
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    //scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateBack: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        //scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back_button"//stringResource(string.back_button)
                    )
                }
            }
        }
    )
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