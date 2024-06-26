package com.jeizard.mbanking.ui.screens.all_transactions_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeizard.mbanking.R
import com.jeizard.mbanking.models.Transaction
import com.jeizard.mbanking.models.TransactionStatus
import com.jeizard.mbanking.ui.screens.common.TransactionItem
import com.jeizard.mbanking.ui.theme.DarkGrey
import com.jeizard.mbanking.ui.theme.MBankingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTransactionsScreen() {
    val transactions = listOf(
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3454",
            date = "06.06.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company2\"",
            number = "f4345jfshjek3453",
            date = "03.06.2024",
            amount = "$10.09",
            status = TransactionStatus.Declined
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3452",
            date = "02.06.2024",
            amount = "$10.09",
            status = TransactionStatus.InProgress
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3451",
            date = "01.06.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        ),
        Transaction(
            company = "OOO \"Company\"",
            number = "f4345jfshjek3450",
            date = "29.05.2024",
            amount = "$10.09",
            status = TransactionStatus.Executed
        )
    )

    MBankingTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "All Transactions",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*Navigate to Main screen (Back)*/ }) {
                            Icon(Icons.Rounded.KeyboardArrowLeft, "ArrowLeftIcon")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*Navigate to Filter By Date screen*/ }) {
                            Icon(painter = painterResource(R.drawable.ic_filter), "FilterIcon")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
                )
            },
            content = { paddingValues ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkGrey)
                ) {
                    LazyColumn {
                        items(transactions) { transaction ->
                            TransactionItem(transaction)
                        }
                    }
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AllTransactionsScreenOldPreview() {
    MBankingTheme {
        AllTransactionsScreen()
    }
}