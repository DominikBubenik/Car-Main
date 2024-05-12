package com.example.car_main.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.node.CanFocusChecker.end
//import androidx.compose.ui.node.CanFocusChecker.end
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.car_main.AppViewModelProvider
import com.example.car_main.R
import com.example.car_main.StatsDestination
import com.example.car_main.TimeLineDestination
import com.example.car_main.data.Car
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
   navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    val homeUiState by viewModel.homeUiState.collectAsState()
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
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()

        ) {
            CarCardList(itemList = homeUiState.itemList, onItemClick = { }, contentPadding = innerPadding)
        }
    }
}

@Composable
private fun CarCardList(
    itemList: List<Car>,
    onItemClick: (Car) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = itemList, key = { it.id }) { item ->
            CarCard(car = item,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onItemClick(item) })
        }
    }
}

@Composable
fun CarCard(car: Car,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
//            .fillMaxWidth()
//            .padding(16.dp),

        //set shape of the card
        shape = RoundedCornerShape(16.dp),
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.my_car), // Specify the drawable resource for the image
                    contentDescription = "Car Image", // Provide content description for accessibility
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // Adjust height as needed
                    contentScale = ContentScale.FillWidth // Scale the image to fill the width of the parent
                )

                Spacer(modifier = Modifier.height(16.dp)) // Add spacing between image and text

                // Text content

            }
            Text(car.id.toString() + car.brand + car.model + car.licenceNum, modifier = Modifier.padding(16.dp),style = MaterialTheme.typography.labelLarge)
        }
    )
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