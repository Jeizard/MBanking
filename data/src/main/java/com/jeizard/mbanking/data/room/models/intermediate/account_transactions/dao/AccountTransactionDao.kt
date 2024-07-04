package com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao

import androidx.room.Dao
import androidx.room.Insert
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity

@Dao
interface AccountTransactionDao {
    @Insert
    fun insert(accountTransactionDBEntity: AccountTransactionDBEntity)
}
