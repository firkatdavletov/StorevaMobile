package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.dialogs.delete_user_dialog.DefaultDeleteUserComponent
import ru.storeva.android.features.dialogs.delete_user_dialog.DeleteUserComponent
import ru.storeva.android.features.dialogs.delete_user_dialog.DeleteUserDialogCallbacks
import ru.storeva.android.features.dialogs.logout_user_dialog.DefaultLogoutUserComponent
import ru.storeva.android.features.dialogs.logout_user_dialog.LogoutUserComponent
import ru.storeva.android.features.dialogs.logout_user_dialog.LogoutUserDialogCallbacks
import ru.storeva.android.features.dialogs.product_card.DefaultProductCardComponent
import ru.storeva.android.features.dialogs.product_card.ProductCardComponent
import ru.storeva.android.navigation.DialogConfig

fun dialogsModule() =
    module {
        factory<ProductCardComponent> { (componentContext: ComponentContext, config: DialogConfig.ProductCard) ->
            DefaultProductCardComponent(
                componentContent = componentContext,
                productId = config.productId,
                snackBarManager = get(),
                getProductCardUseCase = get(),
                addToCartUseCase = get(),
                removeFromCartUseCase = get(),
                cartRepository = get(),
            )
        }

        factory<DeleteUserComponent> { (componentContext: ComponentContext, config: DialogConfig.DeleteUser, callbacks: DeleteUserDialogCallbacks) ->
            DefaultDeleteUserComponent(
                componentContext = componentContext,
                snackBarManager = get(),
                deleteUserUseCase = get(),
                callbacks = callbacks,
            )
        }

        factory<LogoutUserComponent> { (componentContext: ComponentContext, config: DialogConfig.LogoutUser, callbacks: LogoutUserDialogCallbacks) ->
            DefaultLogoutUserComponent(
                componentContext = componentContext,
                snackBarManager = get(),
                logoutUserUseCase = get(),
                callbacks = callbacks,
            )
        }
    }