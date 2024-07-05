package com.jeizard.mbanking.domain.entities

data class Transaction(
    val number: String,
    val company: String,
    val date: String,
    val amount: Double,
    val status: TransactionStatus
)
