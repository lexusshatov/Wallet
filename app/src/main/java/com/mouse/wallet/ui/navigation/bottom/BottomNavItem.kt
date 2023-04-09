package com.mouse.wallet.ui.navigation.bottom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mouse.wallet.R
import com.mouse.wallet.ui.navigation.Graph

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
) {
    object Coins : BottomNavItem(R.string.coins, R.drawable.ic_coin, Graph.Coins.route)
    object Exchange : BottomNavItem(R.string.exchange, R.drawable.ic_exchange, Graph.Exchange.route)
    object Profile : BottomNavItem(R.string.profile, R.drawable.ic_profile, Graph.Profile.route)
}
