package com.mouse.wallet.ui

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope

data class ScreenState(
    val snackbarHostState: SnackbarHostState,
    val scope: CoroutineScope,
)