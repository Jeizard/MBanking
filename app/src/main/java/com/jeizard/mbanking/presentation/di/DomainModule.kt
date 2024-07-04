package com.jeizard.mbanking.presentation.di

import com.jeizard.mbanking.domain.usecases.AddTransactionUseCase
import com.jeizard.mbanking.domain.usecases.UpdateTransactionsByAccountNumberUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<UpdateTransactionsByAccountNumberUseCase> {
        UpdateTransactionsByAccountNumberUseCase(transactionRepository = get())
    }

    factory<AddTransactionUseCase> {
        AddTransactionUseCase(transactionRepository = get())
    }
}