package com.mouse.wallet.ui.navigation.bottom

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.data.isSignedIn
import com.mouse.wallet.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WalletNavigationBar(
    navController: NavController,
    userViewModel: UserViewModel = koinViewModel(),
) {
    val items = listOf(
        BottomNavItem.Coins,
        BottomNavItem.Exchange,
        BottomNavItem.Profile
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), stringResource(id = item.title)) },
                label = { Text(stringResource(item.title), fontSize = 9.sp) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    if (!userViewModel.user.isSignedIn) return@NavigationBarItem
                    navController.navigate(item.route)
                }
            )
        }
    }
}