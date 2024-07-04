package com.jeizard.mbanking.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao.AccountTransactionDao
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity
import com.jeizard.mbanking.data.room.models.single.accounts.dao.AccountDao
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity

@Database(
    entities = [
        AccountDBEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "mbanking_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as AppDatabase
        }
    }
}
