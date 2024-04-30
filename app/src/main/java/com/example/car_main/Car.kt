package com.example.car_main

data class Car(
    val brand: String = "",
    val model: String = "",
    val year: Int = 0,
    val licenceNum: String = "",
    val fuelType: String = "",
    var isActive: Boolean = false
)

data class CarUiState(
    val carDetails: Car = Car(),
    val isEntryValid: Boolean = false
)
