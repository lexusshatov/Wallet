package com.mouse.wallet.ui.navigation

sealed class Graph(val route: String) {
    object Login : Graph("login_graph_route")
    object Home : Graph("home_graph_route")
}
