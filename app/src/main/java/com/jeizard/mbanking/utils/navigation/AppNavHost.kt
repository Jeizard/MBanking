package com.jeizard.mbanking.utils.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeizard.mbanking.presentation.ui.screens.all_transactions_screen.AllTransactionsScreen
import com.jeizard.mbanking.presentation.ui.screens.common.view_models.TransactionsViewModel
import com.jeizard.mbanking.presentation.ui.screens.main_screen.MainScreen
import com.jeizard.mbanking.presentation.ui.screens.transaction_screen.TransactionScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route,
    transactionViewModel: TransactionsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainScreen(navController = navController, viewModel = transactionViewModel)
        }
        composable(NavigationItem.AllTransactions.route) {
            AllTransactionsScreen(navController = navController, viewModel = transactionViewModel)
        }
        composable(NavigationItem.Transaction.route) {
            TransactionScreen(navController = navController, viewModel = transactionViewModel)
        }
    }
}