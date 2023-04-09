package com.mouse.wallet.ui.navigation

sealed class Graph(val route: String) {
    object Login : Graph("login_graph")
    object Coins : Graph("coins_graph")
    object Exchange : Graph("exchange_graph")
    object Profile : Graph("profile_graph")
}
