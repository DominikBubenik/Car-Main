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
import com.example.car_main.Graphs
import com.example.car_main.TimeLine
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            //PrimaryButton()
            FourButtonsInVerticalLine()
            // Section1()
//            TipTimeTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                ) {
//                    TipTimeLayout()
//                }
//            }
        }
    }
}


@Composable
fun FourButtonsInVerticalLine() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight(0.15f) // Make the Column occupy 75% of the height
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom // Align buttons vertically at the bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), // Make the Row fill the available width
            horizontalArrangement = Arrangement.SpaceBetween // Align buttons horizontally with space between them
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp), // Distribute available width equally among buttons
                colors = ButtonDefaults.buttonColors( Color.Gray), // Set button background color to gray
                shape = RoundedCornerShape(4.dp), // Set button shape to a rounded rectangle
                content = { Text(text = "Menu") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    context.startActivity(Intent(context, Graphs::class.java))
                },
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp), // Distribute available width equally among buttons
                colors = ButtonDefaults.buttonColors(Color.Gray), // Set button background color to gray
                shape = RoundedCornerShape(4.dp), // Set button shape to a rounded rectangle
                content = { Text(text = "Graphs",textAlign = TextAlign.Center, maxLines = 1,overflow = TextOverflow.Ellipsis) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    context.startActivity(Intent(context, TimeLine::class.java))
                },
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp), // Distribute available width equally among buttons
                colors = ButtonDefaults.buttonColors(  Color.Gray), // Set button background color to gray
                shape = RoundedCornerShape(4.dp), // Set button shape to a rounded rectangle
                content = { Text(text = "Time Line",textAlign = TextAlign.Center) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp), // Distribute available width equally among buttons
                colors = ButtonDefaults.buttonColors(Color.Gray), // Set button background color to gray
                shape = RoundedCornerShape(4.dp), // Set button shape to a rounded rectangle
                content = { Text(text = "Stats",textAlign = TextAlign.Center) }
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd // Align content at the bottom center
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.End, // Align children horizontally to the end (right)
            verticalAlignment = Alignment.Bottom // Align children vertically to the bottom
        ) {
            Button(
                onClick = {
                    //ShowImageUploadDialog()
                },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(Color.Gray),
            ) {
                Text(text = "Button")
            }
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

@Preview(widthDp = 285, heightDp = 40)
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(Modifier)
}
/*
@Composable
fun Section1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 45.dp
                )
                .fillMaxSize()
                .background(color = Color(0xfffffcfc)))

        Text(
            text = "Vozidlo 1\n",
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 4.69.em,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.25f),
                    offset = Offset(0f, 4f),
                    blurRadius = 4f
                )
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 91.dp,
                    y = 281.dp
                ))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 45.dp
                )
                .requiredWidth(width = 319.dp)
                .requiredHeight(height = 73.dp)
                .background(color = Color(0xffaae134)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 45.dp
                )
                .fillMaxWidth()
                .requiredHeight(height = 73.dp)
                .background(color = Color(0xffaae134)))
        Box(
            modifier = Modifier
                .requiredWidth(width = 319.dp)
                .requiredHeight(height = 45.dp)
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 237.66665649414062.dp,
                        y = 17.3306884765625.dp
                    )
                    .requiredWidth(width = 67.dp)
                    .requiredHeight(height = 11.dp)
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.battery),
//                    contentDescription = "Battery",
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(x = 42.333343505859375.dp,
//                            y = 0.002685546875.dp)
//                        .requiredWidth(width = 24.dp)
//                        .requiredHeight(height = 11.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.wifi),
//                    contentDescription = "Wifi",
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(x = 22.027069091796875.dp,
//                            y = 0.dp)
//                        .requiredWidth(width = 15.dp)
//                        .requiredHeight(height = 11.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.mobilesignal),
//                    contentDescription = "Mobile Signal",
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(x = 0.dp,
//                            y = 0.3359375.dp)
//                        .requiredWidth(width = 17.dp)
//                        .requiredHeight(height = 11.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 21.000030517578125.dp,
                        y = 12.dp
                    )
                    .requiredWidth(width = 54.dp)
                    .requiredHeight(height = 21.dp)
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.time),
//                    contentDescription = "Time",
//                    colorFilter = ColorFilter.tint(Color.Black),
//                    modifier = Modifier
//                        .requiredWidth(width = 54.dp)
//                        .requiredHeight(height = 21.dp)
//                        .clip(shape = RoundedCornerShape(32.dp)))
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 12.dp,
                    y = 54.dp
                )
                .requiredWidth(width = 54.dp)
                .requiredHeight(height = 48.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color(0xffc3c2c2))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Menu",
                color = Color.White,
                lineHeight = 9.38.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 170.dp,
                    y = 54.dp
                )
                .requiredWidth(width = 54.dp)
                .requiredHeight(height = 48.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color(0xffc3c2c2))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Time \n Line",
                color = Color.White,
                lineHeight = 9.38.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 91.dp,
                    y = 54.dp
                )
                .requiredWidth(width = 54.dp)
                .requiredHeight(height = 48.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color(0xffc3c2c2))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Graphs",
                color = Color.White,
                lineHeight = 9.38.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 248.dp,
                    y = 54.dp
                )
                .requiredWidth(width = 54.dp)
                .requiredHeight(height = 48.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color(0xffc3c2c2))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Stats",
                color = Color.White,
                lineHeight = 9.38.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 111.dp
                )
                .requiredWidth(width = 319.dp)
                .requiredHeight(height = 178.dp)
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = (-2007).dp,
                        y = (-867).dp
                    )
                    .requiredWidth(width = 4096.dp)
                    .requiredHeight(height = 1846.dp))
            Box(
                modifier = Modifier
                    .requiredWidth(width = 1037.dp)
                    .requiredHeight(height = 400.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.money),
                    contentDescription = "autoi 1",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (-6).dp,
                            y = 10.dp
                        )
                        .requiredWidth(width = 325.dp)
                        .requiredHeight(height = 155.dp))
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 248.dp,
                    y = 478.dp
                )
                .requiredHeight(height = 40.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color.Black)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "+",
                color = Color.White,
                lineHeight = 4.69.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
    }
}

@Preview(widthDp = 319, heightDp = 546)
@Composable
private fun Section1Preview() {
    Section1(Modifier)
}*/



/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */




