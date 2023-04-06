package com.mouse.wallet.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

@Composable
fun Screen(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable (ScreenState) -> Unit,
) {
    val screenState = ScreenState(snackbarHostState, scope)
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        content(screenState)
    }
}

data class ScreenState(
    val snackbarHostState: SnackbarHostState,
    val scope: CoroutineScope,
)