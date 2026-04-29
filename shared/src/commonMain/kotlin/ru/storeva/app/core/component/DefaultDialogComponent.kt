package ru.storeva.app.core.component

import ru.storeva.app.core.component.DialogComponent

class DefaultDialogComponent(
    override val title: String,
    override val message: String,
    private val onDismissed: () -> Unit,
) : DialogComponent {

    override fun onDismissClicked() {
        onDismissed()
    }
}