package com.example.car_main.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var Instance: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {

            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CarDatabase::class.java, "car_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}