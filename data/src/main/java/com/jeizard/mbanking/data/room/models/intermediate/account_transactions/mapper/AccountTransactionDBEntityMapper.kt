package com.jeizard.quizapp.data.room.models.room.single_models.images.mapper

import com.jeizard.mbanking.data.mapper.Mapper
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity
import com.jeizard.mbanking.domain.entities.AccountTransaction

class AccountTransactionDBEntityMapper : Mapper<AccountTransactionDBEntity, AccountTransaction> {

    override fun mapFromDBEntity(d: AccountTransactionDBEntity): AccountTransaction {
        return AccountTransaction(d.accountNumber, d.transactionNumber)
    }

    override fun mapToDBEntity(e: AccountTransaction): AccountTransactionDBEntity {
        return AccountTransactionDBEntity(e.accountNumber, e.transactionNumber)
    }

    override fun mapFromDBEntity(dCollection: Collection<AccountTransactionDBEntity>): List<AccountTransaction> {
        return dCollection.map { mapFromDBEntity(it) }
    }

    override fun mapToDBEntity(eCollection: Collection<AccountTransaction>): List<AccountTransactionDBEntity> {
        return eCollection.map { mapToDBEntity(it) }
    }
}
