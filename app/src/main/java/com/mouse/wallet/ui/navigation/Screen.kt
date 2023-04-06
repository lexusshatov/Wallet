package com.mouse.wallet.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}
