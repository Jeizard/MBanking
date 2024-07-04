package com.jeizard.mbanking.data.room.models.single.transactions.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeizard.mbanking.domain.entities.TransactionStatus

@Entity(tableName = "transactions")
data class TransactionDBEntity(
    @PrimaryKey val number: String,
    val company: String,
    val date: String,
    val amount: Double,
    val status: TransactionStatus
)
