package com.example.car_main.data

import android.content.Context

interface AppContainer {
    val carsRepository: CarsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val carsRepository: CarsRepository by lazy {
        OfflineCarsRepository(CarDatabase.getDatabase(context).carDao())
    }
}