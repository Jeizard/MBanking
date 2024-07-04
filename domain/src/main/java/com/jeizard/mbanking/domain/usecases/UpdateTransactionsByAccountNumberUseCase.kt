package com.jeizard.mbanking.domain.usecases

import com.jeizard.mbanking.domain.entities.Transaction
import com.jeizard.mbanking.domain.repositories.TransactionRepository

class UpdateTransactionsByAccountNumberUseCase(private val transactionRepository: TransactionRepository) {
    fun execute(accountNumber: Long){
        transactionRepository.accountNumber = accountNumber
    }
}