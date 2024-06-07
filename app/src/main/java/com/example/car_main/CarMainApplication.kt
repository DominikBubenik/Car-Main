package com.example.car_main

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.car_main.data.AppContainer
import com.example.car_main.data.AppDataContainer
import com.example.car_main.data.CarDatabase
import com.example.car_main.data.CarDatabase.Companion.migration1to2

class CarMainApplication : Application() {
    companion object {
        private lateinit var instance: CarMainApplication

        fun getInstance(): CarMainApplication {
            return instance
        }
    }

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        // Build the Room database with the migration
//        Room.databaseBuilder(applicationContext, CarDatabase::class.java, "car_database")
//            .fallbackToDestructiveMigration()
//            .addMigrations(migration1to2)
//            .build()

        container = AppDataContainer(this)
    }
}