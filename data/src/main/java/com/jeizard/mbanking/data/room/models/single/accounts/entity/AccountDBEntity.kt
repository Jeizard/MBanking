package com.jeizard.mbanking.data.room.models.single.accounts.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountDBEntity(
    @PrimaryKey val number: Long,
    val name: String,
    val card: String
)
