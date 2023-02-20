package com.mouse.core.logger

object SystemLogger : Logger {
    override fun log(tag: String, message: String) = println(message)
}