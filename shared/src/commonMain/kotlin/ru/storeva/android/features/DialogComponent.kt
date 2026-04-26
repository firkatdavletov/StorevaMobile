package ru.storeva.android.features

interface DialogComponent {
    val title: String
    val message: String

    fun onDismissClicked()
}