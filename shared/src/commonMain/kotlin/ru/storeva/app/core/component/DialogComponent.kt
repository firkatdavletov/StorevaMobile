package ru.storeva.app.core.component

interface DialogComponent {
    val title: String
    val message: String

    fun onDismissClicked()
}