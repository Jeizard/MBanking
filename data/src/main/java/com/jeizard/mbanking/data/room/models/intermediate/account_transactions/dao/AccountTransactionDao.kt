package com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao

import androidx.room.Dao
import androidx.room.Query
import com.jeizard.mbanking.data.room.bases.BaseDao
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity

@Dao
abstract class AccountTransactionDao : BaseDao<AccountTransactionDBEntity> {

    @Query("DELETE FROM account_transactions")
    abstract fun deleteAllAccountTransactions()

    @Query("SELECT * FROM account_transactions")
    abstract fun getAllAccountTransactions(): List<AccountTransactionDBEntity>

    override fun deleteAll() {
        deleteAllAccountTransactions()
    }

    override fun getAll(): List<AccountTransactionDBEntity> {
        return getAllAccountTransactions()
    }
}
