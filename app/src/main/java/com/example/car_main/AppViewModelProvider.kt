package com.example.car_main

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.car_main.add_screens.AddCarViewModel
import com.example.car_main.add_screens.AddExpenseViewModel
import com.example.car_main.add_screens.ExpensesMenuViewModel
import com.example.car_main.four_main_screens.GraphsViewModel
import com.example.car_main.four_main_screens.HomeViewModel
import com.example.car_main.four_main_screens.StatsViewModel
import com.example.car_main.four_main_screens.TimeLineViewModel

/**
 * src for template: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#0
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for AddCarViewModel
        initializer {
            AddCarViewModel(inventoryApplication().container.carsRepository)
        }

        // Initializer for ExpenseMenuViewModel
        initializer {
            ExpensesMenuViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        // Initializer for GraphsViewModel
        initializer {
            GraphsViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        // Initializer for TimeLineViewModel
        initializer {
            TimeLineViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        // Initializer for StatsViewModel
        initializer {
            StatsViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        // Initializer for AddExpenseViewModel
        initializer {
            AddExpenseViewModel(this.createSavedStateHandle(), inventoryApplication().container.carsRepository)
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(inventoryApplication().container.carsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [CarMainApplication].
 */
fun CreationExtras.inventoryApplication(): CarMainApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CarMainApplication)