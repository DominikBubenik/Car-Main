package com.example.car_main

data class Car(
    val brand: String,
    val model: String,
    val year: Int,
    val licenceNum: String,
    val fuelType: String,
    var isActive: Boolean
)
