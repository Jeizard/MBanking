package com.jeizard.mbanking.navigation

enum class Screen {
    MAIN,
    ALLTRANSACTIONS
}
sealed class NavigationItem(val route: String) {
    data object Main : NavigationItem(Screen.MAIN.name)
    data object AllTransactions : NavigationItem(Screen.ALLTRANSACTIONS.name)
}