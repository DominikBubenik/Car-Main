package com.example.car_main.data

import kotlinx.coroutines.flow.Flow

class OfflineCarsRepository(private val carDao: CarDao) : CarsRepository {
    override fun getAllCarsStream(): Flow<List<Car>> = carDao.getAllItems()

    override fun getCarStream(id: Int): Flow<Car?> = carDao.getItem(id)

    override suspend fun insertCar(car: Car) = carDao.insert(car)

    override suspend fun deleteCar(car: Car) = carDao.delete(car)

    override suspend fun updateCar(car: Car) = carDao.update(car)
}