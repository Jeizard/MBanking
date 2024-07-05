package com.jeizard.mbanking.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.dao.AccountTransactionDao
import com.jeizard.mbanking.data.room.models.intermediate.account_transactions.entity.AccountTransactionDBEntity
import com.jeizard.mbanking.data.room.models.single.accounts.dao.AccountDao
import com.jeizard.mbanking.data.room.models.single.accounts.entity.AccountDBEntity
import com.jeizard.mbanking.data.room.models.single.transactions.dao.TransactionDao
import com.jeizard.mbanking.data.room.models.single.transactions.entity.TransactionDBEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        AccountDBEntity::class,
        TransactionDBEntity::class,
        AccountTransactionDBEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun accountTransactionDao(): AccountTransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "mbanking_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(CALLBACK)
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database)
                    }
                }
            }
            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database)
                    }
                }
            }
        }

        private fun populateDatabase(db: AppDatabase) {
            db.runInTransaction {
                db.accountDao().insert(
                    AccountDBEntity(
                        name = "Saving Account 1",
                        number = 11212129291221,
                        card = "•••• 1234"
                    )
                )
                db.accountDao().insert(
                    AccountDBEntity(
                        name = "Saving Account 2",
                        number = 21212129291221,
                        card = "•••• 1234"
                    )
                )
                db.accountDao().insert(
                    AccountDBEntity(
                        name = "Saving Account 3",
                        number = 31212129291221,
                        card = "•••• 1234"
                    )
                )
            }
        }
    }
}
