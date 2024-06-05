package com.example.car_main.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM car_expenses WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense>

    @Query("SELECT * FROM car_expenses WHERE car_id = :carId ORDER BY id ASC")
    fun getExpensesForCar(carId: Int): Flow<List<Expense>>

    @Query("SELECT SUM(value) FROM car_expenses WHERE car_id = :carId")
    fun getTotalExpensesForCar(carId: Int): Flow<Int>

    @Query("SELECT date FROM car_expenses WHERE id = :expenseId")
    fun getExpenseDate(expenseId: Int): Flow<Long>
}