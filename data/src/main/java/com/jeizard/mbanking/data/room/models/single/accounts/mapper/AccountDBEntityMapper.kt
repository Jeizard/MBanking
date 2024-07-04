package com.jeizard.mbanking.data.room.models.single.accounts.mapper

import com.jeizard.mbanking.data.mapper.Mapper
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity
import com.jeizard.mbanking.domain.entities.Account

class AccountDBEntityMapper : Mapper<AccountDBEntity, Account> {

    override fun mapFromDBEntity(d: AccountDBEntity): Account {
        return Account(d.number, d.name, d.card)
    }

    override fun mapToDBEntity(e: Account): AccountDBEntity {
        return AccountDBEntity(e.number, e.name, e.card)
    }

    override fun mapFromDBEntity(dCollection: Collection<AccountDBEntity>): List<Account> {
        return dCollection.map { mapFromDBEntity(it) }
    }

    override fun mapToDBEntity(eCollection: Collection<Account>): List<AccountDBEntity> {
        return eCollection.map { mapToDBEntity(it) }
    }
}

