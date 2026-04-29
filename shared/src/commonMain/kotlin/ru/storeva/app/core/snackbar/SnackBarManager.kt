package ru.storeva.app.core.snackbar

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.storeva.app.features.base.IosComponent

class SnackBarManager : IosComponent {
    private val _messages: MutableSharedFlow<String> = MutableSharedFlow(0)

    val messages: MutableSharedFlow<String>
        get() = _messages

    suspend fun showError(message: String) {
        _messages.emit(message)
    }

    override fun observeEvents(onEvent: (String) -> Unit): () -> Unit {
        val job = CoroutineScope(Dispatchers.Main).launch {
            _messages.collect { onEvent(it) }
        }
        return { job.cancel() }
    }
}