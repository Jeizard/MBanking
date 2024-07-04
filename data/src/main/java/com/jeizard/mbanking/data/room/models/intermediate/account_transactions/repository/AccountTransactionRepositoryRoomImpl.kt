package com.jeizard.mbanking.data.room.models.intermediate.account_transactions.repository

import android.app.Application
import com.jeizard.mbanking.data.room.AppDatabase
import com.jeizard.mbanking.data.room.bases.BaseRepositoryRoomImpl
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao.AccountTransactionDao
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity
import com.jeizard.mbanking.domain.entities.AccountTransaction
import com.jeizard.mbanking.domain.repositories.BaseRepository
import com.jeizard.quizapp.data.room.models.room.single_models.images.mapper.AccountTransactionDBEntityMapper

class AccountTransactionRepositoryRoomImpl(application: Application) :
    BaseRepositoryRoomImpl<AccountTransactionDBEntity, AccountTransactionDao, AccountTransaction>(
        AppDatabase.getInstance(application).accountTransactionDao(),
        AccountTransactionDBEntityMapper()
    ),
    BaseRepository<AccountTransaction>