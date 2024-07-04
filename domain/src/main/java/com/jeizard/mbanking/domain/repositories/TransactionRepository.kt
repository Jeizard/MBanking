package com.jeizard.mbanking.domain.repositories

import com.jeizard.mbanking.domain.entities.Transaction

interface TransactionRepository: BaseRepository<Transaction>{
    var accountNumber: Long
}