package com.jeizard.mbanking.domain.repositories

import com.jeizard.mbanking.domain.entities.AccountTransaction

interface AccountTransactionRepository{
    suspend fun insert(accountTransaction: AccountTransaction)
}