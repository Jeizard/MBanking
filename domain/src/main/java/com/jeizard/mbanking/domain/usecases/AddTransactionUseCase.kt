package com.jeizard.mbanking.domain.usecases

import com.jeizard.mbanking.domain.entities.Transaction
import com.jeizard.mbanking.domain.repositories.TransactionRepository

class AddTransactionUseCase(private val transactionRepository: TransactionRepository) {
    suspend fun execute(transaction: Transaction){
        transactionRepository.insert(transaction)
    }
}