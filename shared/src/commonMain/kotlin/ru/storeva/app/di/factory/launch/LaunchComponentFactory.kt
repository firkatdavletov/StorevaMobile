package ru.storeva.app.di.factory.launch

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.launch.presentation.LaunchComponent

fun interface LaunchComponentFactory {
    fun create(
        componentContext: ComponentContext,
        output: LaunchComponent.Output,
    ): LaunchComponent
}