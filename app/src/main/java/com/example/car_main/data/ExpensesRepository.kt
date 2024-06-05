package com.example.car_main.data

import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {
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
}