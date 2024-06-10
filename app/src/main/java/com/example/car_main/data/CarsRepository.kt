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

    suspend fun getActiveCar(): Car?

    suspend fun setActiveCar(carId: Int, value: Boolean)

    /**
     * Retrieve all the expenses from the given data source for a specific car.
     */
    fun getAllExpensesForCarStream(carId: Int): Flow<List<Expense>>

    /**
     * Retrieve an expense from the given data source that matches with the [id].
     */
    fun getExpenseStream(id: Int): Flow<Expense?>

    fun getExpenseDate(id: Int): Flow<Long?>

    /**
     * Insert an expense into the data source.
     */
    suspend fun insertExpense(expense: Expense)

    /**
     * Delete an expense from the data source.
     */
    suspend fun deleteExpense(expense: Expense)

    /**
     * Update an expense in the data source.
     */
    suspend fun updateExpense(expense: Expense)

    suspend fun getTotalExpensesForCar(carId: Int): Flow<Double>

    suspend fun getTotalExpensesKind(carId: Int, kind: String): Flow<Double>
}