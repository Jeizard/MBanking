package com.jeizard.mbanking.domain.entities

data class Transaction(
    val number: Long,
    val company: String,
    val date: String,
    val amount: Double,
    val status: TransactionStatus
)
