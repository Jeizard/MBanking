package com.jeizard.mbanking.ui.screens.common.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeizard.mbanking.models.Transaction
import com.jeizard.mbanking.models.TransactionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> get() = _transactions

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
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company2\"",
                    number = "f4345jfshjek3453",
                    date = "03.06.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Declined
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3452",
                    date = "02.06.2024",
                    amount = "$10.09",
                    status = TransactionStatus.InProgress
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3451",
                    date = "01.06.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                ),
                Transaction(
                    company = "OOO \"Company\"",
                    number = "f4345jfshjek3450",
                    date = "29.05.2024",
                    amount = "$10.09",
                    status = TransactionStatus.Executed
                )
            )
            _transactions.value = sampleTransactions
        }
    }
}
