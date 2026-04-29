package ru.storeva.app.core.component

interface ComponentErrorHandler {
    fun toUserMessage(throwable: Throwable): String
}