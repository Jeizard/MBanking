package com.jeizard.mbanking.domain.repositories

import com.jeizard.mbanking.domain.entities.Transaction

interface TransactionRepository{
    var accountNumber: Long

    suspend fun insert(transaction: Transaction)
    fun getAll(): List<Transaction>

    interface OnTransactionsChangedListener {
        fun onChanged(transactions: List<Transaction>)
    }

    fun addListener(listener: OnTransactionsChangedListener)
    fun removeListener(listener: OnTransactionsChangedListener)
}