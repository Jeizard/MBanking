package com.jeizard.mbanking.utils.models

data class Transaction(
    var company: String,
    var number: String,
    var date: String,
    var amount: String,
    var status: TransactionStatus
)