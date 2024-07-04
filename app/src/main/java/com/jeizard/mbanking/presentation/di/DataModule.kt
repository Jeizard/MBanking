package com.jeizard.mbanking.presentation.di

import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.repository.AccountTransactionRepositoryRoomImpl
import com.jeizard.mbanking.data.room.models.single.accounts.repository.AccountRepositoryRoomImpl
import com.jeizard.mbanking.data.room.models.single.transactions.repository.TransactionRepositoryRoomImpl
import com.jeizard.mbanking.domain.repositories.AccountRepository
import com.jeizard.mbanking.domain.repositories.AccountTransactionRepository
import com.jeizard.mbanking.domain.repositories.TransactionRepository
import org.koin.dsl.module

val dataModule = module {

    single<AccountRepository> {
        AccountRepositoryRoomImpl(application = get())
    }

    single<TransactionRepository> {
        TransactionRepositoryRoomImpl(application = get())
    }

    single<AccountTransactionRepository> {
        AccountTransactionRepositoryRoomImpl(application = get())
    }
}