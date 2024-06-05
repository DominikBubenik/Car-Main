package com.example.car_main.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "car_expenses", foreignKeys = [
    ForeignKey(
        entity = Car::class,
        parentColumns = ["id"],
        childColumns = ["car_id"],
        onDelete = ForeignKey.CASCADE // Define the behavior on deletion
    )
])
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val value:Double = 0.0,
    val kind:String = "",
    val date: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "car_id")
    val carId: Int
)