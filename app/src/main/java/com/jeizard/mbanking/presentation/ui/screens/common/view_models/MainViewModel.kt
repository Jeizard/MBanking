package com.jeizard.mbanking.presentation.ui.screens.common.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeizard.mbanking.domain.repositories.AccountRepository
import com.jeizard.mbanking.domain.entities.Account
import com.jeizard.mbanking.domain.usecases.UpdateTransactionsByAccountNumberUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    accountRepository: AccountRepository,
    private val updateTransactionsByAccountNumberUseCase: UpdateTransactionsByAccountNumberUseCase
) : ViewModel() {

    private val _accounts = MutableStateFlow<List<Account>>(emptyList())
    val accounts: StateFlow<List<Account>> = _accounts.asStateFlow()

    private val _selectedAccount = MutableStateFlow<Account?>(null)
    val selectedAccount: StateFlow<Account?> = _selectedAccount.asStateFlow()

    private val accountsListener = object : AccountRepository.OnAccountsChangedListener {
        override fun onChanged(accounts: List<Account>) {
            viewModelScope.launch {
                _accounts.update { accounts }
                selectAccount(accounts.firstOrNull())
            }
        }
    }

    init {
        accountRepository.addListener(accountsListener)
    }

    fun selectAccount(account: Account?) {
        if (account != null) {
            _selectedAccount.update { account }
            updateTransactionsByAccountNumberUseCase.execute(account.number)
        }
    }
}
