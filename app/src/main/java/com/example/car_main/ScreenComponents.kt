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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.car_main.four_main_screens.GraphDestination
import com.example.car_main.four_main_screens.HomeDestination
import com.example.car_main.four_main_screens.StatsDestination
import com.example.car_main.four_main_screens.TimeLineDestination
import com.example.car_main.ui.theme.BarColour

/**
 * main top app bar in whole app
 */
@Composable
fun TopFourButtons(navController : NavHostController, carId: Int = 0) {
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

/**
 * top app bar for "adding" screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back_button"
                    )
                }
            }
        }
    )
}

/**
 * opens dialog window if user really wants to delete item
 */
@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier,
    itemName: String = ""
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Deleting $itemName") },
        text = { Text("Do you want to remove $itemName") },
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