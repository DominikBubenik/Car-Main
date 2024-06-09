package com.example.car_main.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car)

    @Update
    suspend fun update(car: Car)

    @Delete
    suspend fun delete(car: Car)

    @Query("SELECT * from user_Cars WHERE id = :id")
    fun getItem(id: Int): Flow<Car>

    @Query("SELECT * from user_Cars ORDER BY brand ASC")
    fun getAllItems(): Flow<List<Car>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM car_expenses WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense>

    @Query("SELECT * FROM car_expenses WHERE car_id = :carId ORDER BY date ASC")
    fun getExpensesForCar(carId: Int): Flow<List<Expense>>

    @Query("SELECT SUM(value) FROM car_expenses WHERE car_id = :carId")
    fun getTotalExpensesForCar(carId: Int): Flow<Double>

    @Query("SELECT date FROM car_expenses WHERE id = :expenseId")
    fun getExpenseDate(expenseId: Int): Flow<Long>

    @Query("SELECT SUM(value) FROM car_expenses WHERE car_id = :carId AND kind = :kind")
    fun getTotalExpensesKind(carId: Int, kind: String): Flow<Double>
}