package com.mouse.wallet.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.ui.navigation.graph.homeGraph
import com.mouse.wallet.ui.navigation.graph.loginGraph
import kotlinx.coroutines.CoroutineScope

@Composable
fun WalletNavHost(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    val screenState = ScreenState(snackbarHostState, scope)

    NavHost(
        navController = navController,
        startDestination = Graph.Login.route
    ) {
        loginGraph(navController, screenState)
        homeGraph(navController, screenState)
    }
}