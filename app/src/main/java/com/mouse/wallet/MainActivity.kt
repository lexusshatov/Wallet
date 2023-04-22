package com.mouse.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.mouse.wallet.ui.navigation.WalletNavHost
import com.mouse.wallet.ui.navigation.bottom.WalletNavigationBar
import com.mouse.wallet.ui.theme.WalletTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

            WalletTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = { WalletNavigationBar(navController) }
                ) {
                    WalletNavHost(
                        modifier = Modifier.padding(bottom = 70.dp),
                        navController,
                        snackbarHostState
                    )
                }
            }
        }
    }
}