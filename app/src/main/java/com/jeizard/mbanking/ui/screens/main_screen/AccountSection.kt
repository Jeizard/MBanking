package com.jeizard.mbanking.ui.screens.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeizard.mbanking.ui.screens.common.view_models.TransactionsViewModel
import com.jeizard.mbanking.ui.theme.DarkGrey
import com.jeizard.mbanking.ui.theme.LightBlue
import com.jeizard.mbanking.ui.theme.MBankingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSection(viewModel: TransactionsViewModel = viewModel()) {
    val selectedAccount by viewModel.selectedAccount.collectAsState()
    val accounts by viewModel.accounts.collectAsState()

    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    BackHandler(onBack = {
        if (showBottomSheet) {
            showBottomSheet = false
        }
    })

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Text(
                text = "Select the account",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(accounts) { account ->
                    AccountCard(selectedAccount = account,
                        onCardClicked = {
                            viewModel.selectAccount(account)
                            showBottomSheet = false
                        },
                        backgroundColor = if (account == selectedAccount) LightBlue else DarkGrey,
                        showIcon = false
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize()
    ) {
        Text(
            text = "Account",
            style = MaterialTheme.typography.titleMedium
        )
        AccountCard(
            selectedAccount = selectedAccount!!,
            onCardClicked = {
                showBottomSheet = true
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AccountSectionPreview() {
    MBankingTheme {
        AccountSection()
    }
}