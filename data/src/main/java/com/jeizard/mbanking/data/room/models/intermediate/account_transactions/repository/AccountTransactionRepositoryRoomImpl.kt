package com.jeizard.mbanking.data.room.models.intermediate.account_transactions.repository

import android.app.Application
import com.jeizard.mbanking.data.room.AppDatabase
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao.AccountTransactionDao
import com.jeizard.mbanking.domain.entities.AccountTransaction
import com.jeizard.mbanking.domain.repositories.AccountTransactionRepository
import com.jeizard.quizapp.data.room.models.room.single_models.images.mapper.AccountTransactionDBEntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountTransactionRepositoryRoomImpl(application: Application) : AccountTransactionRepository {
    private val accountTransactionDao: AccountTransactionDao = AppDatabase.getInstance(application).accountTransactionDao()
    private val accountTransactionDBEntityMapper: AccountTransactionDBEntityMapper = AccountTransactionDBEntityMapper()

    override suspend fun insert(accountTransaction: AccountTransaction) {
        withContext(Dispatchers.IO) {
            accountTransactionDao.insert(
                accountTransactionDBEntityMapper.mapToDBEntity(
                    accountTransaction
                )
            )
        }
    }
}