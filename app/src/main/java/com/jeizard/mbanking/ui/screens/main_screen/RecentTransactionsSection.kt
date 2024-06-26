package com.jeizard.mbanking.ui.screens.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeizard.mbanking.models.Transaction
import com.jeizard.mbanking.models.TransactionStatus
import com.jeizard.mbanking.ui.screens.common.TransactionItem
import com.jeizard.mbanking.ui.theme.DarkGrey
import com.jeizard.mbanking.ui.theme.MBankingTheme

@Composable
fun RecentTransactionsSection() {
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
        )
    )

    MBankingTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Recent Transactions",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "VIEW ALL",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable{ /* Navigate to All Transactions screen*/ }
                )
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = DarkGrey)
            ) {
                LazyColumn {
                    items(transactions.take(5)) { transaction ->
                        TransactionItem(transaction)
                    }
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun RecentTransactionsSectionPreview() {
    MBankingTheme {
        RecentTransactionsSection()
    }
}