package com.jeizard.mbanking.ui.screens.common.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeizard.mbanking.utils.models.Transaction
import com.jeizard.mbanking.utils.models.TransactionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class TransactionsViewModel : ViewModel() {
    private val _allTransactions = MutableStateFlow<List<Transaction>>(emptyList())
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    private val _selectedTransaction = MutableStateFlow<Transaction?>(null)
    val selectedTransaction: StateFlow<Transaction?> = _selectedTransaction.asStateFlow()
    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            val sampleTransactions = listOf(
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3454",
                    date = "06.06.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company2\"",
                    number = "f4345jfshjek3453",
                    date = "03.06.2024",
                    amount = "10.09",
                    status = TransactionStatus.Declined
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3452",
                    date = "02.06.2024",
                    amount = "10.09",
                    status = TransactionStatus.InProgress
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3451",
                    date = "01.06.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "10.09",
                    status = TransactionStatus.Executed
                )
            )
            _allTransactions.emit(sampleTransactions)
            _transactions.emit(sampleTransactions)
        }
    }

    fun filterTransactions(startDate: String, endDate: String) {
        viewModelScope.launch {
            if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val startDateParsed = dateFormat.parse(startDate)?.time
                val endDateParsed = dateFormat.parse(endDate)?.time

                _transactions.emit(
                    _allTransactions.value.filter { transaction ->
                        val transactionDate = dateFormat.parse(transaction.date)?.time
                        transactionDate != null && startDateParsed != null && endDateParsed != null &&
                                transactionDate in startDateParsed..endDateParsed
                    }
                )
            } else {
                _transactions.emit(_allTransactions.value)
            }
        }
    }

    fun selectTransaction(transaction: Transaction?) {
        viewModelScope.launch {
            _selectedTransaction.emit(transaction)
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            _allTransactions.update { currentList ->
                val updatedList = currentList + transaction
                sortTransactionsByDate(updatedList)
            }
            _transactions.update { _allTransactions.value }
        }
    }

    private fun sortTransactionsByDate(transactions: List<Transaction>): List<Transaction> {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return transactions.sortedByDescending { transaction ->
            dateFormat.parse(transaction.date)?.time
        }
    }
}
