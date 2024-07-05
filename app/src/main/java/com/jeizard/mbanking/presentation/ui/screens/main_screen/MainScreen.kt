package com.jeizard.mbanking.presentation.ui.screens.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jeizard.mbanking.presentation.ui.screens.common.view_models.MainViewModel
import com.jeizard.mbanking.presentation.ui.screens.common.view_models.TransactionViewModel
import com.jeizard.mbanking.utils.navigation.NavigationItem
import com.jeizard.mbanking.presentation.ui.theme.MBankingTheme
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreen(navController: NavHostController, transactionViewModel: TransactionViewModel = koinViewModel()) {
    MBankingTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        transactionViewModel.selectTransaction(null)
                        navController.navigate(NavigationItem.Transaction.route)
                    },
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add Icon"
                    )
                }
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    AccountSection()
                    RecentTransactionsSection(
                        navController = navController
                    )
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MBankingTheme {
        MainScreen(rememberNavController())
    }
}