package com.example.car_main.four_main_screens


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.car_main.AppViewModelProvider
import com.example.car_main.DeleteConfirmationDialog
import com.example.car_main.R
import com.example.car_main.TopFourButtons
import com.example.car_main.data.Car
import com.example.car_main.navigation.NavigationDestination
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
    var activeCarId = viewModel.curCarId

    Scaffold (
        topBar = {
            TopFourButtons(navController = navController, activeCarId)
        },

        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = navigateToAddCar,
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


/**
 * list of interactive car cards
 */
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

/**
 * card contains description and image of car
 */
@Composable
fun CarCard(
    car: Car,
    onDelete: () -> Unit,
    onSetActiveCar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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
                            painter = rememberImagePainter(uri),
                            contentDescription = "Car Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.my_car),
                            contentDescription = "Car Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // Text content
                        Text(
                            text = "ID: ${car.id} ${car.brand} ${car.model}",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // Text content
                        Text(
                            text = "License number: ${car.licenceNum}",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        // Spacer to simulate a tab
                        Spacer(modifier = Modifier.width(20.dp))

                        // Text content for Fuel type
                        Text(
                            text = "Fuel type: ${car.fuelType}",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
                //confirmation if car should be deleted
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDelete()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = Modifier.padding(),
                        itemName = "car"
                    )
                }
            }
        }
    )
}

