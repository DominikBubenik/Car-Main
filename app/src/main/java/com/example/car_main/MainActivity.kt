package com.example.car_main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.car_main.ui.theme.CarMainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarMainTheme {
                // A surface container using the 'background' color from the theme
                Section1()
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
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Other Expenses",
            color = Color.White,
            lineHeight = 9.38.em,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically))
    }
}

@Preview(widthDp = 285, heightDp = 40)
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(Modifier)
}

@Composable
fun Section1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 319.dp)
            .requiredHeight(height = 546.dp)
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 45.dp
                )
                .requiredWidth(width = 319.dp)
                .requiredHeight(height = 501.dp)
                .background(color = Color(0xfffffcfc)))
        Text(
            text = "Vozidlo 1\n",
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 4.69.em,
            style = TextStyle(
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
                .requiredWidth(width = 319.dp)
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
                style = TextStyle(
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
                style = TextStyle(
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
                style = TextStyle(
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
                style = TextStyle(
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
//                Image(
//                    painter = painterResource(id = R.drawable.money),
//                    contentDescription = "autoi 1",
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(
//                            x = (-6).dp,
//                            y = 10.dp
//                        )
//                        .requiredWidth(width = 325.dp)
//                        .requiredHeight(height = 155.dp))
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
                style = TextStyle(
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
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarMainTheme {
        Greeting("Android")
    }
}