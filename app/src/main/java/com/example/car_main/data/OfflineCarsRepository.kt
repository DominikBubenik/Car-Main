package com.example.car_main.data

import kotlinx.coroutines.flow.Flow

class OfflineCarsRepository(private val carDao: CarDao) : CarsRepository {
    override fun getAllCarsStream(): Flow<List<Car>> = carDao.getAllItems()

    override fun getCarStream(id: Int): Flow<Car?> = carDao.getItem(id)

    override suspend fun insertCar(car: Car) = carDao.insert(car)

    override suspend fun deleteCar(car: Car) = carDao.delete(car)

    override suspend fun updateCar(car: Car) = carDao.update(car)

    override fun getAllExpensesForCarStream(carId: Int): Flow<List<Expense>> = carDao.getExpensesForCar(carId)

    override fun getExpenseStream(id: Int): Flow<Expense?> = carDao.getExpense(id)

    override fun getExpenseDate(id: Int): Flow<Long?> = carDao.getExpenseDate(id)

    override suspend fun insertExpense(expense: Expense) = carDao.insert(expense)

    override suspend fun getActiveCar(): Car? = carDao.getActiveCar()

    override suspend fun setActiveCar(carId: Int, value: Boolean) = carDao.updateActiveCar(carId, value)

    override suspend fun deleteExpense(expense: Expense) = carDao.delete(expense)

    override suspend fun updateExpense(expense: Expense) = carDao.update(expense)

    override suspend fun getTotalExpensesForCar(carId: Int): Flow<Double> = carDao.getTotalExpensesForCar(carId)
    override suspend fun getTotalExpensesKind(carId: Int, kind: String): Flow<Double>
        = carDao.getTotalExpensesKind(carId,kind)
}