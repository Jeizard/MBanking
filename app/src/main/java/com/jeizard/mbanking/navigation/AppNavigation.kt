package com.jeizard.mbanking.navigation

enum class Screen {
    MAIN
}
sealed class NavigationItem(val route: String) {
    data object Main : NavigationItem(Screen.MAIN.name)
}