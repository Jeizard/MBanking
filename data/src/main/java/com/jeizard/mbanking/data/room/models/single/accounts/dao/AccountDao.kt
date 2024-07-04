package com.jeizard.mbanking.data.room.models.single.accounts.dao

import androidx.room.Dao
import androidx.room.Query
import com.jeizard.mbanking.data.room.bases.BaseDao
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity

@Dao
abstract class AccountDao : BaseDao<AccountDBEntity> {

    @Query("DELETE FROM accounts")
    abstract fun deleteAllAccounts()

    @Query("SELECT * FROM accounts")
    abstract fun getAllAccounts(): List<AccountDBEntity>

    override fun deleteAll() {
        deleteAllAccounts()
    }

    override fun getAll(): List<AccountDBEntity> {
        return getAllAccounts()
    }
}
