package com.example.car_main

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.car_main.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
//        initializer {
//            ItemEditViewModel(
//                this.createSavedStateHandle(),
//                inventoryApplication().container.itemsRepository
//            )
//        }
        // Initializer for ItemEntryViewModel
//        initializer {
//            AddCarViewModel(inventoryApplication().container.itemsRepository)
//        }

        // Initializer for ItemDetailsViewModel
//        initializer {
//            ItemDetailsViewModel(
//                this.createSavedStateHandle(),
//                inventoryApplication().container.itemsRepository
//            )
//        }

        // Initializer for HomeViewModel
//        initializer {
//            HomeViewModel(inventoryApplication().container.itemsRepository)
//        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
//fun CreationExtras.CarMainApplication(): CarMainApplication = CarMainApplication
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CarMainApplication)
