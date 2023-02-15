package com.mouse.core.logger

fun interface Logger {
    fun log(tag: String, message: String)
}