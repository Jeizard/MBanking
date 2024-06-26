package com.jeizard.mbanking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeizard.mbanking.ui.screens.all_transactions_screen.AllTransactionsScreen
import com.jeizard.mbanking.ui.screens.main_screen.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainScreen()
        }
        composable(NavigationItem.AllTransactions.route) {
            AllTransactionsScreen()
        }
    }
}