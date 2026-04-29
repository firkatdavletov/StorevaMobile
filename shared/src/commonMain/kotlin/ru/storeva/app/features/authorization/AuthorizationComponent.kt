package ru.storeva.app.features.authorization

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.storeva.app.features.authorization.sign_in_component.SignInComponent
import ru.storeva.app.features.authorization.verification_component.VerificationComponent

interface AuthorizationComponent {
    val childStack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class SignInChild(val component: SignInComponent) : Child()

        class VerificationChild(val component: VerificationComponent) : Child()
    }
}