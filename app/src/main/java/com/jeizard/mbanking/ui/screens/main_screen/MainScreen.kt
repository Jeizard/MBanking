package com.jeizard.mbanking.ui.screens.main_screen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jeizard.mbanking.utils.navigation.NavigationItem
import com.jeizard.mbanking.ui.screens.common.view_models.TransactionsViewModel
import com.jeizard.mbanking.ui.theme.MBankingTheme

@Composable
fun MainScreen(navController: NavHostController, viewModel: TransactionsViewModel = viewModel()) {
    MBankingTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.selectTransaction(null)
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
                        navController = navController,
                        viewModel = viewModel
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