package com.example.car_main.data

import android.content.Context

interface AppContainer {
    val carsRepository: CarsRepository
}

/**
 * src: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#0
 * [AppContainer] implementation that provides instance of [OfflineCarsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [CarsRepository]
     */
    override val carsRepository: CarsRepository by lazy {
        OfflineCarsRepository(CarDatabase.getDatabase(context).carDao())
    }

}