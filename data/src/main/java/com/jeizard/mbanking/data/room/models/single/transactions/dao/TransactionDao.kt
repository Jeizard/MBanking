package com.jeizard.mbanking.data.room.models.single.transactions.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity

@Dao
interface TransactionDao {
    @Insert
    fun insert(transactionDBEntity: TransactionDBEntity)

    @Query(
        "SELECT * " +
        "FROM transactions " +
        "JOIN account_transactions " +
        "ON transactions.number = account_transactions.transaction_number " +
        "WHERE account_transactions.account_number = :accountNumber"
    )
    fun getAllTransactionsByAccountNumber(accountNumber: Long): List<TransactionDBEntity>
}
