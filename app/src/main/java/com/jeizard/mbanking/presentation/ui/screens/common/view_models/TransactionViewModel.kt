package com.jeizard.mbanking.presentation.ui.screens.common.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeizard.mbanking.domain.usecases.AddTransactionUseCase
import com.jeizard.mbanking.domain.entities.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {

    private val _selectedTransaction = MutableStateFlow<Transaction?>(null)
    val selectedTransaction: StateFlow<Transaction?> = _selectedTransaction.asStateFlow()

    fun selectTransaction(transaction: Transaction?) {
        if(transaction != null) {
            viewModelScope.launch {
                _selectedTransaction.update { transaction }
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
           addTransactionUseCase.execute(transaction)
        }
    }
}
