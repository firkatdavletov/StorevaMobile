package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.authorization.sign_in_component.DefaultSignInComponent
import ru.storeva.android.features.authorization.sign_in_component.SignInCallbacks
import ru.storeva.android.features.authorization.sign_in_component.SignInComponent
import ru.storeva.android.features.authorization.verification_component.DefaultVerificationComponent
import ru.storeva.android.features.authorization.verification_component.VerificationComponent
import ru.storeva.android.features.authorization.verification_component.VerifyCallbacks
import ru.storeva.android.navigation.Config

fun authorizationModule() =
    module {
        single<SignInComponent> { (componentContext: ComponentContext, config: Config.SignIn, callbacks: SignInCallbacks) ->
            DefaultSignInComponent(
                componentContext = componentContext,
                snackBarManager = get(),
                getAuthTypesUseCase = get(),
                verifyPhoneNumberUseCase = get(),
                callbacks = callbacks,
                fromScreen = config.fromScreen,
            )
        }
        factory<VerificationComponent> { (componentContext: ComponentContext, config: Config.Verification, callbacks: VerifyCallbacks) ->
            DefaultVerificationComponent(
                componentContext = componentContext,
                snackBarManager = get(),
                verifyCodeUseCase = get(),
                phoneNumber = config.phoneNumber,
                authType = config.authType,
                callbacks = callbacks,
                fromScreen = config.fromScreen,
                checkId = config.checkId,
                callPhone = config.callPhone,
                loadUserUseCase = get(),
                orderRepository = get(),
                authRepository = get(),
            )
        }
    }