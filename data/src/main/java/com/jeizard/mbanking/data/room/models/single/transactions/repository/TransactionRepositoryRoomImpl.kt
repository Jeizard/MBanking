package com.jeizard.mbanking.data.room.models.single.transactions.repository

import android.app.Application
import com.jeizard.mbanking.data.room.AppDatabase
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao.AccountTransactionDao
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity
import com.jeizard.mbanking.data.room.models.single.transactions.dao.TransactionDao
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity
import com.jeizard.mbanking.data.room.models.single.transactions.mapper.TransactionDBEntityMapper
import com.jeizard.mbanking.domain.entities.Transaction
import com.jeizard.mbanking.domain.repositories.BaseRepository
import com.jeizard.mbanking.domain.repositories.TransactionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionRepositoryRoomImpl(application: Application) :
    TransactionRepository {
    private var allTransactionsByAccount: List<TransactionDBEntity> = emptyList()
    private val listeners: MutableSet<BaseRepository.OnDataChangedListener<Transaction>> = HashSet()
    private val transactionDao: TransactionDao = AppDatabase.getInstance(application).transactionDao()
    private val transactionDBEntityMapper: TransactionDBEntityMapper = TransactionDBEntityMapper()

    private val accountTransactionDao: AccountTransactionDao = AppDatabase.getInstance(application).accountTransactionDao()

    override var accountNumber: Long = 0
        set(value) {
            field = value
            CoroutineScope(Dispatchers.IO).launch {
                initData()
            }
        }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            initData()
        }
    }

    private suspend fun initData() {
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

    override suspend fun update(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.update(transactionDBEntityMapper.mapToDBEntity(transaction))
            accountTransactionDao.update(AccountTransactionDBEntity(accountNumber, transaction.number))
            allTransactionsByAccount = transactionDao.getAllTransactionsByAccountNumber(accountNumber)
        }
        withContext(Dispatchers.Main) {
            notifyChanges()
        }
    }

    override suspend fun delete(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.delete(transactionDBEntityMapper.mapToDBEntity(transaction))
            accountTransactionDao.delete(AccountTransactionDBEntity(accountNumber, transaction.number))
            allTransactionsByAccount = transactionDao.getAllTransactionsByAccountNumber(accountNumber)
        }
        withContext(Dispatchers.Main) {
            notifyChanges()
        }
    }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            transactionDao.deleteAll()
            allTransactionsByAccount = transactionDao.getAllTransactionsByAccountNumber(accountNumber)
        }
        withContext(Dispatchers.Main) {
            notifyChanges()
        }
    }

    override fun getAll(): List<Transaction> {
        return transactionDBEntityMapper.mapFromDBEntity(allTransactionsByAccount)
    }

    override fun addListener(listener: BaseRepository.OnDataChangedListener<Transaction>) {
        listeners.add(listener)
        listener.onChanged(transactionDBEntityMapper.mapFromDBEntity(allTransactionsByAccount))
    }

    override fun removeListener(listener: BaseRepository.OnDataChangedListener<Transaction>) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        for (listener in listeners) {
            listener.onChanged(transactionDBEntityMapper.mapFromDBEntity(allTransactionsByAccount))
        }
    }
}