package com.jeizard.mbanking.data.room.models.single.accounts.repository

import android.app.Application
import com.jeizard.mbanking.data.room.AppDatabase
import com.jeizard.mbanking.data.room.bases.BaseRepositoryRoomImpl
import com.jeizard.mbanking.data.room.models.single.accounts.dao.AccountDao
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity
import com.jeizard.mbanking.data.room.models.single.accounts.mapper.AccountDBEntityMapper
import com.jeizard.mbanking.domain.entities.Account
import com.jeizard.mbanking.domain.repositories.BaseRepository

class AccountRepositoryRoomImpl(application: Application) :
    BaseRepositoryRoomImpl<AccountDBEntity, AccountDao, Account>(
        AppDatabase.getInstance(application).accountDao(),
        AccountDBEntityMapper()
    ),
    BaseRepository<Account> {

}