package com.example.car_main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.car_main.navigation.Navigation

@Composable
fun CarMainApp(navController: NavHostController = rememberNavController()) {
    Navigation(navController = rememberNavController() )
}