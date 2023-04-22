package com.mouse.wallet.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.data.isSignedIn
import com.mouse.wallet.ui.ScreenState
import com.mouse.wallet.ui.navigation.graph.coinsGraph
import com.mouse.wallet.ui.navigation.graph.exchangeGraph
import com.mouse.wallet.ui.navigation.graph.loginGraph
import com.mouse.wallet.ui.navigation.graph.profileGraph
import com.mouse.wallet.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun WalletNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scope: CoroutineScope = rememberCoroutineScope(),
    userViewModel: UserViewModel = koinViewModel(),
) {
    val screenState = ScreenState(snackbarHostState, scope)

    val startDestination = if (userViewModel.user.isSignedIn) {
        Graph.Coins.route
    } else Graph.Login.route
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        loginGraph(navController, screenState)
        coinsGraph(navController, screenState)
        exchangeGraph(navController, screenState)
        profileGraph(navController, screenState)
    }
}