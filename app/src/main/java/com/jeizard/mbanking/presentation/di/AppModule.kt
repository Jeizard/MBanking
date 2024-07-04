package com.jeizard.mbanking.presentation.di

import com.jeizard.mbanking.presentation.ui.screens.common.view_models.AllTransactionsViewModel
import com.jeizard.mbanking.presentation.ui.screens.common.view_models.MainViewModel
import com.jeizard.mbanking.presentation.ui.screens.common.view_models.TransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            accountRepository = get(),
            updateTransactionsByAccountNumberUseCase = get()
        )
    }

    viewModel<AllTransactionsViewModel> {
        AllTransactionsViewModel(transactionRepository = get())
    }

    viewModel<TransactionViewModel> {
        TransactionViewModel(addTransactionUseCase = get())
    }
}