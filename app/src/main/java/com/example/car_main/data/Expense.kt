package com.example.car_main.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val value:Int = 0
    //, val imageUri: String? = null
)