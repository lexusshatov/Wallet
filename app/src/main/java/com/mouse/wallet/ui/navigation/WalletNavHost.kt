package com.mouse.wallet.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.ui.screen.auth.LoginScreen
import com.mouse.wallet.ui.screen.home.HomeScreen
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
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(screenState) { navController.navigate(Screen.Home.route) }
        }
        composable(Screen.Home.route) {
            HomeScreen(screenState)
        }
    }
}