package com.jeizard.mbanking.presentation.ui.screens.common.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeizard.mbanking.domain.repositories.TransactionRepository
import com.jeizard.mbanking.domain.entities.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class AllTransactionsViewModel(
    transactionRepository: TransactionRepository
) : ViewModel() {

    private var allTransactionsByAccount = emptyList<Transaction>()
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    private val transactionsListener = object : TransactionRepository.OnTransactionsChangedListener {
        override fun onChanged(transactions: List<Transaction>) {
            viewModelScope.launch {
                allTransactionsByAccount = transactions
                sortTransactionsByDate()
                _transactions.update { allTransactionsByAccount }
            }
        }
    }

    init {
        transactionRepository.addListener(transactionsListener)
    }

    fun filterTransactions(startDate: String, endDate: String) {
        viewModelScope.launch {
            if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val startDateParsed = dateFormat.parse(startDate)?.time
                val endDateParsed = dateFormat.parse(endDate)?.time

                _transactions.update {
                    allTransactionsByAccount.filter { transaction ->
                        val transactionDate = dateFormat.parse(transaction.date)?.time
                        transactionDate != null && startDateParsed != null && endDateParsed != null &&
                                transactionDate in startDateParsed..endDateParsed
                    }
                }
            } else {
                _transactions.update { allTransactionsByAccount }
            }
        }
    }

    private fun sortTransactionsByDate() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        allTransactionsByAccount.sortedByDescending {
            dateFormat.parse(it.date)?.time ?: 0
        }
    }
}
