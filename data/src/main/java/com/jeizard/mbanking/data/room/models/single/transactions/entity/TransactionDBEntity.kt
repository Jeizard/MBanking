package com.jeizard.mbanking.data.room.models.single.transactions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity
import com.jeizard.mbanking.domain.entities.TransactionStatus

@Entity(tableName = "transactions")
data class TransactionDBEntity(
    @PrimaryKey val number: Long,
    val company: String,
    val date: String,
    val amount: Double,
    val status: TransactionStatus
)
