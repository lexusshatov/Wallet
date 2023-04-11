package com.mouse.wallet.ui.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.ui.navigation.Graph
import com.mouse.wallet.ui.navigation.Screen
import com.mouse.wallet.ui.screen.auth.LoginScreen

fun NavGraphBuilder.loginGraph(
    navController: NavController,
    screenState: ScreenState,
) {
    navigation(startDestination = Screen.Login.route, route = Graph.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(screenState) { navController.navigate(Screen.Currency.route) }
        }
    }
}