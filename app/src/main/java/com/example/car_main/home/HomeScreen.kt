package com.example.car_main.home


import android.net.Uri
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.car_main.AppViewModelProvider
import com.example.car_main.GraphDestination
import com.example.car_main.R
import com.example.car_main.StatsDestination
import com.example.car_main.TimeLineDestination
import com.example.car_main.data.Car
import com.example.car_main.navigation.NavigationDestination
import com.example.car_main.ui.theme.BarColour
import kotlinx.coroutines.launch

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(
    navigateToAddCar: () -> Unit,
    navigateToExpenesMenu: (Int) -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
//    var activeCarId by rememberSaveable {
//        mutableStateOf(homeUiState.itemList.firstOrNull()?.id ?: 0)
//    }
//    var activeCarId by rememberSaveable {
//        mutableStateOf(viewModel.curCarId)
//    // mutableStateOf(homeUiState.itemList.firstOrNull()?.id ?: 0)
//    }
    //var activeCarId = viewModel.curCarId
//    var activeCarId by rememberSaveable {
//        mutableStateOf(viewModel.curCarId)
//    }
    var activeCarId = viewModel.curCarId
    //var activeCarId by remember { mutableStateOf(viewModel.getActive()) }


    val carId = remember(homeUiState.itemList) {
        if (homeUiState.itemList.isNotEmpty()) {
            homeUiState.itemList.last().id
        } else {
            0 // Or some default value indicating no car is present
        }
    }
    Scaffold (
        topBar = {
            TopFourButtons(navController = navController, activeCarId)
        },

        floatingActionButton = {
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

            CarCardList(
                itemList = homeUiState.itemList,
                onCarClick = navigateToExpenesMenu,
                onSetActiveCar = { carId ->
                    activeCarId = carId
                    viewModel.toggleActiveCar(carId = carId)
                                 },
                contentPadding = innerPadding)
        }
    }
}

@Composable
fun TopFourButtons( navController : NavHostController, carId: Int = 0) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(BarColour)
            .padding(top = 40.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = {
                navController.navigate(HomeDestination.route)
                      },
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Menu") }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                navController.navigate("${GraphDestination.route}/${carId}")
            },
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = {
                Text(
                    text = "Graphs",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                navController.navigate("${TimeLineDestination.route}/${carId}")
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
                navController.navigate("${StatsDestination.route}/${carId}")
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
private fun CarCardList(
    itemList: List<Car>,
    onCarClick: (Int) -> Unit,
    onSetActiveCar: (Int) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = itemList, key = { it.id }) { item ->
            CarCard(car = item,
                onDelete = {
                    coroutineScope.launch {
                        viewModel.deleteItem(context = context, car = item)
                    }
                },
                onSetActiveCar = onSetActiveCar,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onCarClick(item.id) })
        }
    }
}

@Composable
fun CarCard(
    car: Car,
    onDelete: () -> Unit,
    onSetActiveCar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
//            .fillMaxWidth()
//            .padding(16.dp),

        //set shape of the card
        shape = RoundedCornerShape(16.dp),
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (car.imageUri?.isEmpty() != true) {
                        val imagePath = car.imageUri
                        val uri = Uri.parse(imagePath)
                        Image(
                            painter = rememberImagePainter(uri), // Specify the drawable resource for the image
                            contentDescription = "Car Image", // Provide content description for accessibility
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp), // Adjust height as needed
                            contentScale = ContentScale.FillWidth // Scale the image to fill the width of the parent
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.my_car), // Specify the drawable resource for the image
                            contentDescription = "Car Image", // Provide content description for accessibility
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp), // Adjust height as needed
                            contentScale = ContentScale.FillWidth // Scale the image to fill the width of the parent
                        )
                    }



                    Spacer(modifier = Modifier.height(8.dp)) // Add spacing between image and text
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // Text content
                        Text(
                            text = "${car.id} ${car.brand} ${car.model} ${car.licenceNum}",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        // Favorite button
                        IconButton(
                            onClick = { onSetActiveCar(car.id)},
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorite Car"
                            )
                        }

                        // Delete button
                        IconButton(
                            onClick = { deleteConfirmationRequired = true },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Car"
                            )
                        }
                        
                        if (car.isActive) {
                            Text(text = "Active")
                        }
                    }
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDelete()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = Modifier.padding()
                    )
                }
            }
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
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Deleting") },
        text = { Text("Do you want to remove car") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        })
}
