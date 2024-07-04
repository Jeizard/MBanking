package com.jeizard.mbanking.domain.repositories

import com.jeizard.mbanking.domain.entities.Account

interface AccountRepository{
    fun getAll(): List<Account>

    interface OnAccountsChangedListener {
        fun onChanged(items: List<Account>)
    }

    fun addListener(listener: OnAccountsChangedListener)
    fun removeListener(listener: OnAccountsChangedListener)
}