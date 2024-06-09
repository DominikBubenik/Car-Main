package com.example.car_main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_main.data.Car
import com.example.car_main.data.CarsRepository
import com.example.car_main.toItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val carsRepository: CarsRepository) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        carsRepository.getAllCarsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun getCarId(): Int {
        val itemList = homeUiState.value.itemList
        return if (!itemList.isNullOrEmpty()) {
            itemList.last().id
        } else {
            return 0 // Return null if the list is null or empty
        }
    }

    suspend fun deleteItem(car: Car) {
        carsRepository.deleteCar(car)
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class HomeUiState(val itemList: List<Car> = listOf())
