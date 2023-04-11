package com.mouse.wallet.ui.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.ui.navigation.Graph
import com.mouse.wallet.ui.navigation.Screen
import com.mouse.wallet.ui.screen.home.CurrencyScreen

fun NavGraphBuilder.coinsGraph(
    navController: NavController,
    screenState: ScreenState,
) {
    navigation(startDestination = Screen.Currency.route, route = Graph.Coins.route) {
        composable(Screen.Currency.route) {
            CurrencyScreen(screenState)
        }
    }
}