package com.jeizard.mbanking.presentation.ui.screens.main_screen

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
import com.jeizard.mbanking.domain.entities.Account
import com.jeizard.mbanking.presentation.ui.screens.common.view_models.MainViewModel
import com.jeizard.mbanking.presentation.ui.theme.DarkGrey
import com.jeizard.mbanking.presentation.ui.theme.LightBlue
import com.jeizard.mbanking.presentation.ui.theme.MBankingTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSection(viewModel: MainViewModel = koinViewModel()) {
    val selectedAccount by viewModel.selectedAccount.collectAsState()
    val accounts by viewModel.accounts.collectAsState()

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
                    AccountCard(
                        selectedAccount = account,
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
            selectedAccount = selectedAccount ?: Account(0, "Error", "Error"),
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