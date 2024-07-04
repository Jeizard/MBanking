package com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity

@Entity(
    tableName = "account_transactions",
    primaryKeys = ["account_number", "transaction_number"],
    indices = [
        Index("transaction_number")
    ],
    foreignKeys = [
        ForeignKey(
            entity = AccountDBEntity::class,
            parentColumns = ["number"],
            childColumns = ["account_number"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TransactionDBEntity::class,
            parentColumns = ["number"],
            childColumns = ["transaction_number"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class AccountTransactionDBEntity(
    @ColumnInfo(name = "account_number") val accountNumber: Long,
    @ColumnInfo(name = "transaction_number") val transactionNumber: String
)