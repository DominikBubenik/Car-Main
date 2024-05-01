package com.example.car_main

import android.app.Application
import com.example.car_main.data.AppContainer
import com.example.car_main.data.AppDataContainer

class CarMainApplication : Application() {
    companion object {
        private lateinit var instance: CarMainApplication

        fun getInstance(): CarMainApplication {
            return instance
        }
    }

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}