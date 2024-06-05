package com.example.car_main.data

import android.content.Context

interface AppContainer {
    val carsRepository: CarsRepository
    //val expensesRepository: ExpensesRepository
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

//    override val expensesRepository: ExpensesRepository by lazy {
//        OfflineExpensesRepository(CarDatabase.getDatabase(context).expenseDao())
//    }
}