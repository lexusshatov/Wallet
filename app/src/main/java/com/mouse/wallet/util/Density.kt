package com.mouse.wallet.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize

@Composable
fun IntSize.toDpSize(): DpSize = with(LocalDensity.current) {
    val width = this@toDpSize.width.toDp()
    val height = this@toDpSize.height.toDp()
    DpSize(width, height)
}