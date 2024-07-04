package com.jeizard.mbanking.data.room.models.single.transactions.repository

import android.app.Application
import com.jeizard.mbanking.data.room.AppDatabase
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao.AccountTransactionDao
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity
import com.jeizard.mbanking.data.room.models.single.transactions.dao.TransactionDao
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity
import com.jeizard.mbanking.data.room.models.single.transactions.mapper.TransactionDBEntityMapper
import com.jeizard.mbanking.domain.entities.Transaction
import com.jeizard.mbanking.domain.repositories.TransactionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionRepositoryRoomImpl(application: Application) :
    TransactionRepository {
    private var allTransactionsByAccount: List<TransactionDBEntity> = emptyList()
    private val listeners: MutableSet<TransactionRepository.OnTransactionsChangedListener> = HashSet()
    private val transactionDao: TransactionDao = AppDatabase.getInstance(application).transactionDao()
    private val transactionDBEntityMapper: TransactionDBEntityMapper = TransactionDBEntityMapper()

    private val accountTransactionDao: AccountTransactionDao = AppDatabase.getInstance(application).accountTransactionDao()

    override var accountNumber: Long = 0
        set(value) {
            field = value
            CoroutineScope(Dispatchers.IO).launch {
                updateAllTransactionsByAccountNumber()
            }
        }

    private suspend fun updateAllTransactionsByAccountNumber() {
        allTransactionsByAccount = withContext(Dispatchers.IO) { transactionDao.getAllTransactionsByAccountNumber(accountNumber) }
        withContext(Dispatchers.Main) {
            notifyChanges()
        }
    }

    override suspend fun insert(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.insert(transactionDBEntityMapper.mapToDBEntity(transaction))
            accountTransactionDao.insert(AccountTransactionDBEntity(accountNumber, transaction.number))
            allTransactionsByAccount = transactionDao.getAllTransactionsByAccountNumber(accountNumber)
        }
        withContext(Dispatchers.Main) {
            notifyChanges()
        }
    }

    override fun getAll(): List<Transaction> {
        return transactionDBEntityMapper.mapFromDBEntity(allTransactionsByAccount)
    }

    override fun addListener(listener: TransactionRepository.OnTransactionsChangedListener) {
        listeners.add(listener)
        listener.onChanged(transactionDBEntityMapper.mapFromDBEntity(allTransactionsByAccount))
    }

    override fun removeListener(listener: TransactionRepository.OnTransactionsChangedListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        for (listener in listeners) {
            listener.onChanged(transactionDBEntityMapper.mapFromDBEntity(allTransactionsByAccount))
        }
    }
}