package com.mouse.core.logger

object SystemLogger : Logger {
    override fun log(message: String) = println(message)
}