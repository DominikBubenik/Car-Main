package com.example.car_main.data

import kotlinx.coroutines.flow.Flow

class OfflineExpensesRepository(private val expenseDao: ExpenseDao) : ExpensesRepository {
    override fun getAllExpensesForCarStream(carId: Int): Flow<List<Expense>> = expenseDao.getExpensesForCar(carId)

    override fun getExpenseStream(id: Int): Flow<Expense?> = expenseDao.getExpense(id)

    override fun getExpenseDate(id: Int): Flow<Long?> = expenseDao.getExpenseDate(id)

    override suspend fun insertExpense(expense: Expense) = expenseDao.insert(expense)

    override suspend fun deleteExpense(expense: Expense) = expenseDao.delete(expense)

    override suspend fun updateExpense(expense: Expense) = expenseDao.update(expense)
}