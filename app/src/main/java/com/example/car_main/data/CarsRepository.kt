package com.example.car_main.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface CarsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllCarsStream(): Flow<List<Car>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getCarStream(id: Int): Flow<Car?>

    /**
     * Insert item in the data source
     */
    suspend fun insertCar(car: Car)

    /**
     * Delete item from the data source
     */
    suspend fun deleteCar(car: Car)

    /**
     * Update item in the data source
     */
    suspend fun updateCar(car: Car)
}