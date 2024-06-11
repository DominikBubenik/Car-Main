package com.example.car_main.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Car] and [Expense] from a given data source.
 * src for template: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#7
 */
interface CarsRepository {
    /**
     * Retrieve all the cars from the the given data source.
     */
    fun getAllCarsStream(): Flow<List<Car>>

    /**
     * Retrieve a car from the given data source that matches with the [id].
     */
    fun getCarStream(id: Int): Flow<Car?>

    /**
     * Insert car in the data source
     */
    suspend fun insertCar(car: Car)

    /**
     * Delete car from the data source
     */
    suspend fun deleteCar(car: Car)

    /**
     * Update car in the data source
     */
    suspend fun updateCar(car: Car)

    /**
     * Retrieve car that is active
     */
    suspend fun getActiveCar(): Car?

    /**
     * Update activity of car
     */
    suspend fun setActiveCar(carId: Int, value: Boolean)

    /**
     * Retrieve all the expenses from the given data source for a specific car.
     */
    fun getAllExpensesForCarStream(carId: Int): Flow<List<Expense>>

    /**
     * Retrieve an expense from the given data source that matches with the [id].
     */
    fun getExpenseStream(id: Int): Flow<Expense?>

    /**
     * Retrieve date of expense that matches with the [id].
     */
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

    /**
     * Retrieve total amount of expenses for specific car.
     */
    suspend fun getTotalExpensesForCar(carId: Int): Flow<Double>

    /**
     * Retrieve total amount of specific expenses for specific car.
     */
    suspend fun getTotalExpensesKind(carId: Int, kind: String): Flow<Double>
}