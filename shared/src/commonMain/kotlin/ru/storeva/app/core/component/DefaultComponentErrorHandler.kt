package ru.storeva.app.core.component

class DefaultComponentErrorHandler : ComponentErrorHandler {

    override fun toUserMessage(throwable: Throwable): String {
        return throwable.message ?: "Что-то пошло не так"
    }
}