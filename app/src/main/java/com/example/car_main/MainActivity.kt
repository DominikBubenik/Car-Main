/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.car_main

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.car_main.Graphs
import com.example.car_main.TimeLine
import com.example.car_main.ui.theme.BarColour
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            HomeScreen()
        }
    }
}




@Composable
fun PrimaryButton(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 285.dp)
            .requiredHeight(height = 40.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.Black)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Other Expenses",
            color = Color.White,
            lineHeight = 9.38.em,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically))
    }
}

@Composable
fun ShowImageUploadDialog() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            // Handle the returned data, such as retrieving the selected image URI
            val selectedImageUri = data?.data
            // Process the selected image
            // For example, you can display it in an ImageView or upload it to a server
        }
    }

    AlertDialog.Builder(context)
        .setTitle("Upload Image")
        .setMessage("Select an image from the gallery")
        .setPositiveButton("Gallery") { _, _ ->
            // Open the gallery when the user clicks the "Gallery" button
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            launcher.launch(galleryIntent)
        }
        .setNegativeButton("Cancel", null)
        .show()
}


/*
fun FourButtonsInVerticalLine(context: Context) {
    Row (
        modifier = Modifier.fillMaxWidth()
            .background(Color.Green)
            .padding(top = 40.dp),
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
                context.startActivity(Intent(context, Graphs::class.java))
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
                context.startActivity(Intent(context, TimeLine::class.java))
            },
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Time Line", textAlign = TextAlign.Center) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .height(64.dp), // Distribute available width equally among buttons
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(8.dp),
            content = { Text(text = "Stats", textAlign = TextAlign.Center) }
        )
    }
}
*/