package com.mouse.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mouse.wallet.ui.navigation.WalletNavHost
import com.mouse.wallet.ui.theme.WalletTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalletTheme {
                val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) {
                    WalletNavHost(snackbarHostState = snackbarHostState)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WalletTheme {
        Greeting("Android")
    }
}