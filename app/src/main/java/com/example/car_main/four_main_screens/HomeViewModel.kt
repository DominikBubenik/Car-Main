package com.example.car_main.four_main_screens

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.car_main.data.Car
import com.example.car_main.data.CarsRepository
import com.example.car_main.add_screens.deleteFile
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val carsRepository: CarsRepository) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        carsRepository.getAllCarsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    var curCarId: Int by mutableIntStateOf(0)
        private set

    init {
        viewModelScope.launch {
            curCarId = carsRepository.getActiveCar()?.id ?: 0
        }
    }

    suspend fun setActiveCar(carId: Int, value: Boolean) {
        carsRepository.setActiveCar(carId, value)
    }

    fun toggleActiveCar(carId: Int) {
        viewModelScope.launch {
            val currentActiveId = curCarId
            curCarId = carId
            if (currentActiveId != carId) {
                setActiveCar(currentActiveId, value = false)
                setActiveCar(carId, value = true)
            }
        }
    }

    suspend fun deleteItem(context: Context ,car: Car) {
        deleteFile(context = context, car.imageUri.toString())
        carsRepository.deleteCar(car)
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class HomeUiState(val itemList: List<Car> = listOf())
