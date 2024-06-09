package com.example.car_main

import android.app.Application
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.car_main.home.HomeViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory


object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel

        // Initializer for ItemEntryViewModel
        initializer {
            AddCarViewModel(inventoryApplication().container.carsRepository)
        }

        initializer {
            ExpensesMenuViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        initializer {
            GraphsViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        initializer {
            TimeLineViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        initializer {
            StatsViewModel(this.createSavedStateHandle(),
                inventoryApplication().container.carsRepository)
        }

        initializer {
            AddExpenseViewModel(this.createSavedStateHandle(), inventoryApplication().container.carsRepository)
        }

        // Initializer for ItemDetailsViewModel

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(inventoryApplication().container.carsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): CarMainApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CarMainApplication)