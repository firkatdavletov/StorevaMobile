package ru.storeva.app.features.base

interface IosComponent {
    // --- API для iOS подписки на события ---
    fun observeEvents(onEvent: (String) -> Unit): () -> Unit
}