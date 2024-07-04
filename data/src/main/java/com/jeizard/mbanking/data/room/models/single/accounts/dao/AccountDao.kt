package com.jeizard.mbanking.data.room.models.single.accounts.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity

@Dao
interface  AccountDao {
    @Insert
    fun insert(accountDBEntity: AccountDBEntity)

    @Query("SELECT * FROM accounts")
    fun getAll(): List<AccountDBEntity>
}
