package com.example.car_main.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_Cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val brand: String = "",
    val model: String = "",
    val year: Int = 0,
    val licenceNum: String = "",
    val fuelType: String = "",
    var isActive: Boolean = false,
    val imageUri: String? = ""
)

