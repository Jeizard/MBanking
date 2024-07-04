package com.jeizard.mbanking.data.room.models.single.accounts.repository

import android.app.Application
import com.jeizard.mbanking.data.room.AppDatabase
import com.jeizard.mbanking.data.room.models.single.accounts.dao.AccountDao
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity
import com.jeizard.mbanking.data.room.models.single.accounts.mapper.AccountDBEntityMapper
import com.jeizard.mbanking.domain.entities.Account
import com.jeizard.mbanking.domain.repositories.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountRepositoryRoomImpl(application: Application) : AccountRepository {
    private var allAccounts: List<AccountDBEntity> = emptyList()
    private val accountDao: AccountDao = AppDatabase.getInstance(application).accountDao()
    private val accountDBEntityMapper: AccountDBEntityMapper = AccountDBEntityMapper()
    private val listeners: MutableSet<AccountRepository.OnAccountsChangedListener> = HashSet()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            allAccounts = accountDao.getAll()
            withContext(Dispatchers.Main) {
                notifyChanges()
            }
        }
    }

    override fun getAll(): List<Account> {
        return accountDBEntityMapper.mapFromDBEntity(allAccounts)
    }

    override fun addListener(listener: AccountRepository.OnAccountsChangedListener) {
        listeners.add(listener)
        listener.onChanged(accountDBEntityMapper.mapFromDBEntity(allAccounts))
    }

    override fun removeListener(listener: AccountRepository.OnAccountsChangedListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        for (listener in listeners) {
            listener.onChanged(accountDBEntityMapper.mapFromDBEntity(allAccounts))
        }
    }
}