package com.jeizard.mbanking.data.room.models.single.transactions.mapper

import com.jeizard.mbanking.data.mapper.Mapper
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity
import com.jeizard.mbanking.domain.entities.Transaction

class TransactionDBEntityMapper : Mapper<TransactionDBEntity, Transaction> {

    override fun mapFromDBEntity(d: TransactionDBEntity): Transaction {
        return Transaction(d.number, d.company, d.date, d.amount, d.status)
    }

    override fun mapToDBEntity(e: Transaction): TransactionDBEntity {
        return TransactionDBEntity(e.number, e.company, e.date, e.amount, e.status)
    }

    override fun mapFromDBEntity(dCollection: Collection<TransactionDBEntity>): List<Transaction> {
        return dCollection.map { mapFromDBEntity(it) }
    }

    override fun mapToDBEntity(eCollection: Collection<Transaction>): List<TransactionDBEntity> {
        return eCollection.map { mapToDBEntity(it) }
    }
}

