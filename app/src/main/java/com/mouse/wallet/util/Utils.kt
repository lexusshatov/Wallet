package com.mouse.wallet.util

fun Double.formatted(decimals: Int = 4): String =
    String.format("%.${decimals}f", this)
