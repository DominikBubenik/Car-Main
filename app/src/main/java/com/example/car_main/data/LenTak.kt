package com.example.car_main.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "len_tak")
data class LenTak(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val value:Double = 0.0,
    val kind:String = "",
    val date: Long = System.currentTimeMillis(),
)
