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
}