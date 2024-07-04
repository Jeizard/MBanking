package com.jeizard.mbanking.data.room.models.single.transactions.dao

import androidx.room.Dao
import androidx.room.Query
import com.jeizard.mbanking.data.room.bases.BaseDao
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity

@Dao
abstract class TransactionDao : BaseDao<TransactionDBEntity> {

    @Query("DELETE FROM transactions")
    abstract fun deleteAllTransactions()

    @Query("SELECT * FROM transactions")
    abstract fun getAllTransactions(): List<TransactionDBEntity>

    override fun deleteAll() {
        deleteAllTransactions()
    }

    override fun getAll(): List<TransactionDBEntity> {
        return getAllTransactions()
    }

    @Query(
        "SELECT * " +
        "FROM transactions " +
        "JOIN account_transactions " +
        "ON transactions.number = account_transactions.transaction_number " +
        "WHERE account_transactions.account_number = :accountNumber"
    )
    abstract fun getAllTransactionsByAccountNumber(accountNumber: Long): List<TransactionDBEntity>
}
